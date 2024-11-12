package com.ecom.ecommerce.product.services;

import com.ecom.ecommerce.product.entites.Product;
import com.ecom.ecommerce.product.exceptions.ProductNotFoundException;
import com.ecom.ecommerce.product.exceptions.ProductPurchaseException;
import com.ecom.ecommerce.product.mapper.ProductMapper;
import com.ecom.ecommerce.product.repository.ProductRepository;
import com.ecom.ecommerce.product.request.ProductPurchasedRequest;
import com.ecom.ecommerce.product.request.ProductRequest;
import com.ecom.ecommerce.product.response.ProductPurchasedResponse;
import com.ecom.ecommerce.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    public List<ProductResponse> getAllProducts(){
        return repository.findAll()
                .stream().map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
    public Integer createProduct(ProductRequest request) {
        var product = repository.save(mapper.toProduct(request));
        return product.getId();
    }
    public void updateProduct(ProductRequest request) {
        var product = repository.findById(request.id())
                .orElseThrow(()-> new ProductNotFoundException(format("Cannot Update Product :: Product Not Found for the Provided Product Id :: %s", request.id())));
    }

    public List<ProductPurchasedResponse> purchasedProducts(List<ProductPurchasedRequest> request) {
        var productIds = request.stream()
                .map(ProductPurchasedRequest::productId)
                .toList();
        var storedProducts = repository.findAllById(productIds);

        if(productIds.size()!= storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not Exists");
        }

        var storedRequests = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchasedRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchasedResponse>();
        for (int i=0 ;  i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storedRequests.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with product Id " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity((newAvailableQuantity));
            repository.save(product);

            purchasedProducts.add(mapper.toProductPurchasedResponse(product, productRequest.quantity()));
        }
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new ProductNotFoundException(format("Cannot Update Product :: Product Not Found for the Provided Product Id :: %s", productId)));
    }
}

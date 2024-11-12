package com.ecom.ecommerce.product.mapper;

import com.ecom.ecommerce.product.entites.Category;
import com.ecom.ecommerce.product.entites.Product;
import com.ecom.ecommerce.product.request.ProductRequest;
import com.ecom.ecommerce.product.response.ProductPurchasedResponse;
import com.ecom.ecommerce.product.response.ProductResponse;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request){
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .availableQuantity(request.availableQuantity())
                .description(request.description())
                .price(request.price())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchasedResponse toProductPurchasedResponse(Product product, double quantity) {
        return new ProductPurchasedResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}

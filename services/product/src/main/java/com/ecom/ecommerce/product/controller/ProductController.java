package com.ecom.ecommerce.product.controller;

import com.ecom.ecommerce.product.entites.Product;
import com.ecom.ecommerce.product.request.ProductPurchasedRequest;
import com.ecom.ecommerce.product.request.ProductRequest;
import com.ecom.ecommerce.product.response.ProductPurchasedResponse;
import com.ecom.ecommerce.product.response.ProductResponse;
import com.ecom.ecommerce.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(
            @RequestBody @Valid ProductRequest request
    ){
        service.updateProduct(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchasedResponse>> purchasedProducts(
            @RequestBody @Valid List<ProductPurchasedRequest> request
    ){
       return ResponseEntity.ok(service.purchasedProducts(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }

}

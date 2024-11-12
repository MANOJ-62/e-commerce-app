package com.ecom.ecommerce.product.request;

import com.ecom.ecommerce.product.entites.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,

        @NotNull(message = "Product Name Required")
        String name,
        @NotNull(message = "Product description Required")
        String description,
        @Positive(message = "Product availableQuantity Should be Positive")
        double availableQuantity,
        @Positive(message = "Product price Should be Positive")
        BigDecimal price,
        @NotNull(message = "Product category Required")
        Integer categoryId
) {
}

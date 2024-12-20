package com.ecom.ecommerce.product.response;

import com.ecom.ecommerce.product.entites.Category;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String CategoryDescription
) {
}

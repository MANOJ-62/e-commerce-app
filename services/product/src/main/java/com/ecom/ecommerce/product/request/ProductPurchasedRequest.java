package com.ecom.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;

public record ProductPurchasedRequest(
        @NotNull(message = "Product Id is Required")
        Integer productId,
        @NotNull(message = "Product quantity is Required")
        double quantity
) {
}

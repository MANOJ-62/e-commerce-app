package com.ecom.ecommerce.order.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is Mandatory")
        Integer ProductId,
        @Positive(message = "Quantity is Mandatory")
        double quantity
) {
}

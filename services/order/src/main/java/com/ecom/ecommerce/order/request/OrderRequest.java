package com.ecom.ecommerce.order.request;

import com.ecom.ecommerce.order.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment Method should be precised")
        PaymentMethod paymentMethod,
        @NotBlank(message = "Customer should be precised")
        @NotEmpty(message = "Customer should be precised")
        @NotNull(message = "Customer should be precised")
        String customerId,
        @NotEmpty(message = "You should purchase Atleast one product")
        List<PurchaseRequest> products) {
}

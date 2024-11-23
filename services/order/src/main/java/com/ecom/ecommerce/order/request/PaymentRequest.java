package com.ecom.ecommerce.order.request;

import com.ecom.ecommerce.order.customer.CustomerResponse;
import com.ecom.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}

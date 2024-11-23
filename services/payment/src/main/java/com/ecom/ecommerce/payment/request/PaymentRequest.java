package com.ecom.ecommerce.payment.request;

import com.ecom.ecommerce.PaymentApplication;
import com.ecom.ecommerce.payment.Customer;
import com.ecom.ecommerce.payment.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}

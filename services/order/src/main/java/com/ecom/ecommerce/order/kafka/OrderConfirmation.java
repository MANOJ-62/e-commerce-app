package com.ecom.ecommerce.order.kafka;

import com.ecom.ecommerce.order.customer.CustomerResponse;
import com.ecom.ecommerce.order.enums.PaymentMethod;
import com.ecom.ecommerce.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}

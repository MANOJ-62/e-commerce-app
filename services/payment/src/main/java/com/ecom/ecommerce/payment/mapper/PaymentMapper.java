package com.ecom.ecommerce.payment.mapper;

import com.ecom.ecommerce.payment.entities.Payment;
import com.ecom.ecommerce.payment.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}

package com.ecom.ecommerce.notification.enums;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment Successfully Processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order Successfully Processed");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplates(String template, String subject){
        this.template=template;
        this.subject=subject;
    }
}
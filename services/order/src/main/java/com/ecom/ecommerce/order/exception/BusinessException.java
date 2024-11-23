package com.ecom.ecommerce.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private final String msg;
//    public BusinessException(String message) {
//        super(message);
//    }
}

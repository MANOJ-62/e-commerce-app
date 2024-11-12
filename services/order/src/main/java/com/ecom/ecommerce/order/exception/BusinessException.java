package com.ecom.ecommerce.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
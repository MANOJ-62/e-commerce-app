package com.ecom.ecommerce.customer.handler;

import com.ecom.ecommerce.customer.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException exp){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var feildName = ((FieldError)error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(feildName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }
}

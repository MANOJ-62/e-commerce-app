package com.ecom.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "First Name is Required")
        String firstname,
        @NotNull(message = "lastname Name is Required")
        String lastname,
        @NotNull(message = "email Name is Required")
        @Email(message = "The Format of Email is not valid")
        String email
) {
}

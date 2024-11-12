package com.ecom.ecommerce.customer.requests;

import com.ecom.ecommerce.customer.entities.Address;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest (
     String id,
     @NotNull(message = "Customer FirstName is required ")
     String firstname,
     @NotNull(message = "Customer LastName is required ")
     String lastname,
     @NotNull(message = "Customer email is required ")
     String email,
     @NotNull(message = "Customer Address is required ")
     Address address
){

}

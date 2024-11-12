package com.ecom.ecommerce.customer.customer.response;

import com.ecom.ecommerce.customer.entities.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}

package com.ecom.ecommerce.customer.repositories;

import com.ecom.ecommerce.customer.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}

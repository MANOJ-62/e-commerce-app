package com.ecom.ecommerce.payment.repository;

import com.ecom.ecommerce.payment.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

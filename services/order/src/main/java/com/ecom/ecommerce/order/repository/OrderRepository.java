package com.ecom.ecommerce.order.repository;

import com.ecom.ecommerce.order.entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

package com.ecom.ecommerce.order.repository;

import com.ecom.ecommerce.order.entites.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}

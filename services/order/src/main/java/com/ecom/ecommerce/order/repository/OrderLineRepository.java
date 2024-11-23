package com.ecom.ecommerce.order.repository;

import com.ecom.ecommerce.order.entites.OrderLine;
import com.ecom.ecommerce.order.orderline.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}

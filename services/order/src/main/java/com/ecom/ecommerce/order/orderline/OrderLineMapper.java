package com.ecom.ecommerce.order.orderline;

import com.ecom.ecommerce.order.entites.Order;
import com.ecom.ecommerce.order.entites.OrderLine;
import com.ecom.ecommerce.order.service.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderline(OrderLineRequest request){
        return OrderLine.builder()
                .Id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .Id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderlineResponse(OrderLineResponse orderLineResponse) {
        return new OrderLineResponse(orderLineResponse.id(), orderLineResponse.quantity());
    }
}

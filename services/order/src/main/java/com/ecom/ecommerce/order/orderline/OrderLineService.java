package com.ecom.ecommerce.order.orderline;

import com.ecom.ecommerce.order.repository.OrderLineRepository;
import com.ecom.ecommerce.order.service.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public void saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderline(orderLineRequest);
        repository.save(order);
    }

}

package com.ecom.ecommerce.order.service;

import com.ecom.ecommerce.order.customer.CustomerClient;
import com.ecom.ecommerce.order.entites.OrderLine;
import com.ecom.ecommerce.order.exception.BusinessException;
import com.ecom.ecommerce.order.mapper.OrderMapper;
import com.ecom.ecommerce.order.orderline.OrderLineService;
import com.ecom.ecommerce.order.product.ProductClient;
import com.ecom.ecommerce.order.repository.OrderRepository;
import com.ecom.ecommerce.order.request.OrderRequest;
import com.ecom.ecommerce.order.request.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {

        //check the customer  --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot Create Order:: No Customer Exist with the provided id" + request.customerId()));

        //purchase the products --> products MS
        this.productClient.purchaseProducts(request.products());
        var order = this.repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.ProductId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //persist order
        //persist order lines
        //start payment process
        //send the order confirmation --> notification MS (Kafka)
        return null;
    }
}

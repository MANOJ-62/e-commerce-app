package com.ecom.ecommerce.order.service;

import com.ecom.ecommerce.order.OrderResponse;
import com.ecom.ecommerce.order.customer.CustomerClient;
import com.ecom.ecommerce.order.entites.OrderLine;
import com.ecom.ecommerce.order.exception.BusinessException;
import com.ecom.ecommerce.order.kafka.OrderConfirmation;
import com.ecom.ecommerce.order.kafka.OrderProducer;
import com.ecom.ecommerce.order.mapper.OrderMapper;
import com.ecom.ecommerce.order.orderline.OrderLineService;
import com.ecom.ecommerce.order.payment.PaymentClient;
import com.ecom.ecommerce.order.product.ProductClient;
import com.ecom.ecommerce.order.repository.OrderRepository;
import com.ecom.ecommerce.order.request.OrderRequest;
import com.ecom.ecommerce.order.request.PaymentRequest;
import com.ecom.ecommerce.order.request.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createOrder(OrderRequest request) {

        //check the customer  --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot Create Order:: No Customer Exist with the provided id" + request.customerId()));

        //purchase the products --> products MS
        //persist order
        //persist order lines
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
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
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation --> notification MS (Kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(String.format("No Order found for the provided ID: %d", orderId)));
    }
}

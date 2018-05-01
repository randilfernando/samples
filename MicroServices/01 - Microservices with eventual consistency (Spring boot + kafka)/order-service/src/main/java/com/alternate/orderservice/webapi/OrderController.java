package com.alternate.orderservice.webapi;

import com.alternate.orderservice.backend.OrderService;
import com.alternate.orderservice.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        Order order = new Order(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal(), "pending");
        orderService.createOrder(order);
        CreateOrderResponse createOrderResponse = new CreateOrderResponse(order.getOrderId());
        return ResponseEntity.ok(createOrderResponse);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderById(@PathVariable("id") Integer orderId) {
        Optional<Order> order = orderService.getOrderByOrderId(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.alternate.orderservice.backend;

import com.alternate.orderservice.domain.Order;
import com.alternate.orderservice.domain.events.CustomerCreditReservationFailedEvent;
import com.alternate.orderservice.domain.events.CustomerCreditReservedEvent;

import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);

    Optional<Order> getOrderByOrderId(Integer orderId);

    void handleCustomerCreditReservedEvent(CustomerCreditReservedEvent customerCreditReservedEvent);

    void handleCustomerCreditReservationFailedEvent(CustomerCreditReservationFailedEvent customerCreditReservationFailedEvent);
}

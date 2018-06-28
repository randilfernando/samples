package com.alternate.eventsourcingsample.ordermanagement.repository;

import com.alternate.eventsourcingsample.eventstore.DemoStore;
import com.alternate.eventsourcingsample.ordermanagement.entity.OrderEntity;
import com.alternate.eventsourcingsample.ordermanagement.events.OrderEvent;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public OrderEntity getOrderById(final String orderId) {
        List<OrderEvent> orderEvents = new ArrayList<>();

        for (OrderEvent orderEvent: DemoStore.getAllEvents()) {
            if (orderEvent.getOrderId().equals(orderId)) {
                orderEvents.add(orderEvent);
            }
        }

        OrderEntity orderEntity = new OrderEntity();

        for (OrderEvent event: orderEvents) {
            orderEntity.apply(event);
        }

        return orderEntity;
    }
}

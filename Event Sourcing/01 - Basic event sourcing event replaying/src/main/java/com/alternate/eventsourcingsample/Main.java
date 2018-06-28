package com.alternate.eventsourcingsample;

import com.alternate.eventsourcingsample.eventstore.DemoStore;
import com.alternate.eventsourcingsample.ordermanagement.entity.OrderEntity;
import com.alternate.eventsourcingsample.ordermanagement.entity.OrderItemEntity;
import com.alternate.eventsourcingsample.ordermanagement.events.ItemAddedEvent;
import com.alternate.eventsourcingsample.ordermanagement.events.OrderCreatedEvent;
import com.alternate.eventsourcingsample.ordermanagement.events.OrderFinalizedEvent;
import com.alternate.eventsourcingsample.ordermanagement.repository.OrderRepository;

public class Main {
    public static void main(String[] args) {
        DemoStore.addEvent(new OrderCreatedEvent("CR-0001","140161F"));
        DemoStore.addEvent(new ItemAddedEvent("CR-0001", new OrderItemEntity("V001", "carrot", 2, 50.00, 100.00)));
        DemoStore.addEvent(new OrderFinalizedEvent("CR-0001", 100.00));

        OrderRepository orderRepository = new OrderRepository();
        OrderEntity orderEntity = orderRepository.getOrderById("CR-0001");
        System.out.println(orderEntity.toString());
    }
}

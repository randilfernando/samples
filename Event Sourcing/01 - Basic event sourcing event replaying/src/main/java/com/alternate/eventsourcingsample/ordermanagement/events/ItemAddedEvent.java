package com.alternate.eventsourcingsample.ordermanagement.events;

import com.alternate.eventsourcingsample.ordermanagement.entity.OrderItemEntity;

public class ItemAddedEvent extends OrderEvent {
    public final OrderItemEntity item;

    public ItemAddedEvent(String orderId, OrderItemEntity item) {
        super(orderId, "itemAddedEvent");
        this.item = item;
    }

    public OrderItemEntity getItem() {
        return item;
    }
}

package com.alternate.eventsourcingsample.ordermanagement.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.Map;

public class OrderEvent {
    private final String orderId;
    private final String eventName;
    private final Date createdDate;

    protected OrderEvent(String orderId, String eventName) {
        this.orderId = orderId;
        this.eventName = eventName;
        this.createdDate = new Date();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}

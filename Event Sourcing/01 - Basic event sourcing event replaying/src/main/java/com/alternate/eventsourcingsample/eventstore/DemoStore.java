package com.alternate.eventsourcingsample.eventstore;

import com.alternate.eventsourcingsample.ordermanagement.events.OrderEvent;

import java.util.ArrayList;
import java.util.List;

public class DemoStore {
    private static List<OrderEvent> events = new ArrayList<>();

    public static void addEvent(OrderEvent event) {
        events.add(event);
    }

    public static List<OrderEvent> getAllEvents() {
        return events;
    }
}

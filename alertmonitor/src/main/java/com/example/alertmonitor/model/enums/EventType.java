package com.example.alertmonitor.model.enums;

import com.example.alertmonitor.exceptions.InvalidDataException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EventType {
    PAYMENT_EXCEPTION ("PAYMENT_EXCEPTION"),
    USER_SERVICE_EXCEPTION ("USER_SERVICE_EXCEPTION");

    private final String name;

    private static final Map<String, EventType> nameToEventTypeMap = new HashMap<>();

    static {
        for(EventType EventType : EnumSet.allOf(EventType.class)) {
            nameToEventTypeMap.put(EventType.getName(), EventType);
        }
    }

    EventType (String name) {
        this.name = name;
    }

    public static EventType getEventType(String name) {
        if(nameToEventTypeMap.containsKey(name)) {
            return nameToEventTypeMap.get(name);
        }

        throw new InvalidDataException("Invalid EventType name" + name);
    }

    public String getName() {
        return this.name;
    }
}

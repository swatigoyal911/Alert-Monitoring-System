package com.example.alertmonitor.strategies;

import com.example.alertmonitor.model.Event;
import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.List;
import java.util.Map;

public class SlidingWindowStrategy implements AlertStrategy{
    @Override
    public boolean isThresholdBreached(Map<Client, Map<EventType, List<Event>>> eventBuffer,
                                       Client client,
                                       EventType eventType,
                                       AlertConfig alertConfig) {
        List<Event> events = eventBuffer.get(client).get(eventType);
        long currentTime = System.currentTimeMillis();
        long windowStartTime = currentTime - alertConfig.getWindowSizeInSecs() * 1000L;

        events.removeIf(event -> event.getTimestamp() < windowStartTime);

        return events.size() >= alertConfig.getCount();
    }
}
package com.example.alertmonitor.strategies;

import com.example.alertmonitor.model.Event;
import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.List;
import java.util.Map;

public class SimpleCountAlertStrategy implements AlertStrategy{
    @Override
    public boolean isThresholdBreached(Map<Client, Map<EventType, List<Event>>> eventBuffer,
                                       Client client,
                                       EventType eventType,
                                       AlertConfig alertConfig) {
        List<Event> events = eventBuffer.get(client).get(eventType);

        if (events.size() >= alertConfig.getCount()) {
            eventBuffer.get(client).get(eventType).clear();
            return true;
        }

        return false;
    }
}

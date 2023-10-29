package com.example.alertmonitor.services;

import com.example.alertmonitor.factories.AlertStrategyFactory;
import com.example.alertmonitor.model.Event;
import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;
import com.example.alertmonitor.observers.AlertObservable;
import com.example.alertmonitor.strategies.AlertStrategy;
import com.example.alertmonitor.templates.DataProcessor;
import com.example.alertmonitor.templates.JsonDataProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventService {
    private static EventService eventService = null;
    private static final Map<Client, Map<EventType, List<Event>>> eventBuffer = new ConcurrentHashMap<>();
    private static final AlertObservable alertObservable = AlertObservable.getInstance();
    private static final DataProcessor dataProcessor = JsonDataProcessor.getInstance();

    private EventService() {}

    public static synchronized EventService getInstance() {
        if(eventService == null) {
            eventService = new EventService();
        }

        return eventService;
    }

    public void addEvent(Client client, EventType eventType) {
        AlertConfig alertConfig = dataProcessor.getAlertConfig(client, eventType);
        if (alertConfig != null) {
            eventBuffer.computeIfAbsent(client, k -> new ConcurrentHashMap<>())
                    .computeIfAbsent(eventType, k -> new ArrayList<>())
                    .add(getEvent(client, eventType)
            );
            checkAlert(client, eventType);
        }
    }

    public void checkAlert(Client client, EventType eventType) {
        AlertConfig alertConfig = dataProcessor.getAlertConfig(client, eventType);
        if (alertConfig != null) {
            AlertStrategy alertStrategy = AlertStrategyFactory.getAlertStrategy(alertConfig.getType());
            boolean isThresholdBreached = alertStrategy.isThresholdBreached(eventBuffer, client, eventType, alertConfig);

            if(isThresholdBreached) {
                dispatchAlert(client, eventType);
            }
        }
    }

    public void dispatchAlert(Client client, EventType eventType) {
        alertObservable.notifyObservers(getEvent(client, eventType));
    }

    private Event getEvent(Client client, EventType eventType) {
        return new Event.Builder()
                .client(client)
                .eventType(eventType)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}

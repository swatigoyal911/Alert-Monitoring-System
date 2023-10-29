package com.example.alertmonitor.observers;

import com.example.alertmonitor.model.Event;
import com.example.alertmonitor.model.configs.ClientConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.alertmonitor.factories.AlertObserverFactory.getAlertObserver;

public class AlertObservable {
    private final Map<Client, Map<EventType, List<AlertObserver>>> observers;
    private static AlertObservable instance = null;

    public AlertObservable() {
        this.observers = new ConcurrentHashMap<>();
    }

    public static synchronized AlertObservable getInstance() {
        if(instance == null) {
            instance = new AlertObservable();
        }

        return instance;
    }

    public void notifyObservers(Event event) {
        Client client = event.getClient();
        EventType eventType = event.getEventType();

        if (observers.containsKey(client) && observers.get(client).containsKey(eventType)) {
            for (AlertObserver observer : observers.get(client).get(eventType)) {
                observer.handleAlert(event);
            }
        }
    }

    public void addObserver(Map<Client, Map<EventType, ClientConfig>> clientConfigMap) {
        clientConfigMap.forEach(
                (client, eventTypeToClientConfigMap) -> eventTypeToClientConfigMap.forEach(
                        (eventType, clientConfig) -> clientConfig.getDispatchStrategyList().forEach(
                                dispatchStrategy -> {
                                    addObserver(client, eventType, getAlertObserver(dispatchStrategy));
                                })
                )
        );
    }

    private void addObserver(Client client, EventType eventType, AlertObserver observer) {
        observers.computeIfAbsent(client, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(observer);
    }
}

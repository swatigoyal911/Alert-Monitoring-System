package com.example.alertmonitor;

import com.example.alertmonitor.model.configs.ClientConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;
import com.example.alertmonitor.observers.AlertObservable;
import com.example.alertmonitor.services.EventService;
import com.example.alertmonitor.templates.DataProcessor;
import com.example.alertmonitor.templates.JsonDataProcessor;

import java.util.Map;

public class AlertMonitorApplication {
    private static final String CONFIG_FILE_PATH = "/Users/swatigoyal/Downloads/alertmonitor/src/main/resources/config.json";
    private static final DataProcessor dataProcessor = JsonDataProcessor.getInstance();
    private static final AlertObservable alertObservable = AlertObservable.getInstance();
    private static final EventService eventService = EventService.getInstance();

    public static void main(String[] args) {
        Map<Client, Map<EventType, ClientConfig>> clientConfigMap = dataProcessor.renderData(CONFIG_FILE_PATH);
        alertObservable.addObserver(clientConfigMap);

        simulateEvents();
    }

    public static void simulateEvents() {
        for (int i = 0; i < 8; i++) {
            Client client = Client.X;
            EventType eventType = EventType.USER_SERVICE_EXCEPTION;

            eventService.addEvent(client, eventType);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.alertmonitor.templates;

import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.configs.ClientConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.Map;

public abstract class DataProcessor {
    public abstract Map<Client, Map<EventType, ClientConfig>> renderData(String jsonFilePath);

    public abstract AlertConfig getAlertConfig(Client client, EventType eventType);
}

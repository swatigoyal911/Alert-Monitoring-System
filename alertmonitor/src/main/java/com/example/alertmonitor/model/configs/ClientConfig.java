package com.example.alertmonitor.model.configs;

import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.List;

public class ClientConfig {
    private Client client;
    private EventType eventType;
    private AlertConfig alertConfig;
    private List<DispatchStrategy> dispatchStrategyList;

    private ClientConfig() {}

    public static class Builder {
        private Client client;
        private EventType eventType;
        private AlertConfig alertConfig;
        private List<DispatchStrategy> dispatchStrategyList;

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder alertConfig(AlertConfig alertConfig) {
            this.alertConfig = alertConfig;
            return this;
        }

        public Builder dispatchStrategyList(List<DispatchStrategy> dispatchStrategyList) {
            this.dispatchStrategyList = dispatchStrategyList;
            return this;
        }

        public ClientConfig build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.client = client;
            clientConfig.eventType = eventType;
            clientConfig.alertConfig = alertConfig;
            clientConfig.dispatchStrategyList = dispatchStrategyList;
            return clientConfig;
        }
    }

    public Client getClient() {
        return this.client;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public AlertConfig getAlertConfig() {
        return this.alertConfig;
    }

    public List<DispatchStrategy> getDispatchStrategyList() {
        return this.dispatchStrategyList;
    }

    @Override
    public String toString() {
        return "ClientConfig{" +
                "client=" + client +
                ", eventType=" + eventType +
                ", alertConfig=" + alertConfig +
                ", dispatchStrategyList=" + dispatchStrategyList +
                '}';
    }
}

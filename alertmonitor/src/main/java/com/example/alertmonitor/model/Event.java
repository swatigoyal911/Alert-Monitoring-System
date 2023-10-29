package com.example.alertmonitor.model;

import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

public class Event {
    private Client client;
    private EventType eventType;
    private long timestamp;

    private Event() {}

    public static class Builder {
        private Client client;
        private EventType eventType;
        private long timestamp;

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.client = this.client;
            event.eventType = this.eventType;
            event.timestamp = this.timestamp;
            return event;
        }
    }

    public Client getClient() {
        return this.client;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

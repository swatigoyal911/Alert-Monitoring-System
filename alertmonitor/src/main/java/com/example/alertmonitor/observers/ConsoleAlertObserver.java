package com.example.alertmonitor.observers;

import com.example.alertmonitor.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleAlertObserver  implements AlertObserver {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleAlertObserver.class);

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void handleAlert(Event event) {
        logger.info("Dispatching to Console");
        logger.info("Client {}: {} {}", event.getClient(), event.getEventType(), this.message);
        logger.warn("Alert: `{}`", this.message);
    }
}
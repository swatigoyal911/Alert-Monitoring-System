package com.example.alertmonitor.observers;

import com.example.alertmonitor.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailAlertObserver implements AlertObserver {
    private static final Logger logger = LoggerFactory.getLogger(EmailAlertObserver.class);

    private String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void handleAlert(Event event) {
        logger.info("Dispatch an Email");
        logger.info("Client {}: {} {}", event.getClient(), event.getEventType(), this.subject);
        logger.warn("Alert: `{}`", this.subject);
    }
}
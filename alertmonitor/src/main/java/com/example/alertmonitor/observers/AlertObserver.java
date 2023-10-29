package com.example.alertmonitor.observers;

import com.example.alertmonitor.model.Event;

public interface AlertObserver {
    void handleAlert(Event event);
}

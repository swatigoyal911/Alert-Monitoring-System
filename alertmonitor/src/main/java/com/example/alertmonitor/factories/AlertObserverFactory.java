package com.example.alertmonitor.factories;

import com.example.alertmonitor.model.configs.DispatchStrategy;
import com.example.alertmonitor.observers.AlertObserver;
import com.example.alertmonitor.observers.ConsoleAlertObserver;
import com.example.alertmonitor.observers.EmailAlertObserver;

public class AlertObserverFactory {
    public static AlertObserver getAlertObserver(DispatchStrategy dispatchStrategy) {
        return switch (dispatchStrategy.getType()) {
            case CONSOLE:
                ConsoleAlertObserver alertObserver = new ConsoleAlertObserver();
                alertObserver.setMessage(dispatchStrategy.getMessage());
                yield alertObserver;
            case EMAIL:
                EmailAlertObserver emailAlertObserver = new EmailAlertObserver();
                emailAlertObserver.setSubject(dispatchStrategy.getSubject());
                yield emailAlertObserver;
        };
    }
}

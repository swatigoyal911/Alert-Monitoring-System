package com.example.alertmonitor.factories;

import com.example.alertmonitor.model.enums.AlertType;
import com.example.alertmonitor.strategies.AlertStrategy;
import com.example.alertmonitor.strategies.SimpleCountAlertStrategy;
import com.example.alertmonitor.strategies.SlidingWindowStrategy;
import com.example.alertmonitor.strategies.TumblingWindowStrategy;

public class AlertStrategyFactory {
    public static AlertStrategy getAlertStrategy(AlertType type) {
        return switch (type) {
            case SIMPLE_COUNT -> new SimpleCountAlertStrategy();
            case SLIDING_WINDOW -> new SlidingWindowStrategy();
            case TUMBLING_WINDOW -> new TumblingWindowStrategy();
        };
    }
}

package com.example.alertmonitor.strategies;

import com.example.alertmonitor.model.Event;
import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.EventType;

import java.util.List;
import java.util.Map;

public class SlidingWindowStrategy implements AlertStrategy{
    @Override
    public boolean isThresholdBreached(Map<Client, Map<EventType, List<Event>>> eventBuffer,
                                       Client client,
                                       EventType eventType,
                                       AlertConfig alertConfig) {
        List<Event> events = eventBuffer.get(client).get(eventType);
        long currentTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);

        long windowStartTime = (currentTime - calendar.getTimeInMillis()) % (alertConfig.getWindowSizeInSecs() * 1000L);
        if(windowStartTime == 0) {
            events.removeIf(event -> event.getTimestamp() < (currentTime - (alertConfig.getWindowSizeInSecs()*1000L + 1L)));
            return events.size() >= alertConfig.getCount();
        }

        return false;
    }
}

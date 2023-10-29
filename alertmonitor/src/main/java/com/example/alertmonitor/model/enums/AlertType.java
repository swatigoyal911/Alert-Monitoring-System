package com.example.alertmonitor.model.enums;

import com.example.alertmonitor.exceptions.InvalidDataException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AlertType {
    SIMPLE_COUNT("SIMPLE_COUNT"),
    TUMBLING_WINDOW("TUMBLING_WINDOW"),
    SLIDING_WINDOW("SLIDING_WINDOW");

    private final String name;
    
    private static final Map<String, AlertType> nameToAlertTypeMap = new HashMap<>();

    static {
        for(AlertType AlertType : EnumSet.allOf(AlertType.class)) {
            nameToAlertTypeMap.put(AlertType.getName(), AlertType);
        }
    }

    AlertType (String name) {
        this.name = name;
    }

    public static AlertType getAlertType(String name) {
        if(nameToAlertTypeMap.containsKey(name)) {
            return nameToAlertTypeMap.get(name);
        }

        throw new InvalidDataException("Invalid AlertType name" + name);
    }

    public String getName() {
        return this.name;
    }
}

package com.example.alertmonitor.model.enums;

import com.example.alertmonitor.exceptions.InvalidDataException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DispatchStrategyType {
    CONSOLE("CONSOLE"),
    EMAIL("EMAIL");

    private final String name;

    private static final Map<String, DispatchStrategyType> nameToDispatchStrategyTypeMap = new HashMap<>();

    static {
        for(DispatchStrategyType dispatchStrategyType : EnumSet.allOf(DispatchStrategyType.class)) {
            nameToDispatchStrategyTypeMap.put(dispatchStrategyType.getName(), dispatchStrategyType);
        }
    }

    DispatchStrategyType(String name) {
        this.name = name;
    }

    public static DispatchStrategyType getDispatchStrategyType(String name) {
        if(nameToDispatchStrategyTypeMap.containsKey(name)) {
            return nameToDispatchStrategyTypeMap.get(name);
        }

        throw new InvalidDataException("Invalid DispatchStrategyType name" + name);
    }

    public String getName() {
        return this.name;
    }
}

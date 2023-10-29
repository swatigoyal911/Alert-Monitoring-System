package com.example.alertmonitor.model.enums;

import com.example.alertmonitor.exceptions.InvalidDataException;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Client {
    X ("X");

    private final String name;

    private static final Map<String, Client> nameToClientMap = new HashMap<>();

    static {
        for(Client Client : EnumSet.allOf(Client.class)) {
            nameToClientMap.put(Client.getName(), Client);
        }
    }

    Client (String name) {
        this.name = name;
    }

    public static Client getClient(String name) {
        if(nameToClientMap.containsKey(name)) {
            return nameToClientMap.get(name);
        }

        throw new InvalidDataException("Invalid Client name" + name);
    }

    public String getName() {
        return this.name;
    }
}

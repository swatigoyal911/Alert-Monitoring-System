package com.example.alertmonitor.templates;

import com.example.alertmonitor.model.configs.AlertConfig;
import com.example.alertmonitor.model.configs.ClientConfig;
import com.example.alertmonitor.model.configs.DispatchStrategy;
import com.example.alertmonitor.model.enums.AlertType;
import com.example.alertmonitor.model.enums.Client;
import com.example.alertmonitor.model.enums.DispatchStrategyType;
import com.example.alertmonitor.model.enums.EventType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class JsonDataProcessor extends DataProcessor {
    private final Map<Client, Map<EventType, ClientConfig>> clientConfigMap;
    private static JsonDataProcessor jsonDataProcessor = null;

    private JsonDataProcessor() {
        this.clientConfigMap = new ConcurrentHashMap<>();
    }

    public static synchronized JsonDataProcessor getInstance() {
        if(jsonDataProcessor == null) {
            jsonDataProcessor = new JsonDataProcessor();
        }

        return jsonDataProcessor;
    }

    @Override
    public Map<Client, Map<EventType, ClientConfig>> renderData(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));

            for (JsonNode clientConfigNode : jsonNode) {
                Client client = Client.getClient(clientConfigNode.get("client").asText());
                EventType eventType = EventType.getEventType(clientConfigNode.get("eventType").asText());
                AlertConfig alertConfig = constructAlertConfig(clientConfigNode);

                List<DispatchStrategy> dispatchStrategyList = new ArrayList<>();
                JsonNode dispatchList = clientConfigNode.get("dispatchStrategyList");
                for (JsonNode dispatchNode : dispatchList) {
                    DispatchStrategy dispatchStrategy = constructDispatchStrategy(dispatchNode);
                    dispatchStrategyList.add(dispatchStrategy);
                }

                ClientConfig clientConfig = constructClientConfig(client, eventType, alertConfig, dispatchStrategyList);
                createAlertConfig(clientConfig);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientConfigMap;
    }

    @Override
    public AlertConfig getAlertConfig(Client client, EventType eventType) {
        if(clientConfigMap.containsKey(client) && clientConfigMap.get(client).containsKey(eventType)) {
            return clientConfigMap.get(client).get(eventType).getAlertConfig();
        }

        return null;
    }

    private AlertConfig constructAlertConfig(JsonNode clientConfigNode) {
        AlertConfig.Builder builder = new AlertConfig.Builder();

        AlertType alertType = AlertType.getAlertType(clientConfigNode.get("alertConfig").get("type").asText());
        int count = clientConfigNode.get("alertConfig").get("count").asInt();
        builder.type(alertType)
                .count(count);

        if (Objects.nonNull(clientConfigNode.get("alertConfig").get("windowSizeInSecs"))) {
            builder.windowSizeInSecs(clientConfigNode.get("alertConfig").get("windowSizeInSecs").asInt());
        }

        return builder.build();
    }

    private DispatchStrategy constructDispatchStrategy(JsonNode dispatchNode) {
        DispatchStrategyType type = DispatchStrategyType.getDispatchStrategyType(dispatchNode.get("type").asText());
        String message = Objects.nonNull(dispatchNode.get("message")) ? dispatchNode.get("message").asText() : null;
        String subject = Objects.nonNull(dispatchNode.get("subject")) ? dispatchNode.get("subject").asText() : null;

        return new DispatchStrategy.Builder()
                .type(type)
                .message(message)
                .subject(subject)
                .build();
    }

    private ClientConfig constructClientConfig(Client client,
                                               EventType eventType,
                                               AlertConfig alertConfig,
                                               List<DispatchStrategy> dispatchStrategyList) {
        return new ClientConfig.Builder()
                .client(client)
                .eventType(eventType)
                .alertConfig(alertConfig)
                .dispatchStrategyList(dispatchStrategyList)
                .build();
    }

    private void createAlertConfig(ClientConfig clientConfig) {
        clientConfigMap.computeIfAbsent(
                clientConfig.getClient(),
                k -> new ConcurrentHashMap<>()).put(clientConfig.getEventType(), clientConfig);
    }

    @Override
    public String toString() {
        return "JsonDataProcessor{" +
                "clientConfigMap=" + clientConfigMap +
                '}';
    }
}

package com.example.alertmonitor.model.configs;

import com.example.alertmonitor.model.enums.AlertType;

public class AlertConfig {
    private AlertType type;
    private int count;
    private int windowSizeInSecs;

    private AlertConfig() {
    }

    public static class Builder {
        private AlertType type;
        private int count;
        private int windowSizeInSecs;

        public Builder type(AlertType type) {
            this.type = type;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder windowSizeInSecs(int windowSizeInSecs) {
            this.windowSizeInSecs = windowSizeInSecs;
            return this;
        }

        public AlertConfig build() {
            AlertConfig alertConfig = new AlertConfig();
            alertConfig.type = type;
            alertConfig.count = count;
            alertConfig.windowSizeInSecs = windowSizeInSecs;
            return alertConfig;
        }
    }

    public AlertType getType() {
        return this.type;
    }

    public int getCount() {
        return this.count;
    }

    public int getWindowSizeInSecs() {
        return this.windowSizeInSecs;
    }

    @Override
    public String toString() {
        return "AlertConfig{" +
                "type=" + type +
                ", count=" + count +
                ", windowSizeInSecs=" + windowSizeInSecs +
                '}';
    }
}

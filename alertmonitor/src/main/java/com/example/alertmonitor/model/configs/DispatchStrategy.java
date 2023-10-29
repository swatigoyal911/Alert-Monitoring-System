package com.example.alertmonitor.model.configs;

import com.example.alertmonitor.model.enums.DispatchStrategyType;

public class DispatchStrategy {
    private DispatchStrategyType type;
    private String message;
    private String subject;

    private DispatchStrategy() {

    }

    public static class Builder {
        private DispatchStrategyType type;
        private String message;
        private String subject;

        public Builder type(DispatchStrategyType type) {
            this.type = type;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public DispatchStrategy build() {
            DispatchStrategy dispatchStrategy = new DispatchStrategy();
            dispatchStrategy.message = this.message;
            dispatchStrategy.type = this.type;
            dispatchStrategy.subject = this.subject;
            return dispatchStrategy;
        }
    }

    public DispatchStrategyType getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSubject() {
        return this.subject;
    }

    @Override
    public String toString() {
        return "DispatchStrategy{" +
                "type=" + type +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

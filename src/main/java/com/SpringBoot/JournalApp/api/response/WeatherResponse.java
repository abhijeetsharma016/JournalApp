package com.SpringBoot.JournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private Current current;

    // Getter and setter for current
    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Current {
        @JsonProperty("temp_c")
        private double temperature;

        @JsonProperty("feelslike_c")
        private double feelslike;

        // Getters and setters
        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getFeelslike() {
            return feelslike;
        }

        public void setFeelslike(double feelslike) {
            this.feelslike = feelslike;
        }
    }
}
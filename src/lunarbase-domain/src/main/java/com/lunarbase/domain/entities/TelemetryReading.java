package com.lunarbase.domain.entities;

import java.time.LocalDateTime;

public class TelemetryReading extends Entity {
    private String sensorType;
    private double value;
    private String unit;
    private String location;
    private LocalDateTime timestamp;

    public TelemetryReading() {
        super();
    }

    public TelemetryReading(String sensorType, double value, String unit, String location) {
        super();
        this.sensorType = sensorType;
        this.value = value;
        this.unit = unit;
        this.location = location;
        this.timestamp = LocalDateTime.now();
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
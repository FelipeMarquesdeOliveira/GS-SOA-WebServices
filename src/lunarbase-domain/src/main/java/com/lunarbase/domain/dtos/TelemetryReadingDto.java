package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.TelemetryReading;
import java.time.LocalDateTime;

public record TelemetryReadingDto(
    int id,
    String sensorType,
    double value,
    String unit,
    String location,
    LocalDateTime timestamp
) {
    public static TelemetryReadingDto fromEntity(TelemetryReading reading) {
        return new TelemetryReadingDto(
            reading.getId(),
            reading.getSensorType(),
            reading.getValue(),
            reading.getUnit(),
            reading.getLocation(),
            reading.getTimestamp()
        );
    }
}
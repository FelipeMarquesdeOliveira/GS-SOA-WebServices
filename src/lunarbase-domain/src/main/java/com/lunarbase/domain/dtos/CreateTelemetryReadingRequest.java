package com.lunarbase.domain.dtos;

import java.time.LocalDateTime;

public record CreateTelemetryReadingRequest(
    String sensorType,
    double value,
    String unit,
    String location
) {}
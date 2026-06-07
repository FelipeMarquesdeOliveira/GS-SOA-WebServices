package com.lunarbase.telemetry.service;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.entities.TelemetryReading;
import com.lunarbase.domain.interfaces.TelemetryRepository;
import java.util.List;

public class TelemetryService {
    private final TelemetryRepository repository;

    public TelemetryService(TelemetryRepository repository) {
        this.repository = repository;
    }

    public TelemetryReadingDto addReading(CreateTelemetryReadingRequest request) {
        TelemetryReading reading = new TelemetryReading(request.sensorType(), request.value(), request.unit(), request.location());
        return TelemetryReadingDto.fromEntity(repository.save(reading));
    }

    public CurrentStateDto getCurrentState() {
        List<TelemetryReading> readings = repository.findAll();
        var latest = readings.stream()
            .collect(java.util.stream.Collectors.groupingBy(TelemetryReading::getSensorType))
            .entrySet().stream()
            .map(e -> e.getValue().stream().max(java.util.Comparator.comparing(TelemetryReading::getTimestamp)).orElse(null))
            .filter(java.util.Objects::nonNull)
            .toList();

        if (latest.isEmpty()) return null;

        return new CurrentStateDto(
            latest.stream().filter(r -> r.getSensorType().equals("Temperature")).findFirst().map(TelemetryReading::getValue).orElse(0.0),
            latest.stream().filter(r -> r.getSensorType().equals("Pressure")).findFirst().map(TelemetryReading::getValue).orElse(0.0),
            latest.stream().filter(r -> r.getSensorType().equals("Radiation")).findFirst().map(TelemetryReading::getValue).orElse(0.0),
            latest.stream().filter(r -> r.getSensorType().equals("Oxygen")).findFirst().map(TelemetryReading::getValue).orElse(0.0),
            latest.stream().filter(r -> r.getSensorType().equals("Energy")).findFirst().map(TelemetryReading::getValue).orElse(0.0),
            java.time.LocalDateTime.now()
        );
    }

    public List<TelemetryReadingDto> getAllReadings() {
        return repository.findAll().stream().map(TelemetryReadingDto::fromEntity).toList();
    }
}
package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.TelemetryReading;
import com.lunarbase.domain.interfaces.TelemetryRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TelemetryRepositoryImpl extends InMemoryRepository<TelemetryReading> implements TelemetryRepository {

    @Override
    public TelemetryReading findCurrentReading() {
        return database.values().stream()
            .max(Comparator.comparing(TelemetryReading::getTimestamp))
            .orElse(null);
    }

    @Override
    public List<TelemetryReading> findReadingsInRange(LocalDateTime from, LocalDateTime to) {
        return database.values().stream()
            .filter(r -> !r.getTimestamp().isBefore(from) && !r.getTimestamp().isAfter(to))
            .sorted(Comparator.comparing(TelemetryReading::getTimestamp).reversed())
            .collect(Collectors.toList());
    }
}
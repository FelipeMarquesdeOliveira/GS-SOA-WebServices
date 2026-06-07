package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.TelemetryReading;
import java.time.LocalDateTime;
import java.util.List;

public interface TelemetryRepository extends Repository<TelemetryReading> {
    TelemetryReading findCurrentReading();
    List<TelemetryReading> findReadingsInRange(LocalDateTime from, LocalDateTime to);
}
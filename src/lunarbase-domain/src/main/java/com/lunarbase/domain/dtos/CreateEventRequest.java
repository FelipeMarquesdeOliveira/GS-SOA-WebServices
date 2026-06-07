package com.lunarbase.domain.dtos;

import com.lunarbase.domain.enums.EventSeverity;

public record CreateEventRequest(
    String title,
    String description,
    EventSeverity severity,
    Integer resourceId
) {}
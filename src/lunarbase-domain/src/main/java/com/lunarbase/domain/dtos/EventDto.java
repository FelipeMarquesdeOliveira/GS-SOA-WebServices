package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.SpaceEvent;
import com.lunarbase.domain.enums.EventSeverity;
import com.lunarbase.domain.enums.EventStatus;
import java.time.LocalDateTime;

public record EventDto(
    int id,
    String title,
    String description,
    EventSeverity severity,
    EventStatus status,
    Integer resourceId,
    LocalDateTime acknowledgedAt,
    String acknowledgedBy,
    LocalDateTime createdAt
) {
    public static EventDto fromEntity(SpaceEvent event) {
        return new EventDto(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getSeverity(),
            event.getStatus(),
            event.getResourceId(),
            event.getAcknowledgedAt(),
            event.getAcknowledgedBy(),
            event.getCreatedAt()
        );
    }
}
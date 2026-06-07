package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.CrewTask;
import java.time.LocalDateTime;

public record CrewTaskDto(
    int id,
    int crewMemberId,
    String title,
    String description,
    LocalDateTime dueDate,
    boolean isCompleted,
    LocalDateTime createdAt
) {
    public static CrewTaskDto fromEntity(CrewTask task) {
        return new CrewTaskDto(
            task.getId(),
            task.getCrewMemberId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.isCompleted(),
            task.getCreatedAt()
        );
    }
}
package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.Simulation;
import java.time.LocalDateTime;

public record SimulationDto(
    int id,
    String name,
    String scenario,
    LocalDateTime startedAt,
    LocalDateTime completedAt,
    boolean isSuccess,
    String result,
    LocalDateTime createdAt
) {
    public static SimulationDto fromEntity(Simulation sim) {
        return new SimulationDto(
            sim.getId(),
            sim.getName(),
            sim.getScenario(),
            sim.getStartedAt(),
            sim.getCompletedAt(),
            sim.isSuccess(),
            sim.getResult(),
            sim.getCreatedAt()
        );
    }
}
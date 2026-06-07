package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.Resource;
import com.lunarbase.domain.enums.ResourceStatus;
import com.lunarbase.domain.enums.ResourceType;
import java.time.LocalDateTime;

public record ResourceDto(
    int id,
    String name,
    ResourceType type,
    double currentAmount,
    double maxCapacity,
    double usagePercentage,
    ResourceStatus status,
    String location,
    LocalDateTime createdAt
) {
    public static ResourceDto fromEntity(Resource resource) {
        return new ResourceDto(
            resource.getId(),
            resource.getName(),
            resource.getType(),
            resource.getCurrentAmount(),
            resource.getMaxCapacity(),
            resource.getUsagePercentage(),
            resource.getStatus(),
            resource.getLocation(),
            resource.getCreatedAt()
        );
    }
}
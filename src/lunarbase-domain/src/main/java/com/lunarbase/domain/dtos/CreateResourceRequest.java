package com.lunarbase.domain.dtos;

import com.lunarbase.domain.enums.ResourceType;

public record CreateResourceRequest(
    String name,
    ResourceType type,
    double maxCapacity,
    String location
) {}
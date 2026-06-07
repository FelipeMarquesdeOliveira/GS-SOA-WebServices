package com.lunarbase.domain.dtos;

public record AsteroidDto(
    String name,
    double estimatedDiameter,
    double missDistance,
    String velocity,
    boolean isPotentiallyHazardous
) {}
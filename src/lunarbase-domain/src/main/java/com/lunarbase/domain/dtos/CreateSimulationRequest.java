package com.lunarbase.domain.dtos;

public record CreateSimulationRequest(
    String name,
    String scenario
) {}
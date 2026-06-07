package com.lunarbase.domain.dtos;

public record MarsWeatherDto(
    String sol,
    double temperature,
    double pressure,
    String windSpeed,
    String season
) {}
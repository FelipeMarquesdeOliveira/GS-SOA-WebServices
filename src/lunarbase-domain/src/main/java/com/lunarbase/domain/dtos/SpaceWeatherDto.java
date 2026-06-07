package com.lunarbase.domain.dtos;

public record SpaceWeatherDto(
    String date,
    String solarFlare,
    double solarWindSpeed,
    int geomagneticActivity
) {}
package com.lunarbase.space.service;

import com.lunarbase.domain.dtos.*;
import java.util.List;

public class SpaceIntegrationService {

    public SpaceWeatherDto getSpaceWeather() {
        return new SpaceWeatherDto(
            java.time.LocalDate.now().toString(),
            "M1.2",
            450.5,
            2
        );
    }

    public List<AsteroidDto> getAsteroids() {
        return List.of(
            new AsteroidDto("433 Eros", 16.84, 0.17, "12.9 km/s", false),
            new AsteroidDto("1036 Ganymed", 35.0, 0.42, "11.0 km/s", true),
            new AsteroidDto("2024 MK4", 0.015, 0.002, "8.2 km/s", false)
        );
    }

    public MarsWeatherDto getMarsWeather() {
        return new MarsWeatherDto(
            "Sol " + java.time.LocalDate.now().getDayOfYear(),
            -63.5,
            6.1,
            "12 m/s",
            "Northern Winter"
        );
    }
}
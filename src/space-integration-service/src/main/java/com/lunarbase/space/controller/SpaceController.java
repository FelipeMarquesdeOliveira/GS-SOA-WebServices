package com.lunarbase.space.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.space.service.SpaceIntegrationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/space")
public class SpaceController {
    private final SpaceIntegrationService service = new SpaceIntegrationService();

    @GetMapping("/weather")
    public SpaceWeatherDto getWeather() {
        return service.getSpaceWeather();
    }

    @GetMapping("/asteroids")
    public List<AsteroidDto> getAsteroids() {
        return service.getAsteroids();
    }

    @GetMapping("/mars/weather")
    public MarsWeatherDto getMarsWeather() {
        return service.getMarsWeather();
    }
}
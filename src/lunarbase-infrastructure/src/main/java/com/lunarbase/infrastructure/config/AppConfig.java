package com.lunarbase.infrastructure.config;

import com.lunarbase.domain.interfaces.*;
import com.lunarbase.infrastructure.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ResourceRepository resourceRepository() {
        return new ResourceRepositoryImpl();
    }

    @Bean
    public CrewRepository crewRepository() {
        return new CrewRepositoryImpl();
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepositoryImpl();
    }

    @Bean
    public SimulationRepository simulationRepository() {
        return new SimulationRepositoryImpl();
    }

    @Bean
    public TelemetryRepository telemetryRepository() {
        return new TelemetryRepositoryImpl();
    }
}
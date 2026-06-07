package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.Simulation;
import com.lunarbase.domain.interfaces.SimulationRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationRepositoryImpl extends InMemoryRepository<Simulation> implements SimulationRepository {

    @Override
    public List<Simulation> findHistory(int limit) {
        return database.values().stream()
            .sorted(Comparator.comparing(Simulation::getStartedAt).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }
}
package com.lunarbase.simulation.service;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.entities.Simulation;
import com.lunarbase.domain.exceptions.NotFoundException;
import com.lunarbase.domain.interfaces.SimulationRepository;
import java.util.List;

public class SimulationService {
    private final SimulationRepository repository;

    public SimulationService(SimulationRepository repository) {
        this.repository = repository;
    }

    public SimulationDto create(CreateSimulationRequest request) {
        Simulation sim = new Simulation(request.name(), request.scenario());
        return SimulationDto.fromEntity(repository.save(sim));
    }

    public SimulationDto run(int id) {
        Simulation sim = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Simulation", id));

        java.util.Random random = new java.util.Random();
        boolean success = random.nextDouble() > 0.3;
        String result = success
            ? "Simulation completed successfully. Predicted outcome: Positive with " + (70 + random.nextInt(30)) + "% confidence."
            : "Simulation failed. Critical issues detected in " + (1 + random.nextInt(5)) + " parameters.";

        sim.complete(success, result);
        repository.save(sim);

        return SimulationDto.fromEntity(sim);
    }

    public SimulationDto getById(int id) {
        return repository.findById(id)
            .map(SimulationDto::fromEntity)
            .orElse(null);
    }

    public List<SimulationDto> getHistory(int limit) {
        return repository.findHistory(limit).stream().map(SimulationDto::fromEntity).toList();
    }
}
package com.lunarbase.simulation.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.interfaces.SimulationRepository;
import com.lunarbase.simulation.service.SimulationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationController {
    private final SimulationService service;

    public SimulationController(SimulationRepository repository) {
        this.service = new SimulationService(repository);
    }

    @PostMapping
    public SimulationDto create(@RequestBody CreateSimulationRequest request) {
        return service.create(request);
    }

    @PostMapping("/{id}/run")
    public SimulationDto run(@PathVariable int id) {
        return service.run(id);
    }

    @GetMapping("/{id}")
    public SimulationDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/history")
    public List<SimulationDto> getHistory(@RequestParam(defaultValue = "10") int limit) {
        return service.getHistory(limit);
    }
}
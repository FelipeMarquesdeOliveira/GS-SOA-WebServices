package com.lunarbase.resource.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.interfaces.ResourceRepository;
import com.lunarbase.resource.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
    private final ResourceService service;

    public ResourcesController(ResourceRepository repository) {
        this.service = new ResourceService(repository);
    }

    @GetMapping
    public List<ResourceDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResourceDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ResourceDto> create(@RequestBody CreateResourceRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResourceDto update(@PathVariable int id, @RequestBody double amount) {
        return service.updateAmount(id, amount);
    }

    @PostMapping("/{id}/consume")
    public ResourceDto consume(@PathVariable int id, @RequestBody double amount) {
        return service.consume(id, amount);
    }

    @PostMapping("/{id}/replenish")
    public ResourceDto replenish(@PathVariable int id, @RequestBody double amount) {
        return service.replenish(id, amount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats")
    public ResponseEntity<ResourceStatistics> getStats() {
        return ResponseEntity.ok(service.getStatistics());
    }

    @GetMapping("/{id}/history")
    public List<ResourceHistoryDto> getHistory(
            @PathVariable int id,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        return service.getHistory(id, from, to);
    }
}
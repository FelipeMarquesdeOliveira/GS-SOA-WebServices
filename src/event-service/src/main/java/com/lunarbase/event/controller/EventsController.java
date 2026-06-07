package com.lunarbase.event.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.interfaces.EventRepository;
import com.lunarbase.event.service.EventService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventsController {
    private final EventService service;

    public EventsController(EventRepository repository) {
        this.service = new EventService(repository);
    }

    @GetMapping
    public List<EventDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/active")
    public List<EventDto> getActive() {
        return service.getActive();
    }

    @GetMapping("/resource/{resourceId}")
    public List<EventDto> getByResource(@PathVariable int resourceId) {
        return service.getByResource(resourceId);
    }

    @PostMapping
    public EventDto create(@RequestBody CreateEventRequest request) {
        return service.create(request);
    }

    @PostMapping("/{id}/ack")
    public EventDto acknowledge(@PathVariable int id, @RequestBody Map<String, String> body) {
        return service.acknowledge(id, body.get("acknowledgedBy"));
    }

    @PostMapping("/{id}/link-resource")
    public EventDto linkResource(@PathVariable int id, @RequestBody Map<String, Integer> body) {
        return service.linkResource(id, body.get("resourceId"));
    }
}
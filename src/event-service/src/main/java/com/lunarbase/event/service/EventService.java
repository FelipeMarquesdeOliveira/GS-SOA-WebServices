package com.lunarbase.event.service;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.entities.SpaceEvent;
import com.lunarbase.domain.exceptions.NotFoundException;
import com.lunarbase.domain.interfaces.EventRepository;
import java.util.List;

public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<EventDto> getAll() {
        return repository.findAll().stream().map(EventDto::fromEntity).toList();
    }

    public EventDto create(CreateEventRequest request) {
        SpaceEvent event = new SpaceEvent(request.title(), request.description(), request.severity(), request.resourceId());
        return EventDto.fromEntity(repository.save(event));
    }

    public EventDto acknowledge(int id, String acknowledgedBy) {
        SpaceEvent event = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Event", id));
        event.acknowledge(acknowledgedBy);
        return EventDto.fromEntity(repository.save(event));
    }

    public EventDto linkResource(int id, int resourceId) {
        SpaceEvent event = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Event", id));
        event.linkResource(resourceId);
        return EventDto.fromEntity(repository.save(event));
    }

    public List<EventDto> getActive() {
        return repository.findActiveEvents().stream().map(EventDto::fromEntity).toList();
    }

    public List<EventDto> getByResource(int resourceId) {
        return repository.findByResourceId(resourceId).stream().map(EventDto::fromEntity).toList();
    }
}
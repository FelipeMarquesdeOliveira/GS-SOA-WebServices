package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.SpaceEvent;
import com.lunarbase.domain.enums.EventStatus;
import com.lunarbase.domain.interfaces.EventRepository;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryImpl extends InMemoryRepository<SpaceEvent> implements EventRepository {

    @Override
    public List<SpaceEvent> findActiveEvents() {
        return database.values().stream()
            .filter(e -> e.getStatus() == EventStatus.ACTIVE)
            .collect(Collectors.toList());
    }

    @Override
    public List<SpaceEvent> findByResourceId(int resourceId) {
        return database.values().stream()
            .filter(e -> e.getResourceId() != null && e.getResourceId() == resourceId)
            .collect(Collectors.toList());
    }
}
package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.SpaceEvent;
import java.util.List;

public interface EventRepository extends Repository<SpaceEvent> {
    List<SpaceEvent> findActiveEvents();
    List<SpaceEvent> findByResourceId(int resourceId);
}
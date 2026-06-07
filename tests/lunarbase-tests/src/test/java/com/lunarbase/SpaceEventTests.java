package com.lunarbase;

import com.lunarbase.domain.entities.SpaceEvent;
import com.lunarbase.domain.enums.EventSeverity;
import com.lunarbase.domain.enums.EventStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaceEventTests {

    @Test
    public void spaceEvent_constructor_setsInitialValues() {
        SpaceEvent event = new SpaceEvent("Oxygen Low", "Oxygen levels below 20%", EventSeverity.CRITICAL, 1);

        assertEquals("Oxygen Low", event.getTitle());
        assertEquals("Oxygen levels below 20%", event.getDescription());
        assertEquals(EventSeverity.CRITICAL, event.getSeverity());
        assertEquals(EventStatus.ACTIVE, event.getStatus());
        assertEquals(1, event.getResourceId());
    }

    @Test
    public void spaceEvent_acknowledge_updatesStatus() {
        SpaceEvent event = new SpaceEvent("Test Event", "Description", EventSeverity.WARNING, null);

        event.acknowledge("Commander Silva");

        assertEquals(EventStatus.ACKNOWLEDGED, event.getStatus());
        assertEquals("Commander Silva", event.getAcknowledgedBy());
        assertNotNull(event.getAcknowledgedAt());
    }

    @Test
    public void spaceEvent_linkResource_updatesResourceId() {
        SpaceEvent event = new SpaceEvent("Test Event", "Description", EventSeverity.INFO, null);

        event.linkResource(42);

        assertEquals(42, event.getResourceId());
    }

    @Test
    public void spaceEvent_resolve_updatesStatus() {
        SpaceEvent event = new SpaceEvent("Test Event", "Description", EventSeverity.WARNING, null);

        event.resolve();

        assertEquals(EventStatus.RESOLVED, event.getStatus());
    }

    @Test
    public void spaceEvent_archive_updatesStatus() {
        SpaceEvent event = new SpaceEvent("Test Event", "Description", EventSeverity.INFO, null);
        event.resolve();

        event.archive();

        assertEquals(EventStatus.ARCHIVED, event.getStatus());
    }
}
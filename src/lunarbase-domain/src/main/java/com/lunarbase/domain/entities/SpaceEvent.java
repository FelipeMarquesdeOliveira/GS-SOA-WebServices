package com.lunarbase.domain.entities;

import com.lunarbase.domain.enums.EventSeverity;
import com.lunarbase.domain.enums.EventStatus;
import java.time.LocalDateTime;

public class SpaceEvent extends Entity {
    private String title;
    private String description;
    private EventSeverity severity;
    private EventStatus status;
    private Integer resourceId;
    private LocalDateTime acknowledgedAt;
    private String acknowledgedBy;

    public SpaceEvent() {
        super();
    }

    public SpaceEvent(String title, String description, EventSeverity severity, Integer resourceId) {
        super();
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = EventStatus.ACTIVE;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(EventSeverity severity) {
        this.severity = severity;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public LocalDateTime getAcknowledgedAt() {
        return acknowledgedAt;
    }

    public void setAcknowledgedAt(LocalDateTime acknowledgedAt) {
        this.acknowledgedAt = acknowledgedAt;
    }

    public String getAcknowledgedBy() {
        return acknowledgedBy;
    }

    public void setAcknowledgedBy(String acknowledgedBy) {
        this.acknowledgedBy = acknowledgedBy;
    }

    public void acknowledge(String acknowledgedBy) {
        this.status = EventStatus.ACKNOWLEDGED;
        this.acknowledgedAt = LocalDateTime.now();
        this.acknowledgedBy = acknowledgedBy;
        markUpdated();
    }

    public void resolve() {
        this.status = EventStatus.RESOLVED;
        markUpdated();
    }

    public void archive() {
        this.status = EventStatus.ARCHIVED;
        markUpdated();
    }

    public void linkResource(int resourceId) {
        this.resourceId = resourceId;
        markUpdated();
    }
}
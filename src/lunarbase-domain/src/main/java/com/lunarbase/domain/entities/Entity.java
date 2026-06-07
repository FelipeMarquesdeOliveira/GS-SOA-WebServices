package com.lunarbase.domain.entities;

import java.time.LocalDateTime;

public abstract class Entity {
    private Integer id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Entity() {
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void markUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
}
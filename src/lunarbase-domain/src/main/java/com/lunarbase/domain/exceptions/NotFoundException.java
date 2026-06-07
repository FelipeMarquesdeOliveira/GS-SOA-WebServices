package com.lunarbase.domain.exceptions;

public class NotFoundException extends RuntimeException {
    private final int entityId;

    public NotFoundException(String entityName, int id) {
        super(entityName + " with id " + id + " was not found.");
        this.entityId = id;
    }

    public int getEntityId() {
        return entityId;
    }
}
package com.lunarbase.domain.exceptions;

public class ResourceCriticalException extends RuntimeException {
    private final int resourceId;
    private final String resourceName;

    public ResourceCriticalException(int resourceId, String resourceName, String message) {
        super("Critical alert: " + resourceName + " (ID: " + resourceId + ") - " + message);
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }
}
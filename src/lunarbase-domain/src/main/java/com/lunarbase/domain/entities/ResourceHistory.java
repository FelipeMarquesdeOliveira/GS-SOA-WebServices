package com.lunarbase.domain.entities;

import java.time.LocalDateTime;

public class ResourceHistory extends Entity {
    private int resourceId;
    private double amountBefore;
    private double amountAfter;
    private String action;
    private LocalDateTime timestamp;

    public ResourceHistory() {
        super();
    }

    public ResourceHistory(int resourceId, double amountBefore, double amountAfter, String action) {
        super();
        this.resourceId = resourceId;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public double getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(double amountBefore) {
        this.amountBefore = amountBefore;
    }

    public double getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(double amountAfter) {
        this.amountAfter = amountAfter;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
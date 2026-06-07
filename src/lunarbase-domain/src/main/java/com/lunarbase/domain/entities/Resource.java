package com.lunarbase.domain.entities;

import com.lunarbase.domain.enums.ResourceStatus;
import com.lunarbase.domain.enums.ResourceType;

public class Resource extends Entity {
    private String name;
    private ResourceType type;
    private double currentAmount;
    private double maxCapacity;
    private ResourceStatus status;
    private String location;

    public Resource() {
        super();
    }

    public Resource(String name, ResourceType type, double maxCapacity, String location) {
        super();
        this.name = name;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.currentAmount = maxCapacity;
        this.status = ResourceStatus.STABLE;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void updateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.currentAmount = Math.min(amount, maxCapacity);
        updateStatus();
        markUpdated();
    }

    public void consume(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Consumption amount cannot be negative");
        }
        this.currentAmount = Math.max(0, currentAmount - amount);
        updateStatus();
        markUpdated();
    }

    public void replenish(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Replenish amount cannot be negative");
        }
        this.currentAmount = Math.min(maxCapacity, currentAmount + amount);
        updateStatus();
        markUpdated();
    }

    private void updateStatus() {
        double percentage = currentAmount / maxCapacity;

        if (percentage > 0.5) {
            this.status = ResourceStatus.STABLE;
        } else if (percentage > 0.2) {
            this.status = ResourceStatus.WARNING;
        } else if (percentage > 0) {
            this.status = ResourceStatus.CRITICAL;
        } else {
            this.status = ResourceStatus.OFFLINE;
        }
    }

    public double getUsagePercentage() {
        return maxCapacity > 0 ? (currentAmount / maxCapacity) * 100 : 0;
    }
}
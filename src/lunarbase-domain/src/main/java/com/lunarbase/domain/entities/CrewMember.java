package com.lunarbase.domain.entities;

import com.lunarbase.domain.enums.CrewRole;
import java.time.LocalDateTime;

public class CrewMember extends Entity {
    private String name;
    private CrewRole role;
    private String specialization;
    private boolean isActive;
    private LocalDateTime lastShift;

    public CrewMember() {
        super();
    }

    public CrewMember(String name, CrewRole role, String specialization) {
        super();
        this.name = name;
        this.role = role;
        this.specialization = specialization;
        this.isActive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CrewRole getRole() {
        return role;
    }

    public void setRole(CrewRole role) {
        this.role = role;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getLastShift() {
        return lastShift;
    }

    public void setLastShift(LocalDateTime lastShift) {
        this.lastShift = lastShift;
    }

    public void assignShift() {
        this.lastShift = LocalDateTime.now();
        markUpdated();
    }

    public void deactivate() {
        this.isActive = false;
        markUpdated();
    }

    public void activate() {
        this.isActive = true;
        markUpdated();
    }
}
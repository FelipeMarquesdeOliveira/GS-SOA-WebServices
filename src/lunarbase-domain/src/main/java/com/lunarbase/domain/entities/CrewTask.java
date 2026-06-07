package com.lunarbase.domain.entities;

import java.time.LocalDateTime;

public class CrewTask extends Entity {
    private int crewMemberId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean isCompleted;

    public CrewTask() {
        super();
    }

    public CrewTask(int crewMemberId, String title, String description, LocalDateTime dueDate) {
        super();
        this.crewMemberId = crewMemberId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public int getCrewMemberId() {
        return crewMemberId;
    }

    public void setCrewMemberId(int crewMemberId) {
        this.crewMemberId = crewMemberId;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void markCompleted() {
        this.isCompleted = true;
        markUpdated();
    }
}
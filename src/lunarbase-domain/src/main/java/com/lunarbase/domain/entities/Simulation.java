package com.lunarbase.domain.entities;

import java.time.LocalDateTime;

public class Simulation extends Entity {
    private String name;
    private String scenario;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private boolean isSuccess;
    private String result;

    public Simulation() {
        super();
    }

    public Simulation(String name, String scenario) {
        super();
        this.name = name;
        this.scenario = scenario;
        this.startedAt = LocalDateTime.now();
        this.isSuccess = false;
        this.result = "Running";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void complete(boolean success, String result) {
        this.completedAt = LocalDateTime.now();
        this.isSuccess = success;
        this.result = result;
        markUpdated();
    }
}
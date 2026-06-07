package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.CrewMember;
import com.lunarbase.domain.entities.CrewTask;
import com.lunarbase.domain.interfaces.CrewRepository;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepositoryImpl extends InMemoryRepository<CrewMember> implements CrewRepository {

    private final java.util.Map<Integer, java.util.List<CrewTask>> tasksByMember = new java.util.HashMap<>();

    @Override
    public List<CrewMember> findActiveMembers() {
        return database.values().stream()
            .filter(CrewMember::isActive)
            .collect(Collectors.toList());
    }

    @Override
    public List<CrewTask> findTasksByMember(int crewMemberId) {
        return tasksByMember.getOrDefault(crewMemberId, java.util.Collections.emptyList());
    }

    @Override
    public void saveTask(CrewTask task) {
        if (task.getId() == null) {
            task.setId(getNextId());
        }
        tasksByMember.computeIfAbsent(task.getCrewMemberId(), k -> new java.util.ArrayList<>()).add(task);
    }
}
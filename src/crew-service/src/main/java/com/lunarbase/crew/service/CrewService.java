package com.lunarbase.crew.service;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.entities.CrewMember;
import com.lunarbase.domain.entities.CrewTask;
import com.lunarbase.domain.exceptions.NotFoundException;
import com.lunarbase.domain.interfaces.CrewRepository;
import java.util.List;

public class CrewService {
    private final CrewRepository repository;

    public CrewService(CrewRepository repository) {
        this.repository = repository;
    }

    public List<CrewMemberDto> getAll() {
        return repository.findAll().stream().map(CrewMemberDto::fromEntity).toList();
    }

    public CrewMemberDto create(CreateCrewMemberRequest request) {
        CrewMember member = new CrewMember(request.name(), request.role(), request.specialization());
        return CrewMemberDto.fromEntity(repository.save(member));
    }

    public CrewMemberDto getById(int id) {
        return repository.findById(id).map(CrewMemberDto::fromEntity).orElse(null);
    }

    public List<CrewTaskDto> getTasks(int crewMemberId) {
        if (repository.findById(crewMemberId).isEmpty()) {
            throw new NotFoundException("CrewMember", crewMemberId);
        }
        return repository.findTasksByMember(crewMemberId).stream().map(CrewTaskDto::fromEntity).toList();
    }

    public CrewTaskDto createTask(CreateTaskRequest request) {
        if (repository.findById(request.crewMemberId()).isEmpty()) {
            throw new NotFoundException("CrewMember", request.crewMemberId());
        }
        CrewTask task = new CrewTask(request.crewMemberId(), request.title(), request.description(), request.dueDate());
        repository.saveTask(task);
        return CrewTaskDto.fromEntity(task);
    }

    public CrewMemberDto assignShift(int id) {
        CrewMember member = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("CrewMember", id));
        member.assignShift();
        return CrewMemberDto.fromEntity(repository.save(member));
    }
}
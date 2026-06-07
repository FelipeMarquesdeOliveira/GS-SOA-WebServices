package com.lunarbase.crew.controller;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.interfaces.CrewRepository;
import com.lunarbase.crew.service.CrewService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {
    private final CrewService service;

    public CrewController(CrewRepository repository) {
        this.service = new CrewService(repository);
    }

    @GetMapping
    public List<CrewMemberDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CrewMemberDto getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CrewMemberDto create(@RequestBody CreateCrewMemberRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}/tasks")
    public List<CrewTaskDto> getTasks(@PathVariable int id) {
        return service.getTasks(id);
    }

    @PostMapping("/task")
    public CrewTaskDto createTask(@RequestBody CreateTaskRequest request) {
        return service.createTask(request);
    }

    @PostMapping("/{id}/shift")
    public CrewMemberDto assignShift(@PathVariable int id) {
        return service.assignShift(id);
    }
}
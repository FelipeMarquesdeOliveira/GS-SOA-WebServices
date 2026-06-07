package com.lunarbase.resource.service;

import com.lunarbase.domain.dtos.*;
import com.lunarbase.domain.entities.Resource;
import com.lunarbase.domain.entities.ResourceHistory;
import com.lunarbase.domain.enums.ResourceStatus;
import com.lunarbase.domain.exceptions.NotFoundException;
import com.lunarbase.domain.exceptions.ResourceCriticalException;
import com.lunarbase.domain.interfaces.ResourceRepository;
import java.util.List;

public class ResourceService {
    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public List<ResourceDto> getAll() {
        return repository.findAll().stream().map(ResourceDto::fromEntity).toList();
    }

    public ResourceDto getById(int id) {
        return repository.findById(id)
            .map(ResourceDto::fromEntity)
            .orElseThrow(() -> new NotFoundException("Resource", id));
    }

    public ResourceDto create(CreateResourceRequest request) {
        Resource resource = new Resource(request.name(), request.type(), request.maxCapacity(), request.location());
        return ResourceDto.fromEntity(repository.save(resource));
    }

    public ResourceDto updateAmount(int id, double amount) {
        Resource resource = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Resource", id));

        double before = resource.getCurrentAmount();
        resource.updateAmount(amount);

        repository.saveHistory(new ResourceHistory(id, before, resource.getCurrentAmount(), "Update"));
        repository.save(resource);

        if (resource.getStatus() == ResourceStatus.CRITICAL) {
            throw new ResourceCriticalException(id, resource.getName(), "Resource level critical!");
        }

        return ResourceDto.fromEntity(resource);
    }

    public ResourceDto consume(int id, double amount) {
        Resource resource = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Resource", id));

        double before = resource.getCurrentAmount();
        resource.consume(amount);

        repository.saveHistory(new ResourceHistory(id, before, resource.getCurrentAmount(), "Consume"));
        repository.save(resource);

        if (resource.getStatus() == ResourceStatus.CRITICAL) {
            throw new ResourceCriticalException(id, resource.getName(), "Resource level critical!");
        }

        return ResourceDto.fromEntity(resource);
    }

    public ResourceDto replenish(int id, double amount) {
        Resource resource = repository.findById(id)
            .orElseThrow(() -> new NotFoundException("Resource", id));

        double before = resource.getCurrentAmount();
        resource.replenish(amount);

        repository.saveHistory(new ResourceHistory(id, before, resource.getCurrentAmount(), "Replenish"));
        repository.save(resource);

        return ResourceDto.fromEntity(resource);
    }

    public void delete(int id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Resource", id);
        }
        repository.delete(id);
    }

    public ResourceStatistics getStatistics() {
        return repository.getStatistics();
    }

    public List<ResourceHistoryDto> getHistory(int resourceId, String from, String to) {
        if (repository.findById(resourceId).isEmpty()) {
            throw new NotFoundException("Resource", resourceId);
        }

        java.time.LocalDateTime fromDt = from != null ? java.time.LocalDateTime.parse(from) : null;
        java.time.LocalDateTime toDt = to != null ? java.time.LocalDateTime.parse(to) : null;

        return repository.getHistory(resourceId, fromDt, toDt).stream()
            .map(h -> new ResourceHistoryDto(h.getId(), h.getResourceId(), h.getAmountBefore(), h.getAmountAfter(), h.getAction(), h.getTimestamp()))
            .toList();
    }
}
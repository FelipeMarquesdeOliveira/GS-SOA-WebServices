package com.lunarbase.infrastructure.repository;

import com.lunarbase.domain.entities.Resource;
import com.lunarbase.domain.entities.ResourceHistory;
import com.lunarbase.domain.enums.ResourceType;
import com.lunarbase.domain.interfaces.ResourceRepository;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ResourceRepositoryImpl extends InMemoryRepository<Resource> implements ResourceRepository {

    private final List<ResourceHistory> histories = new ArrayList<>();

    @Override
    public List<Resource> findByType(String type) {
        try {
            ResourceType resourceType = ResourceType.valueOf(type.toUpperCase());
            return database.values().stream()
                .filter(r -> r.getType() == resourceType)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public ResourceStatistics getStatistics() {
        List<Resource> resources = new ArrayList<>(database.values());
        ResourceStatistics stats = new ResourceStatistics();

        stats.setTotalResources(resources.size());
        stats.setStableCount((int) resources.stream().filter(r -> r.getStatus() == com.lunarbase.domain.enums.ResourceStatus.STABLE).count());
        stats.setWarningCount((int) resources.stream().filter(r -> r.getStatus() == com.lunarbase.domain.enums.ResourceStatus.WARNING).count());
        stats.setCriticalCount((int) resources.stream().filter(r -> r.getStatus() == com.lunarbase.domain.enums.ResourceStatus.CRITICAL).count());

        if (!resources.isEmpty()) {
            double avgUsage = resources.stream()
                .mapToDouble(Resource::getUsagePercentage)
                .average()
                .orElse(0);
            stats.setAverageUsagePercentage(avgUsage);
        }

        return stats;
    }

    @Override
    public List<ResourceHistory> getHistory(int resourceId, LocalDateTime from, LocalDateTime to) {
        return histories.stream()
            .filter(h -> h.getResourceId() == resourceId)
            .filter(h -> from == null || !h.getTimestamp().isBefore(from))
            .filter(h -> to == null || !h.getTimestamp().isAfter(to))
            .sorted(Comparator.comparing(ResourceHistory::getTimestamp).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public void saveHistory(ResourceHistory history) {
        if (history.getId() == null) {
            history.setId(getNextId());
        }
        histories.add(history);
    }
}
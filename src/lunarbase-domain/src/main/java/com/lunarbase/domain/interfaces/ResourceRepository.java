package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.Resource;
import com.lunarbase.domain.entities.ResourceHistory;
import java.time.LocalDateTime;
import java.util.List;

public interface ResourceRepository extends Repository<Resource> {
    List<Resource> findByType(String type);
    ResourceStatistics getStatistics();
    List<ResourceHistory> getHistory(int resourceId, LocalDateTime from, LocalDateTime to);
    void saveHistory(ResourceHistory history);
}

class ResourceStatistics {
    private int totalResources;
    private int stableCount;
    private int warningCount;
    private int criticalCount;
    private double averageUsagePercentage;

    public int getTotalResources() { return totalResources; }
    public void setTotalResources(int totalResources) { this.totalResources = totalResources; }
    public int getStableCount() { return stableCount; }
    public void setStableCount(int stableCount) { this.stableCount = stableCount; }
    public int getWarningCount() { return warningCount; }
    public void setWarningCount(int warningCount) { this.warningCount = warningCount; }
    public int getCriticalCount() { return criticalCount; }
    public void setCriticalCount(int criticalCount) { this.criticalCount = criticalCount; }
    public double getAverageUsagePercentage() { return averageUsagePercentage; }
    public void setAverageUsagePercentage(double averageUsagePercentage) { this.averageUsagePercentage = averageUsagePercentage; }
}
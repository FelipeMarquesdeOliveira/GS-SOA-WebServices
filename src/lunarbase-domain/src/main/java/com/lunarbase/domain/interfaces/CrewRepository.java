package com.lunarbase.domain.interfaces;

import com.lunarbase.domain.entities.CrewMember;
import com.lunarbase.domain.entities.CrewTask;
import java.util.List;

public interface CrewRepository extends Repository<CrewMember> {
    List<CrewMember> findActiveMembers();
    List<CrewTask> findTasksByMember(int crewMemberId);
    void saveTask(CrewTask task);
}
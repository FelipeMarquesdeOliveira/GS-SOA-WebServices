package com.lunarbase.domain.dtos;

import com.lunarbase.domain.entities.CrewMember;
import com.lunarbase.domain.enums.CrewRole;
import java.time.LocalDateTime;

public record CrewMemberDto(
    int id,
    String name,
    CrewRole role,
    String specialization,
    boolean isActive,
    LocalDateTime lastShift,
    LocalDateTime createdAt
) {
    public static CrewMemberDto fromEntity(CrewMember member) {
        return new CrewMemberDto(
            member.getId(),
            member.getName(),
            member.getRole(),
            member.getSpecialization(),
            member.isActive(),
            member.getLastShift(),
            member.getCreatedAt()
        );
    }
}
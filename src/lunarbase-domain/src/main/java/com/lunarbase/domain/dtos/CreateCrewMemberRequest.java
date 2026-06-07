package com.lunarbase.domain.dtos;

import com.lunarbase.domain.enums.CrewRole;

public record CreateCrewMemberRequest(
    String name,
    CrewRole role,
    String specialization
) {}
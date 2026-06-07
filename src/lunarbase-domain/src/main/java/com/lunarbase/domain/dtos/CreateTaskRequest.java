package com.lunarbase.domain.dtos;

import java.time.LocalDateTime;

public record CreateTaskRequest(
    int crewMemberId,
    String title,
    String description,
    LocalDateTime dueDate
) {}
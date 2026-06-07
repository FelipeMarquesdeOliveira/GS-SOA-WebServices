package com.lunarbase.domain.dtos;

import java.time.LocalDateTime;

public record ResourceHistoryDto(
    int id,
    int resourceId,
    double amountBefore,
    double amountAfter,
    String action,
    LocalDateTime timestamp
) {}
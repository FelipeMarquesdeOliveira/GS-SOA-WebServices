package com.lunarbase.domain.dtos;

import java.time.LocalDateTime;

public record CurrentStateDto(
    double temperature,
    double pressure,
    double radiation,
    double oxygen,
    double energy,
    LocalDateTime timestamp
) {}
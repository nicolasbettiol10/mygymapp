package com.example.springtest.gymclass.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateGymClassRequest(
        @NotBlank String name,
        @NotBlank String instructor,
        @Positive int capacity,
        @NotNull LocalDateTime startTime,
        @Positive int durationMinutes) {
}

package com.example.springtest.gymclass.dto;

import java.time.LocalDateTime;

import com.example.springtest.gymclass.GymClass;

public record GymClassResponse(
        Long id,
        String name,
        String instructor,
        int capacity,
        LocalDateTime startTime,
        int durationMinutes) {

    public static GymClassResponse from(final GymClass gymClass) {
        return new GymClassResponse(gymClass.getId(), gymClass.getName(), gymClass.getInstructor(),
                gymClass.getCapacity(), gymClass.getStartTime(), gymClass.getDurationMinutes());
    }
}

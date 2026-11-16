package com.example.springtest.gymclass;

import java.time.LocalDateTime;
import java.util.List;

public interface GymClassService {

    GymClass create(String name, String instructor, int capacity, LocalDateTime startTime, int durationMinutes);

    List<GymClass> findAll();

    List<GymClass> findByInstructor(String instructor);

    GymClass findById(Long id);
}

package com.example.springtest.gymclass;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GymClassServiceImpl implements GymClassService {

    private final GymClassRepository gymClassRepository;

    public GymClassServiceImpl(final GymClassRepository gymClassRepository) {
        this.gymClassRepository = gymClassRepository;
    }

    @Override
    @Transactional
    public GymClass create(final String name, final String instructor, final int capacity,
            final LocalDateTime startTime, final int durationMinutes) {
        final GymClass gymClass = new GymClass(name, instructor, capacity, startTime, durationMinutes);
        return gymClassRepository.save(gymClass);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GymClass> findAll() {
        return gymClassRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GymClass> findByInstructor(final String instructor) {
        return gymClassRepository.findByInstructor(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public GymClass findById(final Long id) {
        return gymClassRepository.findById(id).orElseThrow(() -> new GymClassNotFoundException(id));
    }
}

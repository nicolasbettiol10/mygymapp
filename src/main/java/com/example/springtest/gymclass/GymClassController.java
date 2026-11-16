package com.example.springtest.gymclass;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtest.gymclass.dto.CreateGymClassRequest;
import com.example.springtest.gymclass.dto.GymClassResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/classes")
public class GymClassController {

    private final GymClassService gymClassService;

    public GymClassController(final GymClassService gymClassService) {
        this.gymClassService = gymClassService;
    }

    @PostMapping
    public ResponseEntity<GymClassResponse> create(@Valid @RequestBody final CreateGymClassRequest request) {
        final GymClass created = gymClassService.create(request.name(), request.instructor(), request.capacity(),
                request.startTime(), request.durationMinutes());
        return ResponseEntity.created(URI.create("/api/classes/" + created.getId()))
                .body(GymClassResponse.from(created));
    }

    @GetMapping
    public List<GymClassResponse> findAll(@RequestParam(required = false) final String instructor) {
        final List<GymClass> gymClasses = Objects.nonNull(instructor)
                ? gymClassService.findByInstructor(instructor)
                : gymClassService.findAll();
        return gymClasses.stream().map(GymClassResponse::from).toList();
    }

    @GetMapping("/{id}")
    public GymClassResponse findById(@PathVariable final Long id) {
        return GymClassResponse.from(gymClassService.findById(id));
    }
}

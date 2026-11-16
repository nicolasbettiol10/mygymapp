package com.example.springtest.gymclass;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class GymClassRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private GymClassRepository gymClassRepository;

    @Test
    void savesAndFindsGymClass() {
        final GymClass saved = gymClassRepository.save(
                new GymClass("Spinning", "Juan Perez", 10, LocalDateTime.of(2026, 11, 20, 20, 0), 60));

        assertThat(saved.getId()).isNotNull();

        final List<GymClass> all = gymClassRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getName()).isEqualTo("Spinning");
    }

    @Test
    void findByIdReturnsEmptyWhenMissing() {
        assertThat(gymClassRepository.findById(999L)).isEmpty();
    }

    @Test
    void findByInstructorIsCaseInsensitive() {
        gymClassRepository.save(
                new GymClass("Spinning", "Juan Perez", 10, LocalDateTime.of(2026, 11, 20, 20, 0), 60));
        gymClassRepository.save(
                new GymClass("Yoga", "Ana Lopez", 15, LocalDateTime.of(2026, 11, 20, 18, 0), 45));

        final List<GymClass> found = gymClassRepository.findByInstructor("juan perez");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getName()).isEqualTo("Spinning");
    }

    @Test
    void findByInstructorReturnsEmptyWhenNoMatch() {
        assertThat(gymClassRepository.findByInstructor("Nadie")).isEmpty();
    }
}

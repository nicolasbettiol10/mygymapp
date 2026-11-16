package com.example.springtest.gymclass;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GymClassRepository extends JpaRepository<GymClass, Long> {

    @Query("SELECT g FROM GymClass g WHERE LOWER(g.instructor) = LOWER(:instructor)")
    List<GymClass> findByInstructor(@Param("instructor") String instructor);
}

package com.example.springtest.gymclass;

public class GymClassNotFoundException extends RuntimeException {

    public GymClassNotFoundException(final Long id) {
        super("Gym class not found with id: " + id);
    }
}

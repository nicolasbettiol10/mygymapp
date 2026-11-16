package com.example.springtest.gymclass;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GymClassExceptionHandler {

    @ExceptionHandler(GymClassNotFoundException.class)
    public ProblemDetail handleNotFound(final GymClassNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}

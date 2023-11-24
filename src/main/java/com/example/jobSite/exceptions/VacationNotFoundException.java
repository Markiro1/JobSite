package com.example.jobSite.exceptions;

public class VacationNotFoundException extends RuntimeException {
    public VacationNotFoundException(String message) {
        super(message);
    };
}

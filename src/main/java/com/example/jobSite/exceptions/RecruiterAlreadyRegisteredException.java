package com.example.jobSite.exceptions;

public class RecruiterAlreadyRegisteredException extends RuntimeException {
    public RecruiterAlreadyRegisteredException(String message) {
        super(message);
    }
}

package com.example.jobSite.exceptions;

public class WrongEmailException extends RuntimeException{
    public WrongEmailException(String message) {
        super(message);
    }
}

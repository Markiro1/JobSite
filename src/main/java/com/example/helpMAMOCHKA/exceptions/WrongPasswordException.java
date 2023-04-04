package com.example.helpMAMOCHKA.exceptions;
public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message) {
        super(message);
    }
}

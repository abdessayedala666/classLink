package com.example.backend.exceptions;

public class SchoolNotFoundException extends RuntimeException {
    public SchoolNotFoundException(String message) {
        super(message);
    }

}

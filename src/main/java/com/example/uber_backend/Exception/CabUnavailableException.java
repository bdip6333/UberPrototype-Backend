package com.example.uber_backend.Exception;

public class CabUnavailableException extends RuntimeException {
    public CabUnavailableException(String message) {
        super(message);
    }
}

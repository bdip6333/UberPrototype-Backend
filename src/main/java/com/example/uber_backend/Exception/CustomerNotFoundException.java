package com.example.uber_backend.Exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}

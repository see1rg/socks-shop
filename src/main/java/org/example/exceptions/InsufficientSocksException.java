package org.example.exceptions;

public class InsufficientSocksException extends RuntimeException{
    public InsufficientSocksException(String message) {
        super(message);
    }
}

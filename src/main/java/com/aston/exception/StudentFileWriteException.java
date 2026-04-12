package com.aston.exception;

public class StudentFileWriteException extends RuntimeException {
    public StudentFileWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}


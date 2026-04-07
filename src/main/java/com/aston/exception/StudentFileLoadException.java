package com.aston.exception;

public class StudentFileLoadException extends RuntimeException {
  public StudentFileLoadException(String message) {
    super(message);
  }

  public StudentFileLoadException(String message, Throwable cause) {
    super(message, cause);
  }
}
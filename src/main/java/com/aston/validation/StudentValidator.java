package com.aston.validation;

import com.aston.exception.ValidationException;

public class StudentValidator {

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name cannot be blank");
        }

        if (name.length() < 3 || name.length() > 100) {
            throw new ValidationException("Name must be between 3 and 100 characters");
        }
    }

    public void validateAverageGrade(Double averageGrade) {
        if (averageGrade == null) {
            throw new ValidationException("Average grade cannot be null");
        }

        if (averageGrade < 0.0 || averageGrade > 5.0) {
            throw new ValidationException("Average grade must be between 0.0 and 5.0");
        }
    }

    public void validateStudentCardNumber(Integer studentCardNumber) {
        if (studentCardNumber == null) {
            throw new ValidationException("Student card number cannot be null");
        }
        if (studentCardNumber <= 0) {
            throw new ValidationException("Student card number must be positive");
        }
    }

    public void validateStudentData(String studentName, Double averageGrade, Integer studentCardNumber) {
        validateName(studentName);
        validateAverageGrade(averageGrade);
        validateStudentCardNumber(studentCardNumber);
    }
}

package com.aston.validation;

import com.aston.exception.ValidationException;

public class StudentValidator {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 100;
    private static final double MIN_AVERAGE_GRADE = 0.0;
    private static final double MAX_AVERAGE_GRADE = 5.0;
    private static final int MIN_STUDENT_CARD_NUMBER = 1;
    private static final String NAME_LETTERS_ONLY_MESSAGE = "Name must contain only letters";

    public void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name cannot be blank");
        }

        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new ValidationException(
                    "Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters"
            );
        }

        if (!name.chars().allMatch(Character::isLetter)) {
            throw new ValidationException(NAME_LETTERS_ONLY_MESSAGE);
        }
    }

    public void validateAverageGrade(Double averageGrade) {
        if (averageGrade == null) {
            throw new ValidationException("Average grade cannot be null");
        }

        if (averageGrade < MIN_AVERAGE_GRADE || averageGrade > MAX_AVERAGE_GRADE) {
            throw new ValidationException(
                    "Average grade must be between " + MIN_AVERAGE_GRADE + " and " + MAX_AVERAGE_GRADE
            );
        }
    }

    public void validateStudentCardNumber(Integer studentCardNumber) {
        if (studentCardNumber == null) {
            throw new ValidationException("Student card number cannot be null");
        }

        if (studentCardNumber < MIN_STUDENT_CARD_NUMBER) {
            throw new ValidationException("Student card number must be positive");
        }
    }

    public void validateStudentData(String studentName, Double averageGrade, Integer studentCardNumber) {
        validateName(studentName);
        validateAverageGrade(averageGrade);
        validateStudentCardNumber(studentCardNumber);
    }
}
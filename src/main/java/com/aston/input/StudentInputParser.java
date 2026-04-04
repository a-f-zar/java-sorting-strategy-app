package com.aston.input;

import com.aston.exception.ValidationException;
import com.aston.models.Student;
import com.aston.validation.StudentValidator;

public class StudentInputParser {

    private final StudentValidator validator = new StudentValidator();

    public Student parseInput(String nameInput, String gradeInput, String cardNumberInput) {
        String name = nameInput != null ? nameInput.trim() : null;

        Double averageGrade;
        try {
            averageGrade = Double.parseDouble(gradeInput);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid grade: " + gradeInput);
        }

        Integer studentCardNumber;
        try {
            studentCardNumber = Integer.valueOf(cardNumberInput);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid card number: " + cardNumberInput);
        }

        validator.validateStudentData(name, averageGrade, studentCardNumber);

        return Student.builder()
                .name(name)
                .averageGrade(averageGrade)
                .studentCardNumber(studentCardNumber)
                .build();
    }
}

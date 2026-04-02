package com.aston.input;

import com.aston.exception.ValidationException;
import com.aston.model.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentInputParserTest {

    private final StudentInputParser parser = new StudentInputParser();

    @Test
    @DisplayName("Парсер создает студента из корректных строковых значений")
    void parseInputShouldCreateStudentFromValidStringValues() {
        Student student = parser.parseInput("Alexandr", "4.5", "12345");

        assertEquals("Alexandr", student.getName());
        assertEquals(4.5, student.getAverageGrade());
        assertEquals(12345, student.getStudentCardNumber());
    }

    @Test
    @DisplayName("Парсер обрезает пробелы у имени перед созданием студента")
    void parseInputShouldTrimNameBeforeCreatingStudent() {
        Student student = parser.parseInput("  Alexandr  ", "4.5", "12345");

        assertEquals("Alexandr", student.getName());
    }

    @Test
    @DisplayName("Парсер выбрасывает исключение при некорректном формате среднего балла")
    void parseInputShouldThrowExceptionForInvalidGradeFormat() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> parser.parseInput("Alexandr", "four", "12345")
        );

        assertEquals("Invalid grade: four", exception.getMessage());
    }

    @Test
    @DisplayName("Парсер выбрасывает исключение при некорректном формате номера зачетки")
    void parseInputShouldThrowExceptionForInvalidStudentCardNumberFormat() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> parser.parseInput("Alexandr", "4.5", "12A45")
        );

        assertEquals("Invalid card number: 12A45", exception.getMessage());
    }

    @Test
    @DisplayName("Парсер выбрасывает исключение, если разобранные данные не проходят валидацию")
    void parseInputShouldThrowExceptionWhenParsedValuesDoNotPassValidation() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> parser.parseInput("Al", "4.5", "12345")
        );

        assertEquals("Name must be between 3 and 100 characters", exception.getMessage());
    }
}

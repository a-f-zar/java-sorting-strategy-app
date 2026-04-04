package com.aston.validation;

import com.aston.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentValidatorTest {

    private final StudentValidator validator = new StudentValidator();

    @Test
    @DisplayName("Имя проходит валидацию при корректном значении")
    void validateNameShouldAcceptValidName() {
        assertDoesNotThrow(() -> validator.validateName("Alexandr"));
    }

    @Test
    @DisplayName("Валидация имени выбрасывает исключение для пустой строки")
    void validateNameShouldThrowExceptionForBlankName() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validateName("   ")
        );

        assertEquals("Name cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Валидация имени выбрасывает исключение для слишком короткого имени")
    void validateNameShouldThrowExceptionForShortName() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validateName("Al")
        );

        assertEquals("Name must be between 3 and 100 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Средний балл проходит валидацию, если находится в диапазоне от 0 до 5")
    void validateAverageGradeShouldAcceptValueFromZeroToFive() {
        assertDoesNotThrow(() -> validator.validateAverageGrade(4.5));
    }

    @Test
    @DisplayName("Валидация среднего балла выбрасывает исключение для значения меньше нуля")
    void validateAverageGradeShouldThrowExceptionForValueBelowZero() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validateAverageGrade(-1.0)
        );

        assertEquals("Average grade must be between 0.0 and 5.0", exception.getMessage());
    }

    @Test
    @DisplayName("Валидация среднего балла выбрасывает исключение для значения больше пяти")
    void validateAverageGradeShouldThrowExceptionForValueAboveFive() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validateAverageGrade(5.1)
        );

        assertEquals("Average grade must be between 0.0 and 5.0", exception.getMessage());
    }

    @Test
    @DisplayName("Номер зачетки проходит валидацию при положительном значении")
    void validateStudentCardNumberShouldAcceptPositiveValue() {
        assertDoesNotThrow(() -> validator.validateStudentCardNumber(12345));
    }

    @Test
    @DisplayName("Валидация номера зачетки выбрасывает исключение для нуля или отрицательного значения")
    void validateStudentCardNumberShouldThrowExceptionForZeroOrNegativeValue() {
        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> validator.validateStudentCardNumber(0)
        );

        assertEquals("Student card number must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Общая валидация проходит для корректных данных студента")
    void validateStudentDataShouldValidateAllStudentFieldsTogether() {
        assertDoesNotThrow(() -> validator.validateStudentData("Alexandr", 4.8, 12345));
    }
}

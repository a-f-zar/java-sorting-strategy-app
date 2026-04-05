package com.aston.generation;

import com.aston.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomStudentGeneratorTest {
    private final RandomStudentGenerator generator = new RandomStudentGenerator();

    @Test
    @DisplayName("Генератор создает одного корректного студента")
    void generateStudentShouldCreateValidStudent() {
        Student student = generator.generateStudent();

        assertNotNull(student);
        assertNotNull(student.getName());
        assertNotNull(student.getAverageGrade());
        assertNotNull(student.getStudentCardNumber());
    }

    @Test
    @DisplayName("Генератор создает коллекцию студентов заданной длины")
    void generateStudentsShouldCreateCollectionWithRequestedSize() {
        List<Student> students = generator.generateStudents(10);

        assertEquals(10, students.size());
        assertTrue(students.stream().allMatch(student -> student != null));
    }

    @Test
    @DisplayName("Генератор создает пустую коллекцию при count = 0")
    void generateStudentsShouldCreateEmptyCollectionWhenCountIsZero() {
        List<Student> students = generator.generateStudents(0);

        assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("Генератор выбрасывает исключение при отрицательной длине коллекции")
    void generateStudentsShouldThrowExceptionForNegativeCount() {
        assertThrows(IllegalArgumentException.class, () -> generator.generateStudents(-1));
    }
}

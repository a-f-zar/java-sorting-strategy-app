package com.aston.concurrent;

import com.aston.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentOccurrenceCounterTest {

    private final StudentOccurrenceCounter counter = new StudentOccurrenceCounter();

    @Test
    @DisplayName("Счетчик возвращает ноль для пустого списка студентов")
    void countOccurrencesShouldReturnZeroForEmptyList() {
        Student target = buildStudent("Alexandr", 4.5, 12345);

        int result = counter.countOccurrences(List.of(), target);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Счетчик возвращает ноль, если искомый студент отсутствует")
    void countOccurrencesShouldReturnZeroWhenStudentIsAbsent() {
        List<Student> students = List.of(
                buildStudent("Alexandr", 4.5, 12345),
                buildStudent("Maria", 5.0, 67890)
        );
        Student target = buildStudent("Ivan", 4.2, 11111);

        int result = counter.countOccurrences(students, target);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Счетчик возвращает один, если студент встречается один раз")
    void countOccurrencesShouldReturnOneWhenStudentOccursOnce() {
        Student target = buildStudent("Alexandr", 4.5, 12345);
        List<Student> students = List.of(
                target,
                buildStudent("Maria", 5.0, 67890),
                buildStudent("Ivan", 4.2, 11111)
        );

        int result = counter.countOccurrences(students, target);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("Счетчик возвращает корректное количество, если студент встречается несколько раз")
    void countOccurrencesShouldReturnCorrectCountForMultipleOccurrences() {
        Student target = buildStudent("Alexandr", 4.5, 12345);
        List<Student> students = List.of(
                buildStudent("Maria", 5.0, 67890),
                target,
                buildStudent("Ivan", 4.2, 11111),
                target,
                target
        );

        int result = counter.countOccurrences(students, target);

        assertEquals(3, result);
    }

    @Test
    @DisplayName("Счетчик выбрасывает исключение, если искомый студент равен null")
    void countOccurrencesShouldThrowExceptionWhenTargetStudentIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> counter.countOccurrences(List.of(), null)
        );
    }

    @Test
    @DisplayName("Счетчик выбрасывает исключение, если список студентов равен null")
    void countOccurrencesShouldThrowExceptionWhenStudentListIsNull() {
        Student target = buildStudent("Alexandr", 4.5, 12345);

        assertThrows(
                IllegalArgumentException.class,
                () -> counter.countOccurrences(null, target)
        );
    }

    @Test
    @DisplayName("Счетчик корректно считает вхождения студента в большом списке")
    void countOccurrencesShouldReturnCorrectCountForLargeList() {
        Student target = buildStudent("Alexandr", 4.5, 12345);
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (i % 20 == 0) {
                students.add(target);
            } else {
                students.add(buildStudent("Student" + i, 4.0, 20000 + i));
            }
        }

        int result = counter.countOccurrences(students, target);

        assertEquals(500, result);
    }

    private Student buildStudent(String name, double averageGrade, int studentCardNumber) {
        return Student.builder()
                .name(name)
                .averageGrade(averageGrade)
                .studentCardNumber(studentCardNumber)
                .build();
    }
}

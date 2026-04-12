package com.aston.input.context;

import com.aston.input.context.strategy.FileInputStudent;
import com.aston.input.context.strategy.ManualInputStudent;
import com.aston.input.context.strategy.RandomInputStudent;
import com.aston.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputStudentTest {

    private final InputStudent inputStudent = new InputStudent();

    @Test
    @DisplayName("Контекст использует стратегию ручного ввода")
    void shouldUseManualInputStrategy() {
        inputStudent.setStrategy(new ManualInputStudent());

        String data = String.join(System.lineSeparator(),
                "Anna",
                "4.5",
                "12345"
        ) + System.lineSeparator();

        List<Student> students = inputStudent.input(
                new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)),
                1
        );

        assertEquals(1, students.size());
        assertEquals("Anna", students.get(0).getName());
    }

    @Test
    @DisplayName("Контекст использует стратегию случайной генерации")
    void shouldUseRandomInputStrategy() {
        inputStudent.setStrategy(new RandomInputStudent());

        List<Student> students = inputStudent.input(3);

        assertEquals(3, students.size());
        assertFalse(students.contains(null));
    }

    @Test
    @DisplayName("Контекст использует стратегию чтения из файла")
    void shouldUseFileInputStrategy() {
        inputStudent.setStrategy(new FileInputStudent());

        String json = """
                [
                  {"name":"Anna","averageGrade":4.5,"studentCardNumber":12345}
                ]
                """;

        List<Student> students = inputStudent.input(
                new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8)),
                0
        );

        assertEquals(1, students.size());
        assertEquals(12345, students.get(0).getStudentCardNumber());
    }

    @Test
    @DisplayName("Контекст выбрасывает исключение если стратегия не установлена")
    void shouldThrowExceptionWhenStrategyIsNotSet() {
        assertThrows(IllegalStateException.class, () -> inputStudent.input(1));
    }
}

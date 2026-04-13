package com.aston.output;

import com.aston.exception.StudentFileWriteException;
import com.aston.models.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentFileWriterTest {

    private final StudentFileWriter writer = new StudentFileWriter();

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Создаёт файл и записывает заголовок и студентов")
    void writeSortedStudents_createsFileWithContent() throws IOException {
        Path file = tempDir.resolve("output.txt");
        List<Student> students = List.of(
                buildStudent("Alice", 4.5, 1001),
                buildStudent("Bob", 3.8, 1002)
        );

        writer.writeSortedStudents(students, file);

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("[SORTED STUDENTS]"));
        assertTrue(content.contains("total: 2"));
        assertTrue(content.contains("1. "));
        assertTrue(content.contains("Alice"));
        assertTrue(content.contains("2. "));
        assertTrue(content.contains("Bob"));
    }

    @Test
    @DisplayName("Режим добавления: второй вызов дописывает блок, первый не стирается")
    void writeSortedStudents_appendsNewBlock() throws IOException {
        Path file = tempDir.resolve("output.txt");

        writer.writeSortedStudents(List.of(buildStudent("Alice", 4.5, 1001)), file);
        writer.writeSortedStudents(List.of(buildStudent("Bob", 3.8, 1002)), file);

        String content = Files.readString(file);
        assertTrue(content.contains("Alice"), "Первый блок должен остаться");
        assertTrue(content.contains("Bob"), "Второй блок должен быть добавлен");
        assertEquals(2, countOccurrences(content, "[SORTED STUDENTS]"),
                "Должно быть два заголовка [SORTED STUDENTS]");
    }

    @Test
    @DisplayName("Записывает total: 0 для пустого списка")
    void writeSortedStudents_writesEmptyList() throws IOException {
        Path file = tempDir.resolve("output.txt");

        writer.writeSortedStudents(List.of(), file);

        String content = Files.readString(file);
        assertTrue(content.contains("total: 0"));
    }

    @Test
    @DisplayName("Выбрасывает IllegalArgumentException при null списке")
    void writeSortedStudents_throwsWhenStudentsIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> writer.writeSortedStudents(null, tempDir.resolve("f.txt")));
    }

    @Test
    @DisplayName("Выбрасывает IllegalArgumentException при null пути")
    void writeSortedStudents_throwsWhenPathIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> writer.writeSortedStudents(List.of(), null));
    }

    @Test
    @DisplayName("Выбрасывает StudentFileWriteException при ошибке IO")
    void writeSortedStudents_throwsStudentFileWriteExceptionOnIOError() {
        // Директория вместо файла — запись невозможна
        assertThrows(StudentFileWriteException.class,
                () -> writer.writeSortedStudents(List.of(), tempDir));
    }

    @Test
    @DisplayName("Создаёт файл и записывает результат поиска")
    void writeOccurrenceResult_createsFileWithContent() throws IOException {
        Path file = tempDir.resolve("output.txt");
        Student student = buildStudent("Alice", 4.5, 1001);

        writer.writeOccurrenceResult(student, 3, file);

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("[OCCURRENCE RESULT]"));
        assertTrue(content.contains("Alice"));
        assertTrue(content.contains("Occurrences: 3"));
    }

    @Test
    @DisplayName("Режим добавления: два результата поиска — оба присутствуют в файле")
    void writeOccurrenceResult_appendsNewBlock() throws IOException {
        Path file = tempDir.resolve("output.txt");

        writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), 2, file);
        writer.writeOccurrenceResult(buildStudent("Bob", 3.8, 1002), 5, file);

        String content = Files.readString(file);
        assertTrue(content.contains("Alice"));
        assertTrue(content.contains("Occurrences: 2"));
        assertTrue(content.contains("Bob"));
        assertTrue(content.contains("Occurrences: 5"));
        assertEquals(2, countOccurrences(content, "[OCCURRENCE RESULT]"),
                "Должно быть два заголовка [OCCURRENCE RESULT]");
    }

    @Test
    @DisplayName("Записывает нулевое количество вхождений")
    void writeOccurrenceResult_writesZeroCount() throws IOException {
        Path file = tempDir.resolve("output.txt");

        writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), 0, file);

        String content = Files.readString(file);
        assertTrue(content.contains("Occurrences: 0"));
    }

    @Test
    @DisplayName("Смешанные записи: сортировка и поиск — оба блока в одном файле")
    void mixedWrites_bothBlocksPresent() throws IOException {
        Path file = tempDir.resolve("output.txt");

        writer.writeSortedStudents(List.of(buildStudent("Alice", 4.5, 1001)), file);
        writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), 1, file);

        String content = Files.readString(file);
        assertTrue(content.contains("[SORTED STUDENTS]"));
        assertTrue(content.contains("[OCCURRENCE RESULT]"));
    }

    @Test
    @DisplayName("Выбрасывает IllegalArgumentException при null студенте")
    void writeOccurrenceResult_throwsWhenStudentIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> writer.writeOccurrenceResult(null, 1, tempDir.resolve("f.txt")));
    }

    @Test
    @DisplayName("Выбрасывает IllegalArgumentException при null пути")
    void writeOccurrenceResult_throwsWhenPathIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), 1, null));
    }

    @Test
    @DisplayName("Выбрасывает IllegalArgumentException при отрицательном count")
    void writeOccurrenceResult_throwsWhenCountIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), -1,
                        tempDir.resolve("f.txt")));
    }

    @Test
    @DisplayName("Выбрасывает StudentFileWriteException при ошибке IO")
    void writeOccurrenceResult_throwsStudentFileWriteExceptionOnIOError() {
        assertThrows(StudentFileWriteException.class,
                () -> writer.writeOccurrenceResult(buildStudent("Alice", 4.5, 1001), 1, tempDir));
    }

    private Student buildStudent(String name, double averageGrade, int studentCardNumber) {
        return Student.builder()
                .name(name)
                .averageGrade(averageGrade)
                .studentCardNumber(studentCardNumber)
                .build();
    }

    private int countOccurrences(String text, String substring) {
        int count = 0;
        int idx = 0;
        while ((idx = text.indexOf(substring, idx)) != -1) {
            count++;
            idx += substring.length();
        }
        return count;
    }
}
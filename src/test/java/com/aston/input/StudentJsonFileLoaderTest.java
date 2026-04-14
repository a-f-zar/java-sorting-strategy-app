package com.aston.input;

import com.aston.exception.StudentFileLoadException;
import com.aston.models.Student;
import com.aston.models.custom.MyList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentJsonFileLoaderTest {

    private final StudentJsonFileLoader loader = new StudentJsonFileLoader();

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("Загрузчик читает валидный JSON-массив и создает всех студентов")
    void loadShouldCreateAllStudentsFromValidJsonArray() throws Exception {
        Path file = tempDir.resolve("students.json");
        String json = """
                [
                  {"name":"Alexandr","averageGrade":4.5,"studentCardNumber":12345},
                  {"name":"Maria","averageGrade":5.0,"studentCardNumber":67890}
                ]""";
        Files.writeString(file, json);

        MyList<Student> students = loader.load(file);

        assertEquals(2, students.size());
    }

    @Test
    @DisplayName("Загрузчик пропускает невалидные записи")
    void loadShouldSkipInvalidRecords() throws Exception {
        Path file = tempDir.resolve("students-mixed.json");
        String json = """
                [
                  {"name":"Alexandr","averageGrade":4.5,"studentCardNumber":12345},
                  {"name":"Al","averageGrade":4.0,"studentCardNumber":12346},
                  {"name":"Ivan","averageGrade":4.2}
                ]""";
        Files.writeString(file, json);

        MyList<Student> students = loader.load(file);

        assertEquals(1, students.size());
    }

    @Test
    @DisplayName("Загрузчик возвращает пустой список для пустого массива")
    void loadShouldReturnEmptyListForEmptyArray() throws Exception {
        Path file = tempDir.resolve("empty.json");
        Files.writeString(file, "[]");

        MyList<Student> students = loader.load(file);

        assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("Загрузчик выбрасывает исключение, если корень JSON не массив")
    void loadShouldThrowExceptionWhenRootIsNotArray() throws Exception {
        Path file = tempDir.resolve("object.json");
        Files.writeString(file, "{\"name\":\"Alexandr\"}");

        StudentFileLoadException exception = assertThrows(StudentFileLoadException.class, () -> loader.load(file));

        assertEquals("JSON root must be an array of student objects", exception.getMessage());
    }

    @Test
    @DisplayName("Загрузчик выбрасывает StudentFileLoadException при синтаксически некорректном JSON")
    void loadShouldThrowStudentFileLoadExceptionForMalformedJson() throws Exception {
        Path file = tempDir.resolve("malformed.json");
        Files.writeString(file, "[{\"name\":\"Alexandr\"}");

        assertThrows(StudentFileLoadException.class, () -> loader.load(file));
    }
}
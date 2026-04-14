package com.aston.output;

import com.aston.exception.StudentFileWriteException;
import com.aston.models.Student;
import com.aston.models.custom.MyList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentFileWriter {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void writeSortedStudents(MyList<Student> students, Path path) {
        validateArguments(students, path);

        try (BufferedWriter writer = openWriter(path)) {
            writer.write("[SORTED STUDENTS] " + now() + "  (total: " + students.size() + ")");
            writer.newLine();
            for (int i = 0; i < students.size(); i++) {
                writer.write((i + 1) + ". " + students.get(i));
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            throw new StudentFileWriteException(
                    "Failed to write sorted students to file: " + path.toAbsolutePath(), e);
        }
    }

    public void writeOccurrenceResult(Student student, int count, Path path) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }

        try (BufferedWriter writer = openWriter(path)) {
            writer.write("[OCCURRENCE RESULT] " + now());
            writer.newLine();
            writer.write("Student:     " + student);
            writer.newLine();
            writer.write("Occurrences: " + count);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            throw new StudentFileWriteException(
                    "Failed to write occurrence result to file: " + path.toAbsolutePath(), e);
        }
    }

    private BufferedWriter openWriter(Path path) throws IOException {
        return Files.newBufferedWriter(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    private void validateArguments(MyList<Student> students, Path path) {
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("Path cannot be null");
        }
    }

    private String now() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
package com.aston.generation;

import com.aston.models.Student;
import com.aston.validation.StudentValidator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class RandomStudentGenerator {

    private static final List<String> FIRST_NAMES = List.of(
            "Alexandr", "Sergey", "Kirill", "Roman", "Anna",
            "Elena", "Dmitry", "Ivan", "Maria", "Olga"
    );

    private static final double MIN_GRADE = 2.0;
    private static final double MAX_GRADE = 5.0;

    private static final int MIN_CARD_NUMBER = 10000;
    private static final int MAX_CARD_NUMBER = 99999;

    private final StudentValidator validator = new StudentValidator();

    public Student generateStudent() {
        String name = randomName();
        double averageGrade = randomGrade();
        int studentCardNumber = randomCardNumber();

        validator.validateStudentData(name, averageGrade, studentCardNumber);

        return Student.builder()
                .name(name)
                .averageGrade(averageGrade)
                .studentCardNumber(studentCardNumber)
                .build();
    }

    public List<Student> generateStudents(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        }

        return IntStream.range(0, count)
                .mapToObj(i -> generateStudent())
                .toList();
    }

    private String randomName() {
        return FIRST_NAMES.get(ThreadLocalRandom.current().nextInt(FIRST_NAMES.size()));
    }

    private double randomGrade() {
        int min = (int) (MIN_GRADE * 100);
        int max = (int) (MAX_GRADE * 100);

        int value = ThreadLocalRandom.current().nextInt(min, max + 1);
        return value / 100.0;
    }

    private int randomCardNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_CARD_NUMBER, MAX_CARD_NUMBER + 1);
    }
}
package com.aston.sorting;
import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.BubbleSortStrategy;
import com.aston.input.StudentInputParser;
import com.aston.models.Student;
import com.aston.models.comparator.StudentComparator;
import com.aston.models.comparator.StudentComparator.By;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorterStudentTest {
    private final StudentInputParser parser = new StudentInputParser();
    private final Sorter<Student> sorter = new Sorter<>();

    @Test
    @DisplayName("StudentComparator: BubbleSort student by name")
    void StudentComparatorTestName() {
        sorter.setStrategy(new BubbleSortStrategy<>());
        MyList<Student> students = CustomArrayList.of(
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Sergey", "3.8", "123456"),
                parser.parseInput("Kirill", "3.2", "1234")
                );

        MyList<Student> result = sorter.sort(students, StudentComparator.compare(By.NAME));

        MyList<Student> expected = CustomArrayList.of(
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Kirill", "3.2", "1234"),
                parser.parseInput("Sergey", "3.8", "123456")
        );

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }

    @Test
    @DisplayName("StudentComparator: BubbleSort student by grade reversed")
    void StudentComparatorTestGrade() {
        sorter.setStrategy(new BubbleSortStrategy<>());
        MyList<Student> students = CustomArrayList.of(
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Sergey", "3.8", "123456"),
                parser.parseInput("Kirill", "3.2", "1234")
        );

        MyList<Student> result = sorter.sort(students, StudentComparator.compare(By.AVERAGE_GRADE).reversed());

        MyList<Student> expected = CustomArrayList.of(
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Sergey", "3.8", "123456"),
                parser.parseInput("Kirill", "3.2", "1234")
        );

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }

    @Test
    @DisplayName("StudentComparator: BubbleSort student by card")
    void StudentComparatorTestCard() {
        sorter.setStrategy(new BubbleSortStrategy<>());
        MyList<Student> students = CustomArrayList.of(
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Sergey", "3.8", "123456"),
                parser.parseInput("Kirill", "3.2", "1234")
        );

        MyList<Student> result = sorter.sort(students, StudentComparator.compare(By.STUDENT_CARD_NUMBER));

        MyList<Student> expected = CustomArrayList.of(
                parser.parseInput("Kirill", "3.2", "1234"),
                parser.parseInput("Alexandr", "4.5", "12345"),
                parser.parseInput("Sergey", "3.8", "123456")
        );

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }
}
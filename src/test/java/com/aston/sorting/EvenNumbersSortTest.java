package com.aston.sorting;
import com.aston.models.Student;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.BubbleSortStrategy;
import com.aston.sorting.context.strategy.SortingEvenNumbersStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import com.aston.models.comparator.StudentComparator.By;
import static com.aston.models.comparator.StudentComparator.compare;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EvenNumbersSortTest {
    private static final Sorter<Integer> sorter = new Sorter<>();
    private static final Sorter<Student> studentSorter = new Sorter<>();

    @Test
    @DisplayName("Sorting only even numbers")
    void shouldSortOnlyEvenNumbers() {
        sorter.setStrategy(new SortingEvenNumbersStrategy<Integer>(new BubbleSortStrategy<>(), i -> i));
        List<Integer> input = Arrays.asList(5, 4, 7, 2, 3);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertEquals(Arrays.asList(5, 2, 7, 4, 3), result);
    }

    @Test
    @DisplayName("Sorting only even numbers negative")
    void shouldSortOnlyEvenNumbersNegative() {
        sorter.setStrategy(new SortingEvenNumbersStrategy<Integer>(new BubbleSortStrategy<>(), i -> i));
        List<Integer> input = Arrays.asList(5, 4, 7, 2, 3);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertNotEquals(Arrays.asList(5, 4, 7, 2, 3), result);
    }

    @Test
    @DisplayName("Sorting students with even card number")
    void shouldSortOnlyEvenCardNumbers() {

        studentSorter.setStrategy(new SortingEvenNumbersStrategy<Student>(
                new BubbleSortStrategy<>(),
                Student::getStudentCardNumber)
        );

        List<Student> input = Arrays.asList(
                Student.builder().name("A").averageGrade(0d).studentCardNumber(5).build(),
                Student.builder().name("B").averageGrade(0d).studentCardNumber(4).build(),
                Student.builder().name("C").averageGrade(0d).studentCardNumber(7).build(),
                Student.builder().name("D").averageGrade(0d).studentCardNumber(2).build(),
                Student.builder().name("F").averageGrade(0d).studentCardNumber(10).build()
        );

        List<Student> result = studentSorter.sort(
                input,
                compare(By.STUDENT_CARD_NUMBER)
        );

        List<Student> except = Arrays.asList(
                Student.builder().name("A").averageGrade(0d).studentCardNumber(5).build(),
                Student.builder().name("D").averageGrade(0d).studentCardNumber(2).build(),
                Student.builder().name("C").averageGrade(0d).studentCardNumber(7).build(),
                Student.builder().name("B").averageGrade(0d).studentCardNumber(4).build(),
                Student.builder().name("F").averageGrade(0d).studentCardNumber(10).build()
        );

        assertEquals(result, except);
    }
}

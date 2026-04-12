package com.aston.sorting;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.BubbleSortStrategy;
import com.aston.sorting.context.strategy.SortingEvenNumbersStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EvenNumbersSortTest {
    private static final Sorter<Integer> sorter = new Sorter<>();

    @Test
    @DisplayName("Sorting only even numbers")
    void shouldSortOnlyEvenNumbers() {
        sorter.setStrategy(new SortingEvenNumbersStrategy(new BubbleSortStrategy<>()));
        List<Integer> input = Arrays.asList(5, 4, 7, 2, 3);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertEquals(Arrays.asList(5, 2, 7, 4, 3), result);
    }

    @Test
    @DisplayName("Sorting only even numbers negative")
    void shouldSortOnlyEvenNumbersNegative() {
        sorter.setStrategy(new SortingEvenNumbersStrategy(new BubbleSortStrategy<>()));
        List<Integer> input = Arrays.asList(5, 4, 7, 2, 3);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertNotEquals(Arrays.asList(5, 4, 7, 2, 3), result);
    }
}

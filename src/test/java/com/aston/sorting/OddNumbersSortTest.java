package com.aston.sorting;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.SortingOddNumbersStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OddNumbersSortTest {
    private static final Sorter<Integer> sorter = new Sorter<>();

    @Test
    @DisplayName("Sorting only odd numbers")
    void shouldSortOnlyOddNumbers() {
        sorter.setStrategy(new SortingOddNumbersStrategy());
        List<Integer> input = Arrays.asList(5, 8, 3, 4, 1);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertEquals(Arrays.asList(1, 8, 3, 4, 5), result);
    }

    @Test
    @DisplayName("Sorting only odd numbers negative")
    void shouldSortOnlyOddNumbersNegative() {
        sorter.setStrategy(new SortingOddNumbersStrategy());
        List<Integer> input = Arrays.asList(5, 8, 3, 4, 1);
        List<Integer> result = sorter.sort(input, Integer::compareTo);
        assertNotEquals(Arrays.asList(3, 8, 1, 4, 5), result);
    }
}

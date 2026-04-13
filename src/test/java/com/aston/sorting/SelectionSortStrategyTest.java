package com.aston.sorting;

import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.SelectionSortStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectionSortStrategyTest {

    @Test
    @DisplayName("SelectionSortStrategy: сортировка по возрастанию")
    void selectionSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new SelectionSortStrategy<>());

        List<Integer> input = List.of(4, 6, 7, 8, 3);
        List<Integer> expected = List.of(3, 4, 6, 7, 8);

        List<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "SelectionSortStrategy should sort correctly");
    }

    @Test
    @DisplayName("SelectionSortStrategy: сортировка по убыванию")
    void selectionSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new SelectionSortStrategy<>());

        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> expected = List.of(5, 4, 3, 2, 1);

        List<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "SelectionSortStrategy should sort in reverse order");
    }
}
package com.aston.sorting;

import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.SelectionSortStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectionSortStrategyTest {

    @Test
    @DisplayName("SelectionSortStrategy: сортировка по возрастанию")
    void selectionSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new SelectionSortStrategy<>());

        MyList<Integer> input = CustomArrayList.of(4, 6, 7, 8, 3);
        MyList<Integer> expected = CustomArrayList.of(3, 4, 6, 7, 8);

        MyList<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "SelectionSortStrategy should sort correctly");
    }

    @Test
    @DisplayName("SelectionSortStrategy: сортировка по убыванию")
    void selectionSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new SelectionSortStrategy<>());

        MyList<Integer> input = CustomArrayList.of(1, 2, 3, 4, 5);
        MyList<Integer> expected = CustomArrayList.of(5, 4, 3, 2, 1);

        MyList<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "SelectionSortStrategy should sort in reverse order");
    }
}
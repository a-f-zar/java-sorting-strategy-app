package com.aston.sorting;

import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;
import com.aston.sorting.context.Sorter;
import com.aston.sorting.context.strategy.BubbleSortStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SorterTest {
    @Test
    @DisplayName("BubbleSort: сортировка по возрастанию")
    void bubbleSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        MyList<Integer> input = CustomArrayList.of(4, 6, 7, 8, 3);
        MyList<Integer> expected = CustomArrayList.of(3, 4, 6, 7, 8);

        MyList<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }

    @Test
    @DisplayName("BubbleSort: сортировка по убыванию")
    void bubbleSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        MyList<Integer> input = CustomArrayList.of(1, 2, 3, 4, 5);
        MyList<Integer> expected = CustomArrayList.of(5, 4, 3, 2, 1);

        MyList<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "BubbleSort should sort in reverse order");
    }

    @Test
    @DisplayName("Sorter: не установлена стратегия")
    void sorterHasNoStrategy() {
        Sorter<Integer> sorter = new Sorter<>();
        MyList<Integer> input = CustomArrayList.of(4, 6, 7, 8, 3);
        assertThrows(IllegalStateException.class, () ->
                sorter.sort(input, Integer::compareTo)
        );
    }
}

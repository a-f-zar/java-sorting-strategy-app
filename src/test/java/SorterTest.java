import com.aston.context.Sorter;
import com.aston.context.strategy.BubbleSortStrategy;
import com.aston.context.strategy.InsertionSortStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SorterTest {
    @Test
    @DisplayName("BubbleSort: сортировка по возрастанию")
    void bubbleSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        List<Integer> input = List.of(4, 6, 7, 8, 3);
        List<Integer> expected = List.of(3, 4, 6, 7, 8);

        List<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }

    @Test
    @DisplayName("BubbleSort: сортировка по убыванию")
    void bubbleSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> expected = List.of(5, 4, 3, 2, 1);

        List<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "BubbleSort should sort in reverse order");
    }

    @Test
    @DisplayName("InsertionSort: сортировка по возрастанию")
    void insertionSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new InsertionSortStrategy<>());

        List<Integer> input = List.of(4, 6, 7, 8, 3);
        List<Integer> expected = List.of(3, 4, 6, 7, 8);

        List<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "InsertionSort should sort correctly");
    }

    @Test
    @DisplayName("InsertionSort: сортировка по убыванию")
    void insertionSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new InsertionSortStrategy<>());

        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> expected = List.of(5, 4, 3, 2, 1);

        List<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "InsertionSort should sort in reverse order");
    }

    @Test
    @DisplayName("Sorter: не установлена стратегия")
    void sorterHasNoStrategy() {
        Sorter<Integer> sorter = new Sorter<>();
        List<Integer> input = List.of(4, 6, 7, 8, 3);
        assertThrows(IllegalStateException.class, () ->
                sorter.sort(input, Integer::compareTo)
        );
    }
}

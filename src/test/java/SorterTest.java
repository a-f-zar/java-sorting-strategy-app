import com.aston.context.Sorter;
import com.aston.context.strategy.BubbleSortStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorterTest {
    @Test
    void bubbleSortTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        List<Integer> input = List.of(4, 6, 7, 8, 3);
        List<Integer> expected = List.of(3, 4, 6, 7, 8);

        List<Integer> result = sorter.sort(input, Integer::compareTo);

        assertEquals(expected, result, "BubbleSort should sort correctly");
    }

    @Test
    void bubbleSortReverseTest() {
        Sorter<Integer> sorter = new Sorter<>();
        sorter.setStrategy(new BubbleSortStrategy<>());

        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> expected = List.of(5, 4, 3, 2, 1);

        List<Integer> result = sorter.sort(input, (a, b) -> b - a);

        assertEquals(expected, result, "BubbleSort should sort in reverse order");
    }
}

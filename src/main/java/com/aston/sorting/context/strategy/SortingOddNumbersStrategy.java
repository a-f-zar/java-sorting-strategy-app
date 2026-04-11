package com.aston.sorting.context.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SortingOddNumbersStrategy<T> implements SortingStrategy<T> {
    private final BubbleSortStrategy sorter = new BubbleSortStrategy();

    @Override
    public List<T> sort(List<T> inputList, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(inputList);

        List<T> posOdd = list.stream()
                .filter(i -> (int)i % 2 == 1)
                .toList();

        posOdd = sorter.sort(posOdd, comparator);

        int oddIndex = 0;
        for(int i = 0; i < list.size(); i++) {
            if((int)list.get(i) % 2 == 1) {
                list.set(i, posOdd.get(oddIndex));
                oddIndex++;
            }
        }

        return list;
    }
}

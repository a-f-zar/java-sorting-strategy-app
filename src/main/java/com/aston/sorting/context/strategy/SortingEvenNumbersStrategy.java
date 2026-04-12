package com.aston.sorting.context.strategy;

import com.aston.sorting.context.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingEvenNumbersStrategy implements SortingStrategy<Integer> {
    private SortingStrategy<Integer> sorter;

    public SortingEvenNumbersStrategy(SortingStrategy<Integer> sorter) {
        this.sorter = sorter;
    }

    @Override
    public List<Integer> sort(List<Integer> inputList, Comparator<Integer> comparator) {
        List<Integer> list = new ArrayList<>(inputList);

        List<Integer> posEven = list.stream()
                .filter(i -> i % 2 == 0)
                .toList();

        posEven = sorter.sort(posEven, comparator);

        int evenIndex = 0;

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) % 2 == 0) {
                list.set(i, posEven.get(evenIndex));
                evenIndex++;
            }
        }

        return list;
    }
}

package com.aston.context.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BubbleSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public List<T> sort(List<T> inputList, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(inputList);

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }

        return list;
    }
}

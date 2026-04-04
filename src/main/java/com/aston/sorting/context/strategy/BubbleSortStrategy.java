package com.aston.sorting.context.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BubbleSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public List<T> sort(List<T> inputList, Comparator<T> comparator) {
        List<T> list = new ArrayList<>(inputList);

        int n = list.size();
        T tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);

                }
            }
        }

        return list;
    }
}

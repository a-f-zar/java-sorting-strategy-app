package com.aston.sorting.context.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectionSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {

        List<T> sortedList = new ArrayList<>(list);

        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (comparator.compare(sortedList.get(j), sortedList.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T tmp = sortedList.get(i);
                sortedList.set(i, sortedList.get(minIndex));
                sortedList.set(minIndex, tmp);
            }
        }

        return sortedList;
    }
}

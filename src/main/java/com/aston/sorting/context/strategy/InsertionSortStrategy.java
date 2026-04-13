package com.aston.sorting.context.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InsertionSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {

        List<T> sortedList = new ArrayList<>(list);

        for (int i = 1; i < sortedList.size(); i++) {
            T key = sortedList.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(sortedList.get(j), key) > 0) {
                sortedList.set(j + 1, sortedList.get(j));
                j--;
            }

            sortedList.set(j + 1, key);
        }

        return sortedList;
    }
}

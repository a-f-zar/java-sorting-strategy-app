package com.aston.sorting.context.strategy;

import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;

import java.util.Comparator;

public class SelectionSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public MyList<T> sort(MyList<T> list, Comparator<T> comparator) {

        MyList<T> sortedList = new CustomArrayList<>(list);

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

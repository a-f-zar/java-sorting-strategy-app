package com.aston.sorting.context.strategy;

import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;

import java.util.Comparator;

public class InsertionSortStrategy<T> implements SortingStrategy<T> {

    @Override
    public MyList<T> sort(MyList<T> list, Comparator<T> comparator) {

        MyList<T> sortedList = new CustomArrayList<>(list);

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

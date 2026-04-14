package com.aston.sorting.context.strategy;

import com.aston.models.custom.MyList;

import java.util.Comparator;

public interface SortingStrategy<T> {
    MyList<T> sort(MyList<T> inputList, Comparator<T> comparator);
}

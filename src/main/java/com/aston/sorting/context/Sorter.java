package com.aston.sorting.context;

import com.aston.models.custom.MyList;
import com.aston.sorting.context.strategy.SortingStrategy;

import java.util.Comparator;

public class Sorter<T> {
    private SortingStrategy<T> strategy;

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public MyList<T> sort(MyList<T> list, Comparator<T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        return strategy.sort(list, comparator);
    }
}

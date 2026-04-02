package com.aston.context;

import com.aston.context.strategy.SortingStrategy;

import java.util.Comparator;
import java.util.List;

public class Sorter<T> {
    private SortingStrategy<T> strategy;

    public void setStrategy(SortingStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }
        return strategy.sort(list, comparator);
    }
}

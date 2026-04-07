package com.aston.sorting.context.strategy;

import java.util.Comparator;
import java.util.List;

public interface SortingStrategy<T> {
    List<T> sort(List<T> inputList, Comparator<T> comparator);
}

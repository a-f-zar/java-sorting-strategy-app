package com.aston.sorting.context.strategy;

import com.aston.models.custom.CustomArrayList;
import com.aston.models.custom.MyList;

import java.util.Comparator;
import java.util.function.Function;

public class SortingEvenNumbersStrategy<T> implements SortingStrategy<T> {
    private final SortingStrategy<T> sorter;
    private final Function<T, Integer> numberExtractor;

    public SortingEvenNumbersStrategy(SortingStrategy<T> sorter, Function<T, Integer> numberExtractor) {
        this.sorter = sorter;
        this.numberExtractor = numberExtractor;
    }

    @Override
    public MyList<T> sort(MyList<T> inputList, Comparator<T> comparator) {
        MyList<T> list = new CustomArrayList<>(inputList);

        MyList<T> posEven = (MyList<T>) list.stream()
                .filter(i -> numberExtractor.apply(i) % 2 == 0)
                .toList();

        posEven = sorter.sort(posEven, comparator);

        int evenIndex = 0;

        for(int i = 0; i < list.size(); i++) {
            if(numberExtractor.apply(list.get(i)) % 2 == 0) {
                list.set(i, posEven.get(evenIndex));
                evenIndex++;
            }
        }
        return list;
    }
}

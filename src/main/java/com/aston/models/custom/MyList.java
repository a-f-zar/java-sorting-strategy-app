package com.aston.models.custom;

import java.util.stream.Stream;

public interface MyList<E> extends Iterable<E> {
    void add(E element);
    void addAll(MyList<E> otherList);
    void remove(E element);
    int size();
    E get(int index);
    void clear();
    Stream<E> stream();
    boolean isEmpty();
    boolean contains(E element);
    E set(int index, E element);
    Object[] toArray();
    MyList<E> subList(int fromIndex, int toIndex);
}
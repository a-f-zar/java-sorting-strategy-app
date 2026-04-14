package com.aston.models.custom;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomArrayList<E> implements MyList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public CustomArrayList(Collection<? extends E> collection) {
        this.elements = new Object[Math.max(DEFAULT_CAPACITY, collection.size())];
        this.size = 0;
        for (E e : collection) {
            add(e);
        }
    }

    public CustomArrayList (MyList<? extends E> myList){
        this.elements = new Object[Math.max(DEFAULT_CAPACITY, myList.size())];
        this.size = 0;
        for (E e : myList) {
            add(e);
        }
    }

    public static <E> CustomArrayList<E> of(E... elements) {
        CustomArrayList<E> list = new CustomArrayList<>();
        for(E e : elements){
            list.add(e);
        }
        return list;
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    @Override
    public void addAll(MyList<E> otherList) {
        for (E e : otherList) {
            add(e);
        }
    }

    @Override
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if(elements[i].equals(element)) {
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    System.arraycopy(elements, i + 1, elements, i, numMoved);
                }
                elements[--size] = null;
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (E) elements[index];
    }

    @Override
    public void clear() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }
            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (E) elements[cursor++];
            }
        };
    }

    @Override
    public Stream<E> stream() {
        return StreamSupport.stream(Spliterators.spliterator(iterator(), size, Spliterator.ORDERED), false);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = (E) elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public MyList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        CustomArrayList<E> sublist = new CustomArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add((E) elements[i]);
        }
        return sublist;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < size; i++) {
            result = 31 * result + Objects.hashCode(elements[i]);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CustomArrayList<?> other = (CustomArrayList<?>) obj;
        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(this.elements[i], other.elements[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}


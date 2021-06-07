package com.ua.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListImpl<T> extends AbstractList<T> {
    private int size;
    private int index;
    private Object[] arr;
    private final Object[] defautl = {};

    public ArrayListImpl() {
        this.arr = defautl;
    }

    public ArrayListImpl(int capacity) {
        this.arr = new Object[capacity];
    }


    @Override
    public void add(T item) {
        if (index == arr.length) {
            Object[] arr2 = new Object[arr.length + 25];
            System.arraycopy(arr, 0, arr2, 0, size);
            arr = arr2;
        }
        arr[index] = item;
        index++;
        size++;
    }

//    private void checkIndex(int index) {
//        if (index < 0 || index >= this.index)
//            throw new IllegalArgumentException();
//    }

//    private void growArray() {
//        Object[] newArray = new Object[arr.length * 2];
//        System.arraycopy(arr, 0, newArray, 0, index - 1);
//        arr = newArray;
//    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException();
        if (index == arr.length) {
            Object[] newArray = new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArray, 0, index - 1);
            arr = newArray;
        }
        System.arraycopy(arr, index, arr, index + 1, this.index - index);
        arr[index] = item;
        this.index++;
        size++;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException();
        arr[index] = item;
    }

    @Override
    public void remove(T item) {
        for (int i = 0; i < arr.length; i++) {
            Object ob = arr[i];
            if (ob.equals(item)) {
                int numMoved = size - i - 1;
                if (numMoved > 0) {
                    System.arraycopy(arr, i + 1, arr, i, numMoved);
                    size--;
                    arr[size] = null;
                }
                break;
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= this.index)
            throw new IllegalArgumentException();
        Object[] arr2 = arr;
        arr = new Object[arr2.length - 1];
        System.arraycopy(arr2, 0, arr, 0, index);
        int num = arr2.length - index - 1;
        System.arraycopy(arr2, index + 1, arr, index, num);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arr[i] = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        if (item == null) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == item) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == item) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) arr[index];
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(arr);
    }

    private static class IteratorImpl<T> implements Iterator<T> {
        private int index = 0;
        private T[] values;

        IteratorImpl(T[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public T next() {
            return values[index++];
        }

    }

    @Override
    public String toString() {
        if (arr == null)
            return "null";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == size - 1)
                return b.append(']').toString();
            b.append(", ");
        }
    }


    public static void main(String[] args) {
    }
}

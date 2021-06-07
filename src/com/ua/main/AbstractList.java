package com.ua.main;


public abstract class AbstractList<T> implements Iterable<T> {
    public abstract void add(T item);

    public abstract void add(int index, T item);

    public abstract void set(int index, T item);

    public abstract void remove(T item);

    public abstract void remove(int index);

    public abstract int size();

    public abstract void clear();

    public abstract boolean contains(T item);

    public abstract T get(int index);
}


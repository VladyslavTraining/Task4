package com.ua.main;

import java.util.Iterator;

public class CircularLinkedList<T> extends AbstractList<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size;

    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
        size++;
    }

    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.add("var1");
        list.add("var2");
        list.add("var3");
        list.add("var4");
        System.out.println(list);
    }

    @Override
    public void add(int index, T item) {

    }

    @Override
    public void set(int index, T item) {

    }

    @Override
    public void remove(T item) {

    }

    @Override
    public void remove(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        Node currentNode = head;
        if (head != null) {
            do {
                if (currentNode.data == item) {
                    return true;
                }
                currentNode = currentNode.next;
            } while (currentNode != head);
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        Node<T> t = head;

        @Override
        public boolean hasNext() {
            return t != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                System.out.println("have no more elements");
            T tmp = t.data;
            t = t.next;
            return tmp;
        }
    }

    static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }


    @Override
    public String toString() {
        Node<T> temp = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(temp.data).append(", ");
            temp = temp.next;
        }
        while (temp != head);
        return sb.toString();
    }
}



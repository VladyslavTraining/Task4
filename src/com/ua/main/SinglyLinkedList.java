package com.ua.main;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> extends AbstractList<T> {

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
    }

    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<T> cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(item);
        Node<T> cur = this.head;
        if (index == 0) {
            newNode.next = head;
            this.head = newNode;
            size++;
            return;
        }
        if (cur != null) {
            for (int i = 1; i < index; i++) {
                cur = cur.next;
            }
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }


    @Override
    public void set(int index, T item) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(item);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = newNode.data;
    }

    @Override
    public void remove(T item) {
        if (this.head.data == item) {
            this.head = this.head.next;
            size--;
            return;
        }
        Node<T> cur = this.head, prev = this.head;
        while (cur != null && cur.data != item) {
            prev = cur;
            cur = cur.next;
        }
        if (cur != null) {
            prev.next = cur.next;
            size--;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        if (this.head.data == item) {
            return true;
        }
        Node<T> cur = this.head;
        while (cur != null && cur.data != item) {
            cur = cur.next;
        }
        return cur != null;
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
                throw new NoSuchElementException();
            T tmp = t.data;
            t = t.next;
            return tmp;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> t = this.head;
        while (t != null) {
            if (t.next == null) {
                sb.append(t.data);
                return String.format("[%s]", sb);
            }
            sb.append(t.data).append(", ");
            t = t.next;
        }
        return String.format("[%s]", sb);
    }


    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("var1");
        list.add("var2");
        list.add("var3");
        list.add("var4");
        list.add("var5");
        System.out.println(list);
        for (String s : list) {
            System.out.println(s);
        }
    }
}

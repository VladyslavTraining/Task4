package com.ua.main;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {

    }


    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(T element) {
            this.element = element;
        }
    }

    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = tail = newNode;
            head.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = null;
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(item);
        Node current = head;
        if (index == 0) {
            if (size == 0) {
                tail = newNode;
            } else {
                head.prev = newNode;
            }
            newNode.next = head;
            head = newNode;
            size++;
        } else if (index == size) {
            Node newNode2 = new Node(item);
            if (size == 0) {
                head = newNode2;
            } else {
                tail.next = newNode2;
                newNode2.prev = tail;
            }
            tail = newNode2;
            size++;
        } else {
            for (int j = 0; j < index && current.next != null; j++) {
                current = current.next;
            }
            newNode.next = current;
            current.prev.next = newNode;
            newNode.prev = current.prev;
            current.prev = newNode;
            size++;
        }
    }

    @Override
    public void set(int index, T item) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node(item);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = newNode.element;

    }

    @Override
    public void remove(T item) {
        if (head.element.equals(item)) {
            head = head.next;
            size--;
            return;
        }
        Node<T> current = head;
        while (current != null && !current.element.equals(item))
            current = current.next;
        if (current == null)
            throw new NoSuchElementException();
        if (current.next != null)
            current.next.prev = current.prev;
        current.prev.next = current.next;
        size--;
    }

    @Override
    public void remove(int index) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
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
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T item) {
        if (this.head.element == item) {
            return true;
        }
        Node cur = this.head;
        while (cur != null && cur.element != item) {
            cur = cur.next;
        }
        if (cur != null) {
            return true;
        } else return false;
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
        return current.element;
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
            T tmp = t.element;
            t = t.next;
            return tmp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> t = this.head;
        while (t != null) {
            if (t == tail) {
                sb.append(t.element);
            } else {
                sb.append(t.element).append(", ");
            }
            t = t.next;
        }
        return String.format("[%s]", sb);
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.add("var1");
        list.add("var2");
        list.add("var3");
        list.add(3, "var0");
        System.out.println(list);
        System.out.println(list.contains("var4"));
    }
}

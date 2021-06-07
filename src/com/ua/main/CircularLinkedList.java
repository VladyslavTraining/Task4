package com.ua.main;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> extends AbstractList<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size;

    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.add("var1");
        list.add("var2");
        list.add("var3");
        list.add("var4");
        list.remove("var3");
        System.out.println(list.size());
        System.out.println(list);
    }

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

    @Override
    public void add(int index, T item) {
        Node newNode = new Node(item);
        Node temp = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            if (head == null) {
                head = newNode;
                head.next = head;
                head.prev = head;
            } else {
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = newNode;
                newNode.prev = temp;
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else {

            temp = head;
            for (int i = 0; i < index - 1; i++)
                temp = temp.next;
            newNode.next = temp.next;
            newNode.next.prev = newNode;
            newNode.prev = temp;
            temp.next = newNode;
        }
        size++;
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
        current.data = newNode.data;
    }

    @Override
    public void remove(T item) {
        int pos = 0;
        if (head == null) {
            System.out.println("list is empty");
        }
        if (head.data == item) {
            if (head.next != head) {
                Node newNode = null;
                newNode = head;
                while (newNode.next != head) {
                    newNode = newNode.next;
                }
                newNode.next = head.next;
                head = head.next;
            } else
                head = null;
            System.out.println("list is empty");
        } else if (head.data != item) {
            System.out.println("not found");
        }
        Node current = head;
        while (current.next != head && current.data != item) {
            tail = current;
            current = current.next;
        }
        if (current.data == item) {
            tail.next = tail.next.next;
        }
    }
//        Node<T> currentNode = this.head;
//        while (currentNode != null) {
//            if (currentNode.data.equals(item)) {
//                head = currentNode.next;
//                tail.next = currentNode.next;
//                size--;
//                break;
//            } else {
//                currentNode = currentNode.next;
//                size--;
//            }


//        if (head.data.equals(item)) {
//            head = head.next;
//            tail.next = head;
//            size--;
//            return;
//        }
//        Node<T> current = head;
//        int i = 0;
//        while (!current.data.equals(item)) {
//            current = current.next;
//        }
//        if (i == size)
//            throw new NoSuchElementException();
//        current = current.next;
//        current.prev = current.next;
//        size--;


    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            tail.next = head;
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



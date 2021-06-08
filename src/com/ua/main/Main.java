package com.ua.main;


import java.util.*;

public class Main {


    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        Comparator<String> compareString = Comparator.naturalOrder();

        List<String> stringList = Arrays.asList("Beta", "Alpha", "Delta", "Gamma");
        System.out.println(stringList);
        stringList.sort(compareString);
        System.out.println(stringList + System.lineSeparator());

        List<Integer> list = Arrays.asList(11, 20, 5, 20);
        System.out.println(list);
        list.sort(comparator);
        System.out.println(list + System.lineSeparator());

        Set setInteger = new TreeSet(list);
        System.out.println(setInteger + System.lineSeparator());

        getQueueView();

        getStackView();
    }

    private static void getQueueView() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.poll();
        System.out.println(queue + System.lineSeparator());
    }

    private static void getStackView() {
        Stack<Integer> deque = new Stack<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        System.out.println(deque);
        deque.pop();
        System.out.println(deque);
        deque.pop();
        System.out.println(deque);
        deque.pop();
        System.out.println(deque);
    }


}

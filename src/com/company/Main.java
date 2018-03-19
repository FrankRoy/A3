package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {

        //Instantiate my heap class with a starting state of MAX_HEAP
        HeapPriorityQueue<Integer, String> heapPQ = new HeapPriorityQueue<Integer, String>(HeapPriorityState.Max);

        //Add
        Random rand = new Random();
        System.out.println("--------------------------------------------------------");
        System.out.println("Elements inserted are under MAX_HEAP state");
        System.out.println("--------------------------------------------------------");
        for (int i =0; i < 25; i++){
            System.out.println("Element " + i + " inserted: " + heapPQ.insert(rand.nextInt(1000 - 10 + 1), "3"));
        }

        //See if the heap is empty
        System.out.println(heapPQ.isEmpty());

        //See the top element
        System.out.println("The top element is: " + heapPQ.top());

        //See the current state
        System.out.println(heapPQ.state());

        //Try to toggle to min
        heapPQ.toggle();
        System.out.println(heapPQ.state());

        //Try to toggle back to max
        heapPQ.toggle();
        System.out.println(heapPQ.state());

        //Try min switch state
        heapPQ.switchToMin();

        //See if it changes anything
        heapPQ.switchToMin();

        //Test max switch
        heapPQ.switchToMax();

        //See if it changes anything
        heapPQ.switchToMax();

        System.out.println("--------------------------------------------------------");
        System.out.println("Elements removed are under MAX_HEAP state");
        System.out.println("--------------------------------------------------------");

        //Remove all elements (removes them in the order of the current state
        int count = 0;
        while (!heapPQ.isEmpty())
            System.out.println("Element: " + (count++) + " " + heapPQ.remove() + " | Size of heap: " + heapPQ.size());

        //See if the heap is empty after removing all elements
        System.out.println(heapPQ.isEmpty());

        System.out.println("--------------------------------------------------------");
        System.out.println("Elements inserted are under MIN_HEAP state");
        System.out.println("--------------------------------------------------------");




        //Try min switch state
        heapPQ.switchToMin();

        for (int i =0; i < 25; i++){
            System.out.println("Element inserted: " + i + " " + heapPQ.insert(rand.nextInt(1000 - 10 + 1), "3"));
        }

        System.out.println("--------------------------------------------------------");
        System.out.println("Elements removed are under MIN_HEAP state");
        System.out.println("--------------------------------------------------------");
        count = 0;
        while (!heapPQ.isEmpty())
            System.out.println("Element: " + (count++) + " " + heapPQ.remove() + " | Size of heap: " + heapPQ.size());
    }
}

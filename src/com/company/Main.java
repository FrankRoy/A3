package com.company;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
	// write your code here
        HeapPriorityQueue<Integer, String> heapPQ = new HeapPriorityQueue<Integer, String>(State.Max);

        //Add 12 elements to the Heap
        heapPQ.insert(1,"2");
        heapPQ.insert(-5,"4");
        heapPQ.insert(2,"10");
        heapPQ.insert(5,"11");
        heapPQ.insert(9,"13");
        heapPQ.insert(2,"15");
        heapPQ.insert(9,"37");
        heapPQ.insert(4,"4");
        heapPQ.insert(3,"20");
        heapPQ.insert(25,"4");
        heapPQ.insert(33,"4");
        heapPQ.insert(0,"4");
        heapPQ.insert(-2,"4");
        heapPQ.insert(100,"4");

        //See if the heap is empty
        System.out.println(heapPQ.isEmpty());

        //See the top element
        System.out.println(heapPQ.top());

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

        //Remove all elements (removes them in the order of the current state
        int i = 0;
        while (!heapPQ.isEmpty())
            System.out.println("Element: " + (i++) + " " + heapPQ.remove() + " | Size of heap: " + heapPQ.size());

        //See if the heap is empty after removing all elements
        System.out.println(heapPQ.isEmpty());
    }
}

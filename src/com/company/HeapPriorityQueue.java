package com.company;

import javafx.util.Pair;

public class HeapPriorityQueue <K extends Comparable<K>, V> {
//**************************************************************************//
//                              Private Variables                           //
//**************************************************************************//

    private final int INITIAL_SIZE = 20;
    private Pair<K,V> data[];

    private int size = 0;
    private HeapPriorityState currentHeapPriorityState;

    HeapPriorityQueue(HeapPriorityState state){
      data  = new Pair[INITIAL_SIZE];
      currentHeapPriorityState = state;
    }


//**************************************************************************//
//                              Private Methods                             //
//**************************************************************************//
    private int checkKeyExist(K key){
        for (int i =0; i < this.size; i++){

            //Key exists
            if (data[i].getKey().compareTo(key) == 0) {
                return i;
            }
        }
        //-1: key was not found
        return -1;
    }


    private void swap(int p1, int p2) {
        //Swap data[index] with biggest child
        Pair<K, V> temp;
        temp = this.data[p1];
        this.data[p1] = this.data[p2];
        this.data[p2] = temp;
    }

    private void buildHeap(){
        for (int i = (this.size / 2) - 1; i >= 0; i--){
            heapify(i);
        }
    }

    //Arrange the "binary tree" into the heap depending on the current heap state
    private void heapify(int index)  {

        int largestORSmallestOnCurrentState = index;
        int l = left(index);
        int r = right(index);

        if (l <= this.size - 1 && data[l].getKey().compareTo(data[index].getKey()) == this.currentHeapPriorityState.getValue()) {
            largestORSmallestOnCurrentState = l;
        }

        if (r <= this.size - 1 && data[r].getKey().compareTo(data[largestORSmallestOnCurrentState].getKey()) == this.currentHeapPriorityState.getValue()) {
            largestORSmallestOnCurrentState = r;
        }

        //Not root
        if (largestORSmallestOnCurrentState != index){
            swap(index, largestORSmallestOnCurrentState);
            heapify(largestORSmallestOnCurrentState);
        }
    }

    //Moves the entry at index higher, if necessary, to restore the heap property
    private void upHeap(int index) {
        if (index != 0){
            int p = parent(index);

            //Check heap property
            if (this.data[index].getKey().compareTo(this.data[p].getKey()) == currentHeapPriorityState.getValue()){
                swap(index,p);
                index = p;
                upHeap(index);
            }

        }
    }

    //Moves the entry at index lower, if necessary, to restore the heap property
    private void downHeap(int index){

        int leftIndex = left(index);
        int rightIndex = right(index);

        int smallChildIndex;

        if (!hasRight(index))
        {
            //No more children
            if (!hasLeft(index)){
                return;
            }
            else{
                smallChildIndex = leftIndex;
            }
        }else{
            if (this.data[leftIndex].getKey().compareTo(this.data[rightIndex].getKey()) == currentHeapPriorityState.getValue()){
                smallChildIndex = leftIndex;
            }
            else{
                smallChildIndex = rightIndex;
            }
        }

        //Swap the parent with the bigger or smaller children depending on the current state
        if (this.data[index].getKey().compareTo(this.data[smallChildIndex].getKey()) != currentHeapPriorityState.getValue()) {
            swap(index, smallChildIndex);
            downHeap(smallChildIndex);
        }

    }

    //Return the child left element of i
    private int left(int i){
        return 2*i + 1;
    }

    //Check if a left child exists for i
    private boolean hasLeft(int i){
        return left(i) < this.size;
    }

    //Return the child right element of i
    private int right(int i){
        return 2*i + 2;
    }

    //Check if a right child exists for i
    private boolean hasRight(int i){
        return right(i) < this.size;
    }

    private int parent(int i){
        return (int) Math.floor(i / 2);
    }

//**************************************************************************//
//                              Public Methods                              //
//**************************************************************************//

    //Build the array as such that the largest or smallest element is positioned at root
    // and every element under each parent is smaller or bigger depending on the chosen state (MIN/MAX).
    public void heapSort() {

        //Build a valid MIN/MAX heap so the root element is the biggest/smallest respectively.
        buildHeap();

        for (int i = this.size - 1; i >= 0; i--){
            //Swap root with the last element
            swap(0,i);
            //this.size-= 1;
            heapify(0);
        }
    }

    //Remove the root element then restores the heap property
    public Pair<K,V> remove() throws IllegalAccessException {
        if (!isEmpty()) {
            Pair<K,V> temp = this.data[0];
            this.data[0] = this.data[this.size - 1];
            size--;

            //Fix the heap property
            if (this.size > 0)
                downHeap(0);

            return temp;
        }else{
            throw new IllegalAccessException("The array is empty.");
        }

    }

    //Insert an element in the priority queue and place it at the proper position so it follows the heap property
    public Pair<K,V> insert(K key,V value){

        int position = checkKeyExist(key);
        Pair<K,V> newest = new Pair<>(key,value);

        //Replace the current value of the key
        if (position != -1){
            data[position] = newest;
        }else
        {
            //Array is full: extend the array
            if (data.length == this.size){
                Pair<K,V> temp[] = new Pair[size * 2];

                int i;
                for (i = 0; i < this.size; i++){
                    temp[i] = this.data[i];
                }

                //Add new element at the end
                temp[i] = newest;
                this.size++;
                data = temp;
            }
            //Add element at last open spot, then increase size
            else{
                data[size++] = newest;
            }

            if (this.size > 0)
                upHeap(this.size - 1);
        }
        return newest;
    }

    public Pair<K,V> top() throws IllegalAccessException {

        if (!isEmpty())
            return data[0];
        else
            throw new IllegalAccessException();
    }

    public void toggle() {
        if (currentHeapPriorityState == HeapPriorityState.Max) {
            currentHeapPriorityState = HeapPriorityState.Min;
            buildHeap();
        }else{
            currentHeapPriorityState = HeapPriorityState.Max;
            buildHeap();
        }
    }

    public void switchToMin(){
        if (this.currentHeapPriorityState == HeapPriorityState.Min)
            return;
        else
            toggle();
    }

    public void switchToMax(){
        if (this.currentHeapPriorityState == HeapPriorityState.Max)
            return;
        else
            toggle();
    }

    public HeapPriorityState state(){
        return this.currentHeapPriorityState;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return this.size;
    }

}

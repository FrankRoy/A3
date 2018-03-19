package com.company;

enum HeapPriorityState {
   Min(-1), Max(1);

   private int value;

   HeapPriorityState(int value){
       this.value = value;
   }

    public int getValue() {
        return value;
    }

}

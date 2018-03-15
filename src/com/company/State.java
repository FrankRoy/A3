package com.company;

enum State {
   Min(-1), Max(1);

   private int value;

   State(int value){
       this.value = value;
   }

    public int getValue() {
        return value;
    }

}

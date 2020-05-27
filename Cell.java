package com.company;

class Cell{
    public int state;
    public double time;
    public int grainID;
    public int x;
    public int y;

    public Cell(){
        state = 0;
        time = 0;
        grainID = -1;
    }
    public Cell(int state, int grainID){
        this.state = state;
        this.grainID = grainID;
    }
    public void changeState(){
        state++;
    }
}
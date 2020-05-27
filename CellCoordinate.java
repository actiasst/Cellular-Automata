package com.company;

public class CellCoordinate {
    public int x;
    public int y;
    public CellCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isAlready(int x, int y){
        if(this.x == x && this.y == y) {
            return true;
        }
        else {
            return false;
        }
    }
}

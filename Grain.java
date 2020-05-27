package com.company;

class Grain{
    static int GrainID = 0;
    public int ID;
    public int posX;
    public int posY;
    public int R;
    public int G;
    public int B;
    public boolean circle;
    public double side1;
    public double side2;
    public double angle;

    public Grain(int posX, int posY, int r, int g, int b, boolean circle, double side1, double side2, double angle) {
        this.posX = posX;
        this.posY = posY;
        this.circle = circle;
        this.side1 = side1;
        this.side2 = side2;
        this.angle = angle;
        R = r;
        G = g;
        B = b;
        ID = GrainID;
        GrainID++;
    }
}

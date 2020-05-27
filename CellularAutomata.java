package com.company;

import java.util.LinkedList;
import java.util.Random;

public class CellularAutomata {
    public final int numberOfCells;
    public Cell[][] cells;
    LinkedList<Grain> grains = new LinkedList<Grain>();
    private double cellularTime;

    public CellularAutomata(int numberOfCells) {
        this.numberOfCells = numberOfCells;
        cells = new Cell[numberOfCells][numberOfCells];
        for(int i = 0; i < numberOfCells; i++){
            for(int j = 0; j < numberOfCells; j++){
                cells[i][j] = new Cell();
            }
        }
        cellularTime = 0;
    }
    public int calculate(){
        int tmpID;
        for(int i = 1; i < numberOfCells - 1; i++){
            for(int j = 1; j < numberOfCells - 1; j++){
                if(cells[i][j].state == 0){
                    if(cells[i-1][j].state == 2){
                        tmpID = cells[i-1][j].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i+1][j].state == 2){
                        tmpID = cells[i+1][j].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i-1][j-1].state == 2){
                        tmpID = cells[i-1][j-1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i][j-1].state == 2){
                        tmpID = cells[i][j-1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i+1][j-1].state == 2){
                        tmpID = cells[i+1][j-1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i-1][j+1].state == 2){
                        tmpID = cells[i-1][j+1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i][j+1].state == 2){
                        tmpID = cells[i][j+1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                    else if(cells[i+1][j+1].state == 2){
                        tmpID = cells[i+1][j+1].grainID;
                        cells[i][j].grainID = tmpID;
                        if(grains.get(tmpID).circle){
                            cells[i][j].time = timeCircle(i,j,grains.get(tmpID).posX,grains.get(tmpID).posY);
                        }
                        else {
                            cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                    grains.get(tmpID).angle, i, j,
                                    grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                        }
                        cells[i][j].changeState();
                    }
                }
            }
        }
        afterCalculate();
        cellularTime += 0.7;
        return 0;
    }
    public void afterCalculate(){
        for(int i = 1; i < numberOfCells - 1; i++){
            for(int j = 1; j < numberOfCells - 1; j++){
                if(cells[i][j].state == 1 && cells[i][j].time <= cellularTime){
                    cells[i][j].changeState();
                }
            }
        }
    }
    public void addGrain(){
        grains.add(new Grain(250,250,255,0,0, true, 0, 0, 0));
        cells[250][250].changeState();
        cells[250][250].changeState();
        cells[250][250].grainID = grains.getLast().ID;

        grains.add(new Grain(75,75,0,255,0, false, 1, 2, 30));
        cells[75][75].changeState();
        cells[75][75].changeState();
        cells[75][75].grainID = grains.getLast().ID;

        grains.add(new Grain(400,400,0,0,255, false, 1, 1, 45));
        cells[400][400].changeState();
        cells[400][400].changeState();
        cells[400][400].grainID = grains.getLast().ID;

        grains.add(new Grain(120,300,255,0,255, false, 2, 1, 7));
        cells[120][300].changeState();
        cells[120][300].changeState();
        cells[120][300].grainID = grains.getLast().ID;
    }

    public void addRandomGrain(){
        Random random = new Random();
        int x, y;
        while(true) {
            x = random.nextInt(numberOfCells - 2) + 1;
            y = random.nextInt(numberOfCells - 2) + 1;
            if(cells[x][y].state == 0){
                break;
            }
        }
        if(random.nextInt(2) == 0){
            grains.add(new Grain(x, y, random.nextInt(256), random.nextInt(256), random.nextInt(256),
                    true,0,0,0));
            cells[x][y].changeState();
            cells[x][y].changeState();
            cells[x][y].grainID = grains.getLast().ID;
        }
        else{
            grains.add(new Grain(x, y, random.nextInt(256), random.nextInt(256), random.nextInt(256),
                    false,random.nextInt(4) + 1,random.nextInt(4) + 1,random.nextInt(91)));
            cells[x][y].changeState();
            cells[x][y].changeState();
            cells[x][y].grainID = grains.getLast().ID;
        }
    }

    private double timeCircle(double ic, double jc, double ig, double jg){
        return Math.sqrt((Math.pow(ic-ig, 2) + Math.pow(jc-jg, 2)));
    }

    private double timeRectangle(double side1, double side2, double angle, double i, double j, double posX, double posY){
        double rgx, rgy;
        double rlx, rly;
        double[][] RGL = new double[2][2];
        double vi = side1 / Math.max(side1,side2);
        double vj = side2 / Math.max(side1,side2);
        RGL[0][0] = Math.cos(Math.toRadians(angle));
        RGL[0][1] = -(Math.sin(Math.toRadians(angle)));
        RGL[1][0] = Math.sin(Math.toRadians(angle));
        RGL[1][1] = Math.cos(Math.toRadians(angle));

        rgx = i - posX;
        rgy = j - posY;
        rlx = RGL[0][0] * rgx + RGL[0][1] * rgy;
        rly = RGL[1][0] * rgx + RGL[1][1] * rgy;
        if(Math.abs(rlx/rly) < side1 / side2){
            return Math.abs(rly / vj);
        }
        else{
            return Math.abs(rlx / vi);
        }
    }
}

class Cell{
    public int state;
    public double time;
    public int grainID;

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
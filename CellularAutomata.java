package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class CellularAutomata {
    public final int numberOfCells;
    public Cell[][] cells;
    LinkedList<Grain> grains = new LinkedList<Grain>();
    private double cellularTime;
    private ArrayList<CellCoordinate> availableCellsList = new ArrayList<CellCoordinate>();
    private ArrayList<CellCoordinate> midStateCellsList = new ArrayList<CellCoordinate>();
    private int counter;
    private boolean continuousAddingGrains;

    public CellularAutomata(int numberOfCells, boolean continuousAddingGrains) {
        this.numberOfCells = numberOfCells;
        this.continuousAddingGrains = continuousAddingGrains;
        cells = new Cell[numberOfCells][numberOfCells];
        for(int i = 0; i < numberOfCells; i++){
            for(int j = 0; j < numberOfCells; j++){
                cells[i][j] = new Cell();
            }
        }
        cellularTime = 0;
        counter = 0;
    }

    public int modulo(int index){
        return Math.floorMod(index + numberOfCells, numberOfCells);
    }

    public int calculate(){
        if(continuousAddingGrains && (counter % 30 == 0) && (availableCellsList.size() != 0) && (midStateCellsList.size() != 0)){
            addRandomGrain();
        }
        int i, j;
        int tmpID;
        for(int k = 0; k < availableCellsList.size(); k++) {
            i = availableCellsList.get(k).x;
            j = availableCellsList.get(k).y;
            if(cells[i][j].state == 0){
                if(cells[modulo(i-1)][j].state == 3){
                    cells[i][j].x = cells[modulo(i-1)][j].x + 1;
                    cells[i][j].y = cells[modulo(i-1)][j].y;
                    tmpID = cells[modulo(i-1)][j].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[modulo(i + 1)][j].state == 3){
                    cells[i][j].x = cells[modulo(i + 1)][j].x - 1;
                    cells[i][j].y = cells[modulo(i + 1)][j].y;
                    tmpID = cells[modulo(i + 1)][j].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[modulo(i-1)][modulo(j-1)].state == 3){
                    cells[i][j].x = cells[modulo(i-1)][modulo(j-1)].x + 1;
                    cells[i][j].y = cells[modulo(i-1)][modulo(j-1)].y + 1;
                    tmpID = cells[modulo(i-1)][modulo(j-1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[i][modulo(j-1)].state == 3){
                    cells[i][j].x = cells[i][modulo(j-1)].x;
                    cells[i][j].y = cells[i][modulo(j-1)].y + 1;
                    tmpID = cells[i][modulo(j-1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[modulo(i+1)][modulo(j-1)].state == 3){
                    cells[i][j].x = cells[modulo(i+1)][modulo(j-1)].x - 1;
                    cells[i][j].y = cells[modulo(i+1)][modulo(j-1)].y + 1;
                    tmpID = cells[modulo(i+1)][modulo(j-1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[modulo(i-1)][modulo(j+1)].state == 3){
                    cells[i][j].x = cells[modulo(i-1)][modulo(j+1)].x + 1;
                    cells[i][j].y = cells[modulo(i-1)][modulo(j+1)].y - 1;
                    tmpID = cells[modulo(i-1)][modulo(j+1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[i][modulo(j+1)].state == 3){
                    cells[i][j].x = cells[i][modulo(j+1)].x;
                    cells[i][j].y = cells[i][modulo(j+1)].y - 1;
                    tmpID = cells[i][modulo(j+1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
                else if(cells[modulo(i+1)][modulo(j+1)].state == 3){
                    cells[i][j].x = cells[modulo(i+1)][modulo(j+1)].x - 1;
                    cells[i][j].y = cells[modulo(i+1)][modulo(j+1)].y - 1;
                    tmpID = cells[modulo(i+1)][modulo(j+1)].grainID;
                    cells[i][j].grainID = tmpID;
                    if(grains.get(tmpID).circle){
                        cells[i][j].time = timeCircle(cells[i][j].x,cells[i][j].y,grains.get(tmpID).posX,grains.get(tmpID).posY);
                    }
                    else {
                        cells[i][j].time = timeRectangle(grains.get(tmpID).side1, grains.get(tmpID).side2,
                                grains.get(tmpID).angle, cells[i][j].x, cells[i][j].y,
                                grains.get(cells[i][j].grainID).posX, grains.get(cells[i][j].grainID).posY);
                    }
                    cells[i][j].changeState();
                    availableCellsList.remove(k);
                    k--;
                    midStateCellsList.add(new CellCoordinate(i,j));
                }
            }
        }
        afterCalculate();
        cellularTime += 0.7;
        counter++;
        if(midStateCellsList.size() == 0 && availableCellsList.size() == 0) {
            return 0;
        }
        else{
            return 1;
        }
    }
    public void afterCalculate(){
        int i, j;
        for(int k = 0; k < midStateCellsList.size(); k++){
            i = midStateCellsList.get(k).x;
            j = midStateCellsList.get(k).y;
                if(cells[i][j].state == 1 && cells[i][j].time <= cellularTime){
                    cells[i][j].changeState();
                    addNeighbours(i,j);
                    cells[i][j].changeState();
                    midStateCellsList.remove(k);
                    k--;
                    if(cells[modulo(i+1)][j].grainID != -1) {
                        if (cells[modulo(i+1)][j].grainID != cells[i][j].grainID) {
                            cells[i][j].changeState();
                            continue;
                        }
                    }
                    if(cells[modulo(i-1)][j].grainID != -1) {
                        if (cells[modulo(i-1)][j].grainID != cells[i][j].grainID) {
                            cells[i][j].changeState();
                            continue;
                        }
                    }
                    if(cells[i][modulo(j+1)].grainID != -1) {
                        if (cells[i][modulo(j+1)].grainID != cells[i][j].grainID) {
                            cells[i][j].changeState();
                            continue;
                        }
                    }
                    if(cells[i][modulo(j-1)].grainID != -1) {
                        if (cells[i][modulo(j-1)].grainID != cells[i][j].grainID) {
                            cells[i][j].changeState();
                            continue;
                        }
                    }
                }
        }
    }

    public void addGrain(){
        int x = 99;
        int y = 99;
        grains.add(new Grain(x, y, 255, 0, 0,
                true,0,0,0));
        cells[x][y].changeState();
        cells[x][y].changeState();
        cells[x][y].changeState();
        cells[x][y].grainID = grains.getLast().ID;
        addNeighbours(x,y);
        cells[x][y].x = x;
        cells[x][y].y = y;
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
            cells[x][y].changeState();
            cells[x][y].grainID = grains.getLast().ID;
        }
        else{
            grains.add(new Grain(x, y, random.nextInt(256), random.nextInt(256), random.nextInt(256),
                    false,random.nextInt(4) + 1,random.nextInt(4) + 1,random.nextInt(91)));
            cells[x][y].changeState();
            cells[x][y].changeState();
            cells[x][y].changeState();
            cells[x][y].grainID = grains.getLast().ID;
        }
        addNeighbours(x,y);
        cells[x][y].x = x;
        cells[x][y].y = y;
    }

    private void addNeighbours(int x, int y){
        boolean flag;
        int tmpX, tmpY;
        for(int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++){
                if(!(i == 0 && j == 0)){
                    tmpX = modulo(x + i);
                    tmpY = modulo(y + j);
                    flag = false;
                    for(int k = 0; k < availableCellsList.size(); k++){
                        if(availableCellsList.get(k).isAlready(tmpX,tmpY)) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag){
                        continue;
                    }
                    if(cells[tmpX][tmpY].state != 0){
                        continue;
                    }
//                    if(x + i == 0){
//                        continue;
//                    }
//                    if(x + i == numberOfCells-1){
//                        continue;
//                    }
//                    if(y + j == 0){
//                        continue;
//                    }
//                    if(y + j == numberOfCells-1){
//                        continue;
//                    }
                    availableCellsList.add(new CellCoordinate(tmpX, tmpY));
                }
            }
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
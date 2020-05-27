package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;


public class Main {

    public static void main(String[] args) {
        CanvasExample canvasExample = new CanvasExample();
    }
}

class CanvasExample extends WindowAdapter
{
    Frame f= new Frame("Naiwny rozrost ziarna");
    Button button1 = new Button("1 Krok");
    Button button10 = new Button("10 Krok");
    Button button100 = new Button("100 Krok");
    Button button1000 = new Button("Cala symulacja");
    MyCanvas canvas = new MyCanvas();
    public CanvasExample()
    {
        f.setBackground(Color.gray);
        button1.setBounds(30, 900, 100, 20);
        button10.setBounds(150, 900, 100, 20);
        button100.setBounds(270, 900, 100, 20);
        button1000.setBounds(390, 900, 100, 20);
        f.addWindowListener(this);
        f.add(canvas);
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.calculate1();
            }
        });
        button10.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.calculate10();
            }
        });
        button100.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.calculate100();
            }
        });
        button1000.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.calculate1000();
            }
        });
        f.add(button1);
        f.add(button10);
        f.add(button100);
        f.add(button1000);
        f.setLayout(null);
        f.setSize(900, 950);
        f.setVisible(true);
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }
}
class MyCanvas extends Canvas
{
    CellularAutomata cellularAutomata = new CellularAutomata(800,false);
    public MyCanvas() {
        setSize(900, 900);
        for(int i = 0; i < 100; i++){
            cellularAutomata.addRandomGrain();
        }
    }
    public void paint(Graphics g)
    {
        Color[] colorTable = new Color[cellularAutomata.grains.size()];
        for(int i = 0; i < cellularAutomata.grains.size(); i++){
            colorTable[i] = new Color(cellularAutomata.grains.get(i).R,
                    cellularAutomata.grains.get(i).G,
                    cellularAutomata.grains.get(i).B);
        }
        for(int i = 1; i < cellularAutomata.numberOfCells - 1; i++) {
            for(int j = 1; j < cellularAutomata.numberOfCells - 1; j++) {
                if(cellularAutomata.cells[i][j].state == 3) {
                    g.setColor(colorTable[cellularAutomata.cells[i][j].grainID]);
                    g.fillRect(i + 50,j + 50,1,1);
                }
                else if(cellularAutomata.cells[i][j].state == 4){
                    g.setColor(Color.black);
                    g.fillRect(i + 50,j + 50,1,1);
                }
            }
        }
    }
    public void calculate1() {
        for (int i = 0; i < 1; i++) {
            cellularAutomata.calculate();
        }
        repaint();
    }
    public void calculate10() {
        for (int i = 0; i < 10; i++) {
            cellularAutomata.calculate();
        }
        repaint();
    }
    public void calculate100() {
        for (int i = 0; i < 100; i++) {
            cellularAutomata.calculate();
        }
        repaint();
    }
    public void calculate1000() {
        while(true){
            if(cellularAutomata.calculate() == 0){
                break;
            }
        }
        repaint();
    }
}
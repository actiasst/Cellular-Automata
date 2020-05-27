package com.company;

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
    Frame f= new Frame("Canvas Example");
    Button button = new Button("xD");
    MyCanvas canvas = new MyCanvas();
    final TextField tf=new TextField();
    public CanvasExample()
    {
        f.setBackground(Color.gray);
        button.setBounds(10, 900, 100, 20);
        tf.setBounds(50,50, 150,20);
        f.addWindowListener(this);
        f.add(canvas);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.calculate();
            }
        });
        f.add(button);
        f.add(tf);
        f.setLayout(null);
        f.setSize(1200, 1000);
        f.setVisible(true);
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }
}
class MyCanvas extends Canvas
{
    CellularAutomata cellularAutomata = new CellularAutomata(800);
    public MyCanvas() {
        setSize(800, 800);
        //cellularAutomata.addGrain();
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
                if(cellularAutomata.cells[i][j].state==2) {
                    g.setColor(colorTable[cellularAutomata.cells[i][j].grainID]);
                    g.fillRect(i + 50,j + 50,1,1);
                }
            }
        }
    }
    public int calculate() {
        for (int i = 0; i < 100; i++) {
            cellularAutomata.calculate();
        }
        repaint();
        return 0;
    }
}
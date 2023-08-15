package com.practice.misc;

import com.practice.MyTechHub;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class August1523 extends JFrame implements MyTechHub {

    private int x;
    private int y;
    private int width;
    private int height;
    private List<LinePoint> linePointList;
    public August1523(int x,int y,int width, int height) {
        this.x = x;
        this.y = y;
        setTitle("Flag of India by MyTechHub3");
        setSize(width,height);
        setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        linePointList = new ArrayList<>();
        populateLinePoints();
    }











    private void populateLinePoints(){
        linePointList.add(new LinePoint(255,245,340,245));
        linePointList.add(new LinePoint(295,205,295,290));
        linePointList.add(new LinePoint(260,275,330,215));
        linePointList.add(new LinePoint(260,215,330,275));
        linePointList.add(new LinePoint(257,260,335,230));
        linePointList.add(new LinePoint(257,230,335,260));
        linePointList.add(new LinePoint(260,215,330,275));
        linePointList.add(new LinePoint(260,215,330,275));

        linePointList.add(new LinePoint(275,280,315,212));
        linePointList.add(new LinePoint(275,212,315,280));
        linePointList.add(new LinePoint(283,285,308,208));
        linePointList.add(new LinePoint(283,208,308,285));
        linePointList.add(new LinePoint(257,252,337,238));
        linePointList.add(new LinePoint(257,238,337,252));
    }

    @Override
    public void paint(Graphics g) {
        drawing(g);
    }

    public void drawing(Graphics g) {
        x=100;
        y=100;
        width = 400;
        height = 100;
        drawRectangle(g, Color.ORANGE);

        y += 100;
        drawRectangle(g, Color.WHITE);

        y += 100;
        drawRectangle(g, Color.GREEN);

        g.setColor(Color.BLUE);
        for (int i = 0; i <= 360; i++) {
            delay(10);
            g.setColor(Color.BLUE);
            g.drawArc(250, 200, 95,95,
                    0, i);
            g.fillArc(250, 200, 95,95,
                    0, i);

            g.setColor(Color.WHITE);
            g.drawArc(255, 205, 83, 83,
                    0, i);
            g.fillArc(255, 205, 83, 83,
                    0, i);

            g.setColor(Color.BLUE);
            g.fillArc(287, 237, 20, 20,
                    0, i);
            g.setColor(Color.WHITE);
        }
        drawLines(g);
    }

    private void drawLines(Graphics g){
        linePointList.forEach(linePoint -> {
            g.setColor(Color.MAGENTA);
            g.drawLine(linePoint.getX1(),linePoint.getY1(),linePoint.getX2(),linePoint.getY2());
            delay(50);
            g.setColor(Color.BLUE);
            g.drawLine(linePoint.getX1(),linePoint.getY1(),linePoint.getX2(),linePoint.getY2());
        });
    }

    private void drawRectangle(Graphics g, Color color){
        g.setColor(color);
        for (int w=5; w <= width; w+= 1){
            delay(5);
            g.draw3DRect(x, y, w, height, true);
            g.fill3DRect(x, y, w, height, true);
        }
    }
}

class LinePoint {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public LinePoint(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }
}

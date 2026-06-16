package com.practice.misc;

import com.practice.MyTechHub;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Janasena extends JFrame implements MyTechHub {

    private int x;
    private int y;
    private int width;
    private int height;
    private List<LinePoint> linePointList;
    public Janasena(int x,int y,int width, int height) {
        this.x = x;
        this.y = y;
        setTitle("Flag of Janasena by MyTechHub3");
        setSize(width,height);
        setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        linePointList = new ArrayList<>();
        populateLinePoints();
    }

    private void populateLinePoints(){
        linePointList.add(new LinePoint(425,150,330,290));
        linePointList.add(new LinePoint(425,150,520,290));
        linePointList.add(new LinePoint(330,290,520,290));

        linePointList.add(new LinePoint(425,345,330,210));
        linePointList.add(new LinePoint(425,345,520,210));
        linePointList.add(new LinePoint(330,210,520,210));
    }

    @Override
    public void paint(Graphics g) {
        drawing(g);
    }

    public void drawing(Graphics g) {
        x = 100;
        y = 200;
        width = 600;
        height = 5;
        drawRectangle(g, Color.RED);

        y += 5;
        drawRectangle(g, Color.WHITE);

        y += 5;
        height = 30;
        drawRectangle(g, Color.RED);

        y += 30;
        height = 5;
        drawRectangle(g, Color.WHITE);

        y += 5;
        height = 5;
        drawRectangle(g, Color.RED);

        y += 10;
        height = 10;
        drawRectangle(g, Color.WHITE);

        for (int i = 0; i <= 360; i++) {
            delay(10);
            g.setColor(Color.RED);
            g.drawArc(300, 120, 250,250,
                    0, i);
            g.fillArc(300, 120, 250,250,
                    0, i);

            g.setColor(Color.WHITE);
            g.drawArc(320, 140, 210, 210,
                    0, i);
            g.fillArc(320, 140, 210, 210,
                    0, i);
        }

        g.setColor(Color.BLACK);
        g.drawPolygon(
                new int[] {425, 320, 530},
                new int[] {130, 300, 300}, 3);
        g.drawPolygon(
                new int[] {425, 320, 530},
                new int[] {365, 200, 200}, 3);


        g.setColor(Color.RED);
        g.fillPolygon(
                new int[] {425, 330, 520},
                new int[] {150, 290, 290}, 3);
        g.fillPolygon(
                new int[] {425, 330, 520},
                new int[] {345, 210, 210}, 3);

        drawLines(g);

        g.setColor(Color.WHITE);
        g.fillPolygon(
                new int[] {425, 390, 460},
                new int[] {280, 230, 230}, 3);

        g.setColor(Color.RED);
        g.fillOval(420,245, 10, 10);
    }

    private void drawRectangle(Graphics g, Color color){
        g.setColor(color);
        for (int w=5; w <= width; w+= 1){
            delay(5);
            g.draw3DRect(x, y, w, height, true);
            g.fill3DRect(x, y, w, height, true);
        }
    }

    private void drawLines(Graphics g){
        linePointList.forEach(linePoint -> {
            g.setColor(Color.BLUE);
            g.drawLine(linePoint.getX1(),linePoint.getY1(),linePoint.getX2(),linePoint.getY2());
            delay(50);
            g.setColor(Color.WHITE);
            g.drawLine(linePoint.getX1(),linePoint.getY1(),linePoint.getX2(),linePoint.getY2());
        });
    }
}


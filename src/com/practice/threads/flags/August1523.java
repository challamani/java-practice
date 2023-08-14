package com.practice.threads.flags;

import javax.swing.*;
import java.awt.*;

class August1523 extends JFrame{

    private int x;
    private int y;
    private int width;
    private int height;

    public August1523(int x,int y,int width, int height) {
        this.x = x;
        this.y = y;
        setTitle("Flag of India by @MyTechHub3");
        setSize(width, height);
        setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        drawing(g);
    }

    public void drawing(Graphics g) {

        x = 50;
        y = 50;
        width = 400;
        height = 100;

        g.setColor(Color.ORANGE);
        g.draw3DRect(x, y, width, height, true);
        g.fill3DRect(x, y, width, height, true);

        y += 100;
        g.setColor(Color.WHITE);
        g.draw3DRect(x, y, width, height, true);
        g.fill3DRect(x, y, width, height, true);

        y += 100;
        g.setColor(Color.GREEN);
        g.draw3DRect(x, y, width, height, true);
        g.fill3DRect(x, y, width, height, true);


        g.setColor(Color.BLUE);
        for (int i = 0; i <= 360; i++) {
            g.drawArc(200, 150, 95,
                    95,
                    0, i);
            g.fillArc(200, 150, 95,
                    95,
                    0, i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {

            }
        }

        g.setColor(Color.WHITE);
        g.drawOval(205, 155, 83, 83);
        g.fillOval(205, 155, 83, 83);
    }
}

package com.practice.misc;

import java.awt.*;
import javax.swing.*;
public class SmilePlease extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.YELLOW);
        g.fillOval(10, 10, 200, 200);

        g.setColor(Color.BLACK);
        g.fillOval(55, 65, 30, 30);
        g.fillOval(135, 65, 30, 30);

        // draw Mouth
        g.fillOval(50, 110, 120, 60);
        // adding smile
        g.setColor(Color.YELLOW);
        g.fillRect(50, 110, 120, 30);
        g.fillOval(50, 120, 120, 40);
    }
    public static void main(String[] args) {
        SmilePlease smile = new SmilePlease();
        JFrame app = new JFrame("Smiley App");
        app.add(smile, BorderLayout.CENTER);
        app.setSize(300, 300);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}

package com.practice.threads.util;

import com.practice.MyTechHub;

public class PaintThread extends Thread implements MyTechHub {

    private final int row;
    private final int column;
    private final String display;
    private final String direction;
    private final String color;
    private final int delay;
    private final int maxIterations;

    private final int increment;

    public PaintThread(int row,
                       int column,
                       String display,
                       String direction,
                       String color,
                       int delay,
                       int maxIterations,
                       int increment){
        this.row = row;
        this.column = column;
        this.display = display;
        this.direction = direction;
        this.color = color;
        this.delay = delay;
        this.maxIterations = maxIterations;
        this.increment = increment;
    }

    @Override
    public void run(){
        int index=0;
        char escCode = 0X1B;
        int rowLocal = row;
        int colLocal = column;

        while (++index <= maxIterations) {

            switch (direction) {
                case "UP" -> rowLocal-=increment;
                case "DOWN" -> rowLocal+=increment;
                case "LEFT" -> colLocal-=increment;
                case "RIGHT" -> colLocal+=increment;
            }
            System.out.printf("%s %c[%d;%df %s",
                    color,
                    escCode,
                    rowLocal,
                    colLocal,
                    display);
            delay(delay);
        }
    }
}

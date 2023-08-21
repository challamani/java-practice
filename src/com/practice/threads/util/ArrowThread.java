package com.practice.threads.util;

import com.practice.MyTechHub;

public class ArrowThread extends Thread implements MyTechHub {
    private final int row;
    private final int column;
    private final String display;
    private final String direction;
    private final String color;
    private final int delay;

    private final int maxIterations;

    public ArrowThread(int row,
                       int column,
                       String display,
                       String direction,
                       String color,
                       int delay,
                       int maxIterations){
        this.row = row;
        this.column = column;
        this.display = display;
        this.direction = direction;
        this.color = color;
        this.delay = delay;
        this.maxIterations = maxIterations;
    }

    @Override
    public void run(){
        int index=0;
        char escCode = 0X1B;
        int rowLocal = row;
        int colLocal = column;

        while (++index <= maxIterations) {

            switch (direction) {
                case "UP" -> rowLocal--;
                case "DOWN" -> rowLocal++;
                case "LEFT" -> colLocal--;
                case "RIGHT" -> colLocal++;
            }
            System.out.printf("%s %c[%d;%df %s",
                    color,
                    escCode,
                    rowLocal,
                    colLocal,
                    display);
            delay(delay);
            System.out.printf("%s %c[%d;%df %s",
                    color,
                    escCode,
                    rowLocal,
                    colLocal,
                    ' ');
        }
    }
}

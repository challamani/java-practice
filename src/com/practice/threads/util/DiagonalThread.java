package com.practice.threads.util;

import com.practice.MyTechHub;

public class DiagonalThread extends Thread implements MyTechHub {
    private final int row;
    private final int column;
    private final SharedItem sharedItem;
    private final String direction;
    private final String color;
    private final int delay;
    private final int maxIterations;

    public DiagonalThread(int row,
                          int column,
                          SharedItem sharedItem,
                          String direction,
                          String color,
                          int delay,
                          int maxIterations) {
        this.row = row;
        this.column = column;
        this.sharedItem = sharedItem;
        this.direction = direction;
        this.color = color;
        this.delay = delay;
        this.maxIterations = maxIterations;
    }

    @Override
    public void run() {
        int index = 0;
        char escCode = 0X1B;
        int rowLocal = row;
        int colLocal = column;

        int innerIndex = 0;
        while (++index <= maxIterations) {
            synchronized (sharedItem) {
                while (++innerIndex <= maxIterations) {
                    switch (direction) {
                        case "UP" -> {
                            rowLocal--;
                            colLocal--;
                        }
                        case "DOWN" -> {
                            rowLocal++;
                            colLocal++;
                        }
                    }
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            sharedItem.getDisplay());
                    delay(delay);
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            ' ');
                    if (innerIndex == maxIterations) {
                        innerIndex = 0;
                        rowLocal = row;
                        colLocal = column;
                        sharedItem.notify();
                        try {
                            sharedItem.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }//synchronized Code
        }
    }
}

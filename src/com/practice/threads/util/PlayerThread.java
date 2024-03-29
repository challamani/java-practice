package com.practice.threads.util;

import com.practice.MyTechHub;

public class PlayerThread extends Thread implements MyTechHub {
    private final int row;
    private final int column;
    private String direction;
    private final String color;
    private final int delay;
    private final int maxIterations;

    public PlayerThread(int row,
                        int column,
                        String direction,
                        String color,
                        int delay,
                        int maxIterations) {
        this.row = row;
        this.column = column;
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
            while (++innerIndex <= maxIterations) {
                switch (direction) {
                    case "UP" -> rowLocal--;
                    case "DOWN" -> rowLocal++;
                    case "LEFT" -> colLocal--;
                    case "RIGHT" -> colLocal++;
                }

                if (innerIndex == maxIterations) {
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            "\uD83D\uDD25");
                    delay(delay);
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            "✨");
                } else {
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            '웃');
                    delay(delay);
                    System.out.printf("%s %c[%d;%df %s",
                            color,
                            escCode,
                            rowLocal,
                            colLocal,
                            '유');
                }
                delay(delay);
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        ' ');

                if (innerIndex == maxIterations) {
                    innerIndex = 0;
                    direction = direction.equals("LEFT") ? "RIGHT" : "LEFT";
                }
            }
        }
    }
}

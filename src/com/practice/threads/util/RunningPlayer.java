package com.practice.threads.util;

import com.practice.MyTechHub;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class RunningPlayer extends Thread implements MyTechHub {
    private final int row;
    private final int column;
    private final String direction;
    private final String color;
    private final int maxIterations;

    private final AtomicInteger atomicInteger;

    public RunningPlayer(int row,
                         int column,
                         String direction,
                         String color,
                         int maxIterations,
                         AtomicInteger atomicInteger) {
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.color = color;
        this.maxIterations = maxIterations;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        int index = 0;
        char escCode = 0X1B;
        int rowLocal = row;
        int colLocal = column;
        while (++index <= maxIterations) {
            switch (direction) {
                case "LEFT" -> colLocal--;
                case "RIGHT" -> colLocal++;
            }

            if (index == maxIterations) {
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        "\uD83D\uDD25");
                delay(ThreadLocalRandom.current()
                        .nextInt(100, 200));
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        "✨");

                delay(ThreadLocalRandom.current()
                        .nextInt(100, 200));
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        '웃');
                int value =  atomicInteger.addAndGet(1);
                System.out.printf("%s %c[%d;%df %c [%d]",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        '웃',value);
            } else {
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        '웃');
                delay(ThreadLocalRandom.current()
                        .nextInt(100, 200));
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        '유');
                delay(ThreadLocalRandom.current()
                        .nextInt(100, 200));
                System.out.printf("%s %c[%d;%df %s",
                        color,
                        escCode,
                        rowLocal,
                        colLocal,
                        ' ');
            }
        }
    }
}

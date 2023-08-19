package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.Random;

public class FoodLocation {

    private volatile int row;
    private volatile int col;
    private final int xLimit;
    private final int yLimit;
    public FoodLocation(int xBoundary, int yBoundary) {
        xLimit = xBoundary;
        yLimit = yBoundary;
    }
    public void produceFood() {
        row = new Random().nextInt(15, xLimit - 15);
        col = new Random().nextInt(15, yLimit - 15);
    }

    public void consumeFood() {
        char escChar = 0x1B;
        System.out.printf("%s %c[%d;%df %c",
                MyTechHub.GREEN_COLOR,
                escChar,
                row,
                col,
                ' ');
        row = 0;
        col = 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

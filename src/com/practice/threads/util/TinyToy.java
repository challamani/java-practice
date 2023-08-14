package com.practice.threads.util;

import com.practice.MyTechHub;

public class TinyToy extends Thread implements MyTechHub {

    private final int row;
    private final int col;
    private final int iterations;
    private final String color;
    public TinyToy(int row, int col, int iterations,
                   String color){
        this.color = color;
        this.row = row;
        this.col = col;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        int index = 0;
        char escCode = 0x1B;

        while (index++ <= iterations) {
            displayChar(color, escCode, row, col, "☺");
            displayChar(color, escCode, row, col - 2, "\\");
            displayChar(color, escCode, row, col + 2, "/");

            displayChar(color, escCode, row + 1, col, "▓");

            displayChar(color, escCode, row + 2, col - 2, "/");
            displayChar(color, escCode, row + 2, col + 2, "\\");

            delay(200);
            displayChar(color, escCode, row, col - 2, " ");
            displayChar(color, escCode, row, col + 2, " ");

            displayChar(color, escCode, row + 2, col - 2, " ");
            displayChar(color, escCode, row + 2, col + 2, " ");

            displayChar(color, escCode, row + 1, col - 2, "/");
            displayChar(color, escCode, row + 1, col + 2, "\\");

            displayChar(color, escCode, row + 2, col, "‼");
            delay(200);

            displayChar(color, escCode, row + 1, col - 2, " ");
            displayChar(color, escCode, row + 1, col + 2, " ");
            displayChar(color, escCode, row + 2, col, " ");
        }
    }

    private void displayChar(String color,
                             char escCode,
                             int r,
                             int c,
                             String display) {
        System.out.printf("%s %c[%d;%df%s",
                color,
                escCode,
                r,
                c,
                display);
    }
}

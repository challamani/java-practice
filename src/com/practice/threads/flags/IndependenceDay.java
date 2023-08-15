package com.practice.threads.flags;

import com.practice.MyTechHub;

class IndependenceDay extends Thread implements MyTechHub {

    private final char[] displayChars;
    private final int column;
    private final int row;

    public IndependenceDay(char[] displayChars,
                           int row,
                           int column) {
        this.displayChars = displayChars;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        String color = getColor(1);
        char escCode = 0X1B;

        for (int col = column; col < (column + (2 * displayChars.length));
             col += 2) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    row,
                    col,
                    '♥');
            delay(50);
        }

        int rowLocal = row + 30;
        int index = 0;
        color = getColor(3);
        for (int col = column; col < (column + (2 * displayChars.length));
             col += 2) {
            while (--rowLocal > row) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escCode,
                        rowLocal,
                        col,
                        '↑');
                delay(30);
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escCode,
                        rowLocal,
                        col,
                        ' ');
            }
            if (rowLocal == row) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escCode,
                        row,
                        col,
                        displayChars[index]);
            }
            rowLocal = row + 30;
            index++;
        }
        System.out.println();
    }
}

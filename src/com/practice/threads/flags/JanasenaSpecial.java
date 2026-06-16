package com.practice.threads.flags;

import com.practice.MyTechHub;

class JanasenaSpecial extends Thread implements MyTechHub {

    private final String[] displayChars;
    private final int column;
    private final int row;

    public JanasenaSpecial(String[] displayChars,
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

        for (int col = column; col < (column + (3 * displayChars.length));
             col += 3) {
            System.out.printf("%s %c[%d;%df%c",
                    color,
                    escCode,
                    row,
                    col,
                    '✡');
            delay(20);
        }

        int rowLocal = row + 30;
        int index = 0;
        color = getColor(7);
        for (int col = column; col < (column + (3 * displayChars.length));
             col += 3) {
            while (--rowLocal > row) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escCode,
                        rowLocal,
                        col,
                        '↑');
                delay(20);
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escCode,
                        rowLocal,
                        col,
                        ' ');
            }
            if (rowLocal == row) {
                System.out.printf("%s %c[%d;%df %s",
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

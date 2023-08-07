package com.practice.threads.misc;

import com.practice.MyTechHub;

import java.util.concurrent.ThreadLocalRandom;

public class XLogo {

    public static void main(String[] args) {
        new XLeftThread(30, 20)
                .start();
        new XRightThread(30, 20)
                .start();
    }
}

class XLeftThread extends Thread implements MyTechHub {

    private final int row;
    private final int column;

    public XLeftThread(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public void run(){
        char escCode = 0X1B;
        int localRow = row;
        String color = getColor(3);
        for(int col=column; col<=(column+20); col++){
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    --localRow,
                    col,
                    '✘');
            delay(200);
        }
        System.out.println();
    }


}

class XRightThread extends Thread implements MyTechHub {

    private final int row;
    private final int column;
    public XRightThread(int row, int column){
        this.row = row;
        this.column = column;
    }
    @Override
    public void run(){
        char escCode = 0X1B;
        int localRow = row;
        String color = getColor(3);

        for(int col=(column+20); col>=(column); col--) {
            localRow--;
            displayChar(color, escCode, localRow, col);
            displayChar(color, escCode, localRow, col + 3);
            delay(200);
        }

        int index=0;
        while (++index <= 9) {
            localRow = row;
            color = getColor(index);

            new XRightWing2(30, 20,
                    color).start();
            new XRightWing1(30, 20,
                    color).start();

            for (int col = (column + 20); col >= (column); col--) {
                localRow--;
                displayChar(color, escCode, localRow, col);
                displayChar(color, escCode, localRow, col + 3);
                delay(ThreadLocalRandom.current()
                        .nextInt(100, 200));
            }
        }

        System.out.println();
        delay(3000);
    }

    private void displayChar(String color,
                             char escCode,
                             int r, int c){
        System.out.printf("%s %c[%d;%df %c",
                color,
                escCode,
                r,
                c,
                '✘');
    }
}

class XRightWing1 extends Thread implements MyTechHub {

    private final int row;
    private final int column;
    private final String color;
    public XRightWing1(int row, int column, String color){
        this.row = row;
        this.column = column;
        this.color = color;
    }

    @Override
    public void run(){
        int index=0;
        int localRow = (row/2)+4;
        int localCol = column+(column/2)-3;
        char escCode=0X1B;

        while (++index <= 10) {
            localRow++;
            localCol--;
            int innerRow = localRow;
            int innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, '✘');
                displayChar(color, escCode, innerRow, innerCol+3, '✘');
            }
            delay(150);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+3, ' ');
            }
        }

        index=0;
        while (++index < 10) {
            localRow--;
            localCol++;

            int innerRow = localRow;
            int innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, '✘');
                displayChar(color, escCode, innerRow, innerCol+3, '✘');
            }
            delay(150);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+3, ' ');
            }
        }
        System.out.println();
    }

    private void displayChar(String color,
                             char escCode,
                             int r, int c, char display){
        System.out.printf("%s %c[%d;%df %c",
                color,
                escCode,
                r,
                c,
                display);
    }
}

class XRightWing2 extends Thread implements MyTechHub {

    private final int row;
    private final int column;
    private final String color;
    public XRightWing2(int row, int column, String color){
        this.row = row;
        this.column = column;
        this.color = color;
    }

    @Override
    public void run(){
        char escCode = 0X1B;
        int index=0;
        int localRow = (row/2)+4;
        int localCol = column+(column/2)+3;

        while (++index <= 10) {
            localRow--;
            localCol++;
            int innerRow = localRow;
            int innerCol = localCol;

            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, '✘');
                displayChar(color, escCode, innerRow, innerCol+3, '✘');
            }
            delay(150);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+3, ' ');
            }
        }

        index=0;
        while (++index < 10) {
            localRow++;
            localCol--;

            int innerRow = localRow;
            int innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, '✘');
                displayChar(color, escCode, innerRow, innerCol+3, '✘');
            }
            delay(150);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+3, ' ');
            }
        }
        System.out.println();
    }

    private void displayChar(String color,
                             char escCode,
                             int r, int c, char display){
        System.out.printf("%s %c[%d;%df %c",
                color,
                escCode,
                r,
                c,
                display);
    }
}
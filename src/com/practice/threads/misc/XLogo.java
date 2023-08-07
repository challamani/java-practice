package com.practice.threads.misc;

import com.practice.MyTechHub;

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
                    '/');
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

        for(int col=(column+20); col>=(column); col--){
            localRow--;
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    localRow,
                    col,
                    '\\');

            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    localRow,
                    col+2,
                    '\\');
            delay(200);
        }

        new XRightThreadPart(30, 20)
                .start();

        color = getColor(4);
        int index=0;
        localRow = (row/2)+4;
        int localCol = column+(column/2)-2;
        while (++index <= 10) {
            localRow++;
            localCol--;
            int innerRow = localRow;
            int innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, '\\');
                displayChar(color, escCode, innerRow, innerCol+2, '\\');
            }
            delay(200);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+2, ' ');
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
                displayChar(color, escCode, innerRow, innerCol, '\\');
                displayChar(color, escCode, innerRow, innerCol+2, '\\');
            }
            delay(200);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow--;
                innerCol--;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+2, ' ');
            }
        }
        System.out.println();
        delay(3000);
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

class XRightThreadPart extends Thread implements MyTechHub {

    private final int row;
    private final int column;

    public XRightThreadPart(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public void run(){
        char escCode = 0X1B;
        String color = getColor(4);

        int index=0;
        int localRow = (row/2)+4;
        int localCol = column+(column/2)+2;

        while (++index <= 10) {
            localRow--;
            localCol++;
            int innerRow = localRow;
            int innerCol = localCol;

            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, '\\');
                displayChar(color, escCode, innerRow, innerCol+2, '\\');
            }
            delay(200);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+2, ' ');
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
                displayChar(color, escCode, innerRow, innerCol, '\\');
                displayChar(color, escCode, innerRow, innerCol+2, '\\');
            }
            delay(200);
            innerRow = localRow;
            innerCol = localCol;
            for (int i = 0; i <= 9; i++) {
                innerRow++;
                innerCol++;
                displayChar(color, escCode, innerRow, innerCol, ' ');
                displayChar(color, escCode, innerRow, innerCol+2, ' ');
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
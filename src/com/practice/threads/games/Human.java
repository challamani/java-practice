package com.practice.threads.games;

import com.practice.MyTechHub;

public class Human implements MyTechHub {

    @Override
    public String getProblem() {
        return "A small exercise move using multiple threads";
    }

    @Override
    public String getApproach() {
        return "Parallel threads with some delay to simulate the move";
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread headThread = new HeadThread(10, 18,
                10, 20);
        Thread bodyThread = new BodyThread(18, 6,
                19, 26);

        Thread leftHand = new LeftHand(6, 23,
                25, 30);
        Thread rightHand = new RightHand(6, 23,
                32, 30);

        Thread leftLeg = new LeftLeg(6, 38,
                28, 30);
        Thread rightLeg = new RightLeg(6, 38,
                30, 30);

        headThread.start();
        bodyThread.start();

        leftHand.start();
        rightHand.start();

        rightLeg.start();
        leftLeg.start();


        //TODO: Your task is to create a country flag using threads
        //TODO: Comment the source code link, I'll merge all flags
        //TODO: Thank you :)
    }
}

class HeadThread extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;

    public HeadThread(int height, int width, int row, int col){
        this.height=height;
        this.width=width;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'O';
        int index=0;

        String color = getColor(5);
        clearScreen();
        int localRow = row;
        while(index++ <= height) {
            for (int i = (col + index); i <= (col + width) - index; i += 2) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escChar,
                        localRow,
                        i, ch);
                delay(50);
            }
            localRow += 1;
        }
    }
}


class BodyThread extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;

    public BodyThread(int height, int width, int row, int col){
        this.height=height;
        this.width=width;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = '@';
        int index=0;

        String color = getColor(3);
        clearScreen();
        int localRow = row;
        while(index++ <= height) {
            for (int i = col; i <= (col + width); i += 2) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escChar,
                        localRow,
                        i, ch);
                delay(50);
            }
            localRow += 1;
        }
    }
}

class LeftHand extends Thread implements MyTechHub{

    private final int length;
    private final int row;
    private final int col;
    private final int rounds;

    public LeftHand(int length, int row, int col, int rounds){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = '>';
        int index=0;

        String color = getColor(1);
        clearScreen();
        while(index++ <= rounds) {
            renderLeftUp(color, escChar, ch);
            delay(200);
            renderLeftUp(color, escChar, ' ');

            renderLeftDown(color, escChar, ch);
            delay(200);
            renderLeftDown(color, escChar, ' ');
        }
    }

    private void renderLeftUp(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row - i,
                    col - i, display);
        }
    }
    private void renderLeftDown(String color, char escChar, char display){
        for (int i = 0; i <= length; i ++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col - i, display);
        }
    }
}

class RightHand extends Thread implements MyTechHub{

    private final int length;
    private final int row;
    private final int col;
    private final int rounds;

    public RightHand(int length, int row, int col, int rounds){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = '<';
        int index=0;

        String color = getColor(1);
        clearScreen();
        while(index++ <= rounds) {
            renderRightUp(color,escChar, ch);
            delay(200);
            renderRightUp(color,escChar, ' ');

            renderRightDown(color,escChar,ch);
            delay(200);
            renderRightDown(color,escChar,' ');
        }
    }
    private void renderRightUp(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row - i,
                    col + i, display);
        }
    }

    private void renderRightDown(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col + i, display);
        }
    }
}


class RightLeg extends Thread implements MyTechHub{

    private final int length;
    private final int row;
    private final int col;
    private final int rounds;

    public RightLeg(int length, int row, int col, int rounds){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'X';
        int index=0;

        String color = getColor(1);
        clearScreen();
        while(index++ <= rounds) {
            renderRightDown(color,escChar, ch);
            delay(200);
            renderRightDown(color,escChar, ' ');

            renderRight(color,escChar,ch);
            delay(200);
            renderRight(color,escChar,' ');
        }
    }
    private void renderRightDown(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col + i, display);
        }
    }

    private void renderRight(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col, display);
        }
    }
}


class LeftLeg extends Thread implements MyTechHub{

    private final int length;
    private final int row;
    private final int col;
    private final int rounds;

    public LeftLeg(int length, int row, int col, int rounds){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'X';
        int index=0;

        String color = getColor(1);
        clearScreen();
        while(index++ <= rounds) {
            renderLeftDown(color,escChar, ch);
            delay(200);
            renderLeftDown(color,escChar, ' ');

            renderLeft(color,escChar,ch);
            delay(200);
            renderLeft(color,escChar,' ');
        }
    }
    private void renderLeftDown(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col - i, display);
        }
    }

    private void renderLeft(String color, char escChar, char display) {
        for (int i = 0; i <= length; i++) {
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    row + i,
                    col, display);
        }
    }
}

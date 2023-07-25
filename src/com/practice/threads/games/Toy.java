package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Toy implements MyTechHub {

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

        List<CharPos> charPosList = new CopyOnWriteArrayList<>();

        Thread headThread = new HeadThread(10, 18,
                10, 20, charPosList);
        Thread bodyThread = new BodyThread(18, 6,
                19, 26, charPosList);

        Thread leftHand = new LeftHand(6, 23,
                25, 20, charPosList);
        Thread rightHand = new RightHand(6, 23,
                32, 20, charPosList);

        Thread leftLeg = new LeftLeg(6, 38,
                28, 20, charPosList);
        Thread rightLeg = new RightLeg(6, 38,
                30, 20, charPosList);

        headThread.start();
        bodyThread.start();

        leftHand.start();
        rightHand.start();

        rightLeg.start();
        leftLeg.start();

        leftHand.join();
        rightHand.join();
        Thread fallThread =  new FallThread(charPosList);
        fallThread.start();
    }
}

class FallThread extends Thread implements MyTechHub{

    private List<CharPos> charPosList;
    public FallThread(List<CharPos> charPosList){
        this.charPosList = charPosList;
    }

    @Override
    //A parent threads, which starts 100s of child threads
    public void run(){
        for(CharPos c: charPosList){
            char escCode = 0x1B;
            new Thread(() -> {
                for(int row=c.getRow(); row<=60; row++) {
                    System.out.printf("%s %c[%d;%df %c",
                            c.getColor(),
                            escCode,
                            row,
                            c.getCol(),
                            c.getCh());
                    delay(ThreadLocalRandom.current()
                            .nextInt(1, 100));
                    System.out.printf("%s %c[%d;%df %c",
                            c.getColor(),
                            escCode,
                            row,
                            c.getCol(),
                            ' ');
                }
            }).start();
        }
    }
}

class HeadThread extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;
    private  List<CharPos> charPosList;
    public HeadThread(int height, int width, int row, int col, List<CharPos> charPosList){
        this.height=height;
        this.width=width;
        this.row = row;
        this.col = col;
        this.charPosList =  charPosList;
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
                charPosList.add(new CharPos(localRow,i, ch,color));
                delay(50);
            }
            localRow += 1;
        }
    }
}

class CharPos {
    private int row;
    private int col;
    private char ch;
    private String color;

    public CharPos(int row, int col, char ch, String color) {
        this.row = row;
        this.col = col;
        this.ch = ch;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getCh() {
        return ch;
    }
}

class BodyThread extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;
    private List<CharPos> charPosList;

    public BodyThread(int height, int width, int row, int col, List<CharPos> charPosList){
        this.height=height;
        this.width=width;
        this.row=row;
        this.col=col;
        this.charPosList =  charPosList;
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
                charPosList.add(new CharPos(localRow, i, ch, color));
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

    private  List<CharPos> charPosList;
    public LeftHand(int length, int row, int col, int rounds, List<CharPos> charPosList){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPosList = charPosList;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'X';
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

        for (int i = 0; i <= length; i ++) {
            charPosList.add(new CharPos(row+i,col-i, ch, color));
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
    private List<CharPos> charPosList;

    public RightHand(int length, int row, int col, int rounds, List<CharPos> charPosList){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPosList = charPosList;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'X';
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

        for (int i = 0; i <= length; i ++) {
            charPosList.add(new CharPos(row+i,col+i, ch, color));
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
    private List<CharPos> charPosList;

    public RightLeg(int length, int row, int col, int rounds, List<CharPos> charPosList){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPosList = charPosList;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'W';
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
    private List<CharPos> charPosList;

    public LeftLeg(int length, int row, int col, int rounds,List<CharPos> charPosList){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPosList = charPosList;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = 'W';
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

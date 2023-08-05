package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Map<String, List<CharPosition>> mapOfPositions = new HashMap<>();
        mapOfPositions.put("head",new CopyOnWriteArrayList<>());
        mapOfPositions.put("body", new CopyOnWriteArrayList<>());
        mapOfPositions.put("hands",new CopyOnWriteArrayList<>());
        mapOfPositions.put("legs",new CopyOnWriteArrayList<>());

        Thread headThread = new HeadThread(10, 18,
                10, 20, mapOfPositions.get("head"));
        Thread bodyThread = new BodyThread(18, 6,
                19, 26, mapOfPositions.get("body"));

        Thread leftHand = new LeftHand(6, 23,
                25, 10, mapOfPositions.get("hands"));
        Thread rightHand = new RightHand(6, 23,
                33, 10, mapOfPositions.get("hands"));

        Thread leftLeg = new LeftLeg(6, 38,
                28, 10, mapOfPositions.get("legs"));
        Thread rightLeg = new RightLeg(6, 38,
                30, 10, mapOfPositions.get("legs"));

        headThread.start();
        bodyThread.start();

        leftHand.start();
        rightHand.start();

        rightLeg.start();
        leftLeg.start();

        IntStream.of(10,20,30,40).forEach(row -> {
            TinyToy right = new TinyToy(row, 50, 40);
            right.start();
            TinyToy left = new TinyToy(row, 10, 40);
            left.start();
        });

        leftHand.join();
        rightHand.join();

        FallThread fallThread =  new FallThread(mapOfPositions.values()
                .stream().flatMap(List::stream).collect(Collectors.toList()));
        fallThread.start();
        fallThread.join();



        List<Integer> intValues =
                IntStream.range(20,45).boxed().collect(Collectors.toList());
        intValues.addAll(IntStream.range(20,45).boxed().toList());

        Collections.shuffle(intValues);
        intValues.forEach(column -> {
            new FlowersThread(List.of('❄','✡','♾'),
                    5, column,
                    "left",1,
                    "\u001B[33m").start();

            new FlowersThread(List.of('✲','❇','✪'),
                    5, column+2,
                    "right",1
                    ,"\u001B[37m").start();

            new FlowersThread(List.of('✩','✻','✢','∞'),
                    5, column-2,
                    "left",1,
                    "\u001B[35m").start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        for (Thread thread : fallThread.getChildThreads()) {
            thread.join();
        }
    }
}

class FlowersThread extends Thread implements  MyTechHub {

    private List<Character> characters;
    private final int row;
    private final int column;
    private String direction;
    private int controlValue;
    private String color;
    public FlowersThread(List<Character> characters,
                         int row,
                         int column,
                         String direction,
                         int controlValue,
                         String color) {
        this.characters = characters;
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.controlValue = controlValue;
        this.color = color;
    }

    @Override
    public void run() {

        int row = this.row;
        int column = this.column;
        char escChar = 0x1B;
        int length = characters.size();

        int[][] position = new int[length][2];
        int[][] previous = new int[length][2];
        for (int i = 0; i < characters.size(); i++) {
            if (direction.equals("left")) {
                column--;
            } else {
                column++;
            }
            position[i][0] = row;
            position[i][1] = column;
        }

        int index = 0;
        int twistControl = 0;
        String direction = this.direction;
        while (index++ <= 60) {
            previous[0][0] = position[0][0];
            previous[0][1] = position[0][1];

            twistControl++;
            if (twistControl == controlValue) {
                twistControl = 0;
                direction = direction.equals("right") ? "left" : "right";
            }
            if (direction.equals("left")) {
                position[0][0] += 1;
                position[0][1] -= 1;
            } else {
                position[0][0] += 1;
                position[0][1] += 1;
            }

            Character character = characters.get(0);
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    position[0][0],
                    position[0][1],
                    character);

            for (int i = 1; i < characters.size(); i++) {
                previous[i][0] = position[i][0];
                previous[i][1] = position[i][1];
                position[i][0] = previous[i - 1][0];
                position[i][1] = previous[i - 1][1];
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escChar,
                        position[i][0],
                        position[i][1],
                        characters.get(i));
            }
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    previous[length - 1][0],
                    previous[length - 1][1],
                    ' ');
            delay(ThreadLocalRandom.current().nextInt(60, 100));
        }
    }
}
class BuildThread extends Thread implements MyTechHub{
    Map<String, List<CharPosition>> mapOfPositions;
    public BuildThread(Map<String, List<CharPosition>> mapOfPositions){
        this.mapOfPositions = mapOfPositions;
    }
    @Override
    public void run(){
        startAndWait("head");
        startAndWait("hands");
        startAndWait("body");
        startAndWait("legs");
    }

    private void startAndWait(String key){
        for(CharPosition c: mapOfPositions.get(key)){
            Thread child = new Thread(() -> backToSamePosition(c));
            child.start();
            try {
                child.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private  void backToSamePosition(CharPosition c){
        char escChar = 0x1B;
        for (int row=50; row>=c.getRow(); row--){
            System.out.printf("%s %c[%d;%df %c",
                    c.getColor(),
                    escChar,
                    row,
                    c.getCol(),
                    c.getCh());
            delay(ThreadLocalRandom.current()
                    .nextInt(1,10));
            System.out.printf("%s %c[%d;%df %c",
                    c.getColor(),
                    escChar,
                    row,
                    c.getCol(),
                    ' ');
        }

        System.out.printf("%s %c[%d;%df %c",
                c.getColor(),
                escChar,
                c.getRow(),
                c.getCol(),
                c.getCh());
    }
}



class FallThread extends Thread implements MyTechHub{

    private List<CharPosition> charPositions;
    private List<Thread> childThreads;
    public FallThread(List<CharPosition> charPositions){
        this.charPositions = charPositions;
        childThreads = new ArrayList<>();
    }

    @Override
    //A parent thread, which starts 100s of child threads
    public void run(){
        for(CharPosition c: charPositions){
            char escCode = 0x1B;
            Thread child = new Thread(() -> {
                for (int row = c.getRow(); row <= 50; row++) {
                    System.out.printf("%s %c[%d;%df %c",
                            c.getColor(),
                            escCode,
                            row,
                            c.getCol(),
                            c.getCh());
                    delay(ThreadLocalRandom.current()
                            .nextInt(30, 100));
                    System.out.printf("%s %c[%d;%df %c",
                            c.getColor(),
                            escCode,
                            row,
                            c.getCol(),
                            ' ');
                }
            });
            childThreads.add(child);
            child.start();
        }
    }

    public List<Thread> getChildThreads() {
        return childThreads;
    }
}

class TinyToy extends Thread implements  MyTechHub{

    private final int row;
    private final int col;
    private final int iterations;
    public TinyToy(int row, int col, int iterations){
        this.row = row;
        this.col = col;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        int index = 0;
        char escCode = 0x1B;
        String color = getColor(3);

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
class HeadThread extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;
    private  List<CharPosition> charPositions;
    public HeadThread(int height, int width, int row, int col, List<CharPosition> charPositions){
        this.height=height;
        this.width=width;
        this.row = row;
        this.col = col;
        this.charPositions =  charPositions;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = '☺';
        int index=0;

        String color = getColor(3);
        clearScreen();
        int localRow = row;
        while(index++ <= height) {
            for (int i = (col + index); i <= (col + width) - index; i += 2) {
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escChar,
                        localRow,
                        i, ch);
                charPositions.add(new CharPosition(localRow,i, ch,color));
                delay(50);
            }
            localRow += 1;
        }
    }
}

class CharPosition {
    private int row;
    private int col;
    private char ch;
    private String color;

    public CharPosition(int row, int col, char ch, String color) {
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
    private List<CharPosition> charPositions;

    public BodyThread(int height, int width, int row, int col, List<CharPosition> charPositions){
        this.height=height;
        this.width=width;
        this.row=row;
        this.col=col;
        this.charPositions =  charPositions;
    }
    @Override
    public void run(){
        char escChar= 0x1B;
        char ch = '▓';
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
                charPositions.add(new CharPosition(localRow, i, ch, color));
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

    private  List<CharPosition> charPositions;
    public LeftHand(int length, int row, int col, int rounds, List<CharPosition> charPositions){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPositions = charPositions;
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
            charPositions.add(new CharPosition(row+i,col-i, ch, color));
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
    private List<CharPosition> charPositions;

    public RightHand(int length, int row, int col, int rounds, List<CharPosition> charPositions){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPositions = charPositions;
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
            charPositions.add(new CharPosition(row+i,col+i, ch, color));
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
    private List<CharPosition> charPositions;

    public RightLeg(int length, int row, int col, int rounds, List<CharPosition> charPositions){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPositions = charPositions;
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
        for (int i = 0; i <= length; i ++) {
            charPositions.add(new CharPosition(row+i,col, ch, color));
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
    private List<CharPosition> charPositions;

    public LeftLeg(int length, int row, int col, int rounds,List<CharPosition> charPositions){
        this.length=length;
        this.rounds = rounds;
        this.row = row;
        this.col = col;
        this.charPositions = charPositions;
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

        for (int i = 0; i <= length; i ++) {
            charPositions.add(new CharPosition(row+i,col, ch, color));
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

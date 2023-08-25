package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class InfinityWar extends Thread implements MyTechHub {


    private List<Character> characters;
    private final int row;
    private final int column;
    private String direction;
    private int controlValue;
    private String color;
    private String innerDirection;

    private int maxIterations;

    public InfinityWar(List<Character> characters,
                       int row,
                       int column,
                       String direction,
                       String innerDirection,
                       int controlValue,
                       String color,
                       int maxIterations) {
        this.characters = characters;
        this.row = row;
        this.column = column;
        this.direction = direction;
        this.controlValue = controlValue;
        this.color = color;
        this.innerDirection = innerDirection;
        this.maxIterations= maxIterations;
    }

    @Override
    public void run() {

        char escChar = 0x1B;
        int row = this.row;
        int column = this.column;
        int length = characters.size();

        int[][] position = new int[length][2];
        int[][] previous = new int[length][2];

        for (int i = 0; i < characters.size(); i++) {
            if (innerDirection.equals("L")) {
                column--;
            } else {
                column++;
            }
            position[i][0] = row;
            position[i][1] = column;
        }

        int index = 0;
        int twistControl = 0;

        while (index++ <= maxIterations) {

            previous[0][0] = position[0][0];
            previous[0][1] = position[0][1];
            moveAsPerDirection(position);

            twistControl++;
            if (twistControl == controlValue) {
                twistControl = 0;
                innerDirection = innerDirection.equals("L") ? "R" : "L";
            }
            if (innerDirection.equals("L")) {
                position[0][1] -= 1;
            } else {
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
            delay(ThreadLocalRandom.current()
                    .nextInt(30));
        }
    }

    private void moveAsPerDirection(int[][] position) {
        switch (direction) {
            case "L" -> position[0][1] -= 1;
            case "R" -> position[0][1] += 1;
            case "U" -> position[0][0] -= 1;
            case "D" -> position[0][0] += 1;
            case "RD" -> {
                position[0][0] += 1;
                position[0][1] += 1;
            }
            case "LD" -> {
                position[0][0] += 1;
                position[0][1] -= 1;
            }
            case "LU" -> {
                position[0][0] -= 1;
                position[0][1] -= 1;
            }
            case "RU" -> {
                position[0][0] -= 1;
                position[0][1] += 1;
            }
        }
    }

    public static void main(String[] args) {

        String[] directions = {"L", "D", "R", "RD", "LD", "RU", "LU", "U", "RD"};

        IntStream.range(1, 1000).forEach(val -> {
            int d = new Random().nextInt(9);

            new InfinityWar(List.of('꙰','꙰','꙰','꙰','꙰','꙰'),
                    2,15,
                    "D",
                    val%2==0?"R":"L",
                    2,
                    MyTechHub.YELLOW_COLOR,
                    60
                ).start();

            new InfinityWar(List.of('꙰','꙰','꙰','꙰','꙰','꙰'),
                    2,30,
                    "D",
                    val%2==0?"R":"L",
                    2,
                    MyTechHub.WHITE_COLOR,
                    60
            ).start();

            new InfinityWar(List.of('꙰','꙰','꙰','꙰','꙰','꙰'),
                    60,20,
                    "U",
                    val%2==0?"R":"L",
                    2,
                    MyTechHub.WHITE_COLOR,
                    60
            ).start();

            new InfinityWar(List.of('꙰','꙰','꙰','꙰','꙰','꙰'),
                    60,25,
                    "U",
                    val%2==0?"R":"L",
                    2,
                    MyTechHub.YELLOW_COLOR,
                    60
            ).start();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}

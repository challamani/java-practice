package com.practice.threads.util;

import com.practice.MyTechHub;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeThread extends Thread implements MyTechHub {

    private List<Character> characters;
    private final int row;
    private final int column;
    private String direction;
    private int controlValue;
    private String color;
    public SnakeThread(List<Character> characters,
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
            delay(ThreadLocalRandom.current().nextInt(80, 100));
        }
    }
}
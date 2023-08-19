package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.ArrayDeque;
import java.util.Queue;

public class AutoSnake {

    public static void main(String[] args) {
        Queue<Target> targetQueue = new ArrayDeque<>();
        int start = 6;
        int colBoundary = 50;
        int rowBoundary = 50;
        int maxFoodItems = 100;
        FoodLocation foodLocation = new FoodLocation
                (colBoundary, rowBoundary);
        new FoodGenThread(maxFoodItems, foodLocation, "\uD83C\uDF54")
                .start();
        char display = '@';
        int row = 10;
        int col = 10;
        int length = 12;
        int maxMoves = 1000;
        new HungrySnake(display,
                row,
                col,
                length,
                start,
                colBoundary,
                rowBoundary,
                "R",
                MyTechHub.GREEN_COLOR,
                targetQueue,
                foodLocation,
                maxMoves).start();
    }
}

class Target {
    @Override
    public String toString() {
        return "Target{" +
                "direction='" + direction + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    private final String direction;
    private final int start;
    private final int end;

    public String getDirection() {
        return direction;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    public Target(String direction, int start, int end) {
        this.direction = direction;
        this.start = start;
        this.end = end;
    }
}

class FoodGenThread extends Thread implements MyTechHub {

    private final int maxLimit;
    private final FoodLocation foodLocation;
    private final String display;
    public FoodGenThread(int maxLimit,
                         FoodLocation foodLocation,
                         String display){
        this.maxLimit = maxLimit;
        this.foodLocation = foodLocation;
        this.display = display;
    }

    @Override
    public void run() {
        char escChar = 0x1B;
        int index = 0;
        while (++index < maxLimit) {
            foodLocation.produceFood();
            System.out.printf("%s %c[%d;%df %s",
                    MyTechHub.WHITE_COLOR,
                    escChar,
                    foodLocation.getRow(),
                    foodLocation.getCol(),
                    display);
            try {
                synchronized (foodLocation){
                    foodLocation.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
 class HungrySnake extends Thread implements MyTechHub {

    private final char display;
    private final int row;
    private final int column;
    private String direction;
    private final String color;
    private final int colBoundary;
    private final int rowBoundary;
    private final int length;
    private final int start;
    private final Queue<Target> targetQueue;
    private final FoodLocation foodLocation;
    private final int maxMoves;
    private int swallowedEggs;
    public HungrySnake(char ch,
                       int row,
                       int column,
                       int length,
                       int start,
                       int colBoundary,
                       int rowBoundary,
                       String direction,
                       String color,
                       Queue<Target> targetQueue,
                       FoodLocation foodLocation,
                       int maxMoves) {

        this.display = ch;
        this.row = row;
        this.column = column;
        this.length = length;
        this.start = start;
        this.colBoundary = colBoundary;
        this.rowBoundary = rowBoundary;
        this.direction = direction;
        this.color = color;
        this.targetQueue = targetQueue;
        this.foodLocation = foodLocation;
        this.maxMoves = maxMoves;
        this.swallowedEggs = 0;
    }

    @Override
    public void run() {
        int row = this.row;
        int column = this.column;

        char escChar = 0x1B;
        int index =0;
        int[][] position = new int[length][2];
        int[][] previous = new int[length][2];

        for (int i = 0; i < length; i++) {
            if (direction.equals("L")) {
                column += 2;
            } else {
                column -= 2;
            }
            position[i][0] = row;
            position[i][1] = column;
        }

        while (++index < maxMoves) {

            previous[0][0] = position[0][0];
            previous[0][1] = position[0][1];
            movePosition(position);

            System.out.printf("%s %c[%d;%df %c",
                    MyTechHub.YELLOW_COLOR,
                    escChar,
                    position[0][0],
                    position[0][1],
                    display);

            for (int i = 1; i < length; i++) {
                previous[i][0] = position[i][0];
                previous[i][1] = position[i][1];
                position[i][0] = previous[i - 1][0];
                position[i][1] = previous[i - 1][1];
                System.out.printf("%s %c[%d;%df %c",
                        color,
                        escChar,
                        position[i][0],
                        position[i][1],
                        display);
            }
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escChar,
                    previous[length - 1][0],
                    previous[length - 1][1],
                    ' ');

            delay(100);
            if(targetQueue.isEmpty()) {
                setTarget(direction, position[0][0], position[0][1]);
            }
            handleTargetLocation(position);
        }
    }

    private void movePosition(int[][] position){
        switch (direction) {
            case "L" -> position[0][1] -= (position[0][1] <= start)
                    ? -1 * colBoundary : 2;
            case "R" -> position[0][1] += (position[0][1] >= colBoundary)
                    ? -1*(colBoundary -start) : 2;
            case "D" -> position[0][0] += (position[0][0] >= rowBoundary)
                    ? -1*(rowBoundary -start) : 1;
            case "U" -> position[0][0] -= (position[0][0] <= start)
                    ? -1 * rowBoundary : 1;
        }
    }

    private void handleTargetLocation(int[][] position) {

        if (!targetQueue.isEmpty()) {
            Target target = targetQueue.peek();
            if (direction.equals(target.getDirection())) {
                switch (direction) {
                    case "R", "L" -> {
                        if (position[0][1] == target.getEnd()) {
                            targetQueue.poll();
                            if(!targetQueue.isEmpty()){
                                direction = targetQueue.peek().getDirection();
                            }
                        }
                    }
                    case "U", "D" -> {
                        if (position[0][0] == target.getEnd()) {
                            targetQueue.poll();
                            if(!targetQueue.isEmpty()){
                                direction = targetQueue.peek().getDirection();
                            }
                        }
                    }
                }
            } else {
                direction = target.getDirection();
            }
        }

        if(position[0][0] == foodLocation.getRow()
                && (position[0][1] == foodLocation.getCol()
                    || position[0][1] == foodLocation.getCol()+1
                    || position[0][1] == foodLocation.getCol()-1)) {
            targetQueue.clear();
            swallowedEggs++;
            displayScore();
            synchronized (foodLocation) {
                foodLocation.consumeFood();
                foodLocation.notify();
            }
        }
    }

    private void displayScore(){
        System.out.printf("%s %c[%d;%df %s",
                MyTechHub.WHITE_COLOR,
                0x1B,
                3,
                3,
                "Burgers in Snake account: "+swallowedEggs);
    }

    private void setTarget(String direction, int currentRow, int currentCol){

        String nextDirection;
        Target target;
        int nextCol=currentCol;
        int nextRow=currentRow;

        if(direction.equals("D") || direction.equals("U")){
            nextDirection =  currentCol < foodLocation.getCol() ? "R" : "L";
            target = new Target(nextDirection, currentCol, foodLocation.getCol());
            nextCol = foodLocation.getRow();
        }else {
            nextDirection = currentRow < foodLocation.getRow() ? "D" : "U";
            target = new Target(nextDirection, currentRow, foodLocation.getRow());
            nextRow = foodLocation.getRow();
        }
        targetQueue.add(target);
        if(targetQueue.size() < 2){
            setTarget(target.getDirection(),nextRow,nextCol);
        }
    }

}
package com.practice;

import java.util.Map;

public interface MyTechHub {

    String GREEN_COLOR="\u001B[32m";
    String RED_COLOR="\u001B[31m";
    String BLACK_COLOR="\u001B[30m";
    String YELLOW_COLOR="\u001B[33m";
    String BLUE_COLOR="\u001B[34m";
    String MAGENTA="\u001B[35m";
    String CYAN_COLOR="\u001B[36m";
    String WHITE_COLOR="\u001B[37m";

    Map<Integer, String> COLOR_MAP = Map.of(0, RED_COLOR, 1, GREEN_COLOR,
            2, YELLOW_COLOR,
            3, WHITE_COLOR,
            4, MAGENTA,
            5, BLUE_COLOR, 6, GREEN_COLOR, 7, RED_COLOR,
            8, YELLOW_COLOR, 9,WHITE_COLOR);

    default int[] returnLeftMax(int[] elements) {
        int[] leftMax = new int[elements.length];
        int index = 0;
        int leftMaxVal = Integer.MIN_VALUE;
        while (index < elements.length) {
            if (leftMaxVal < elements[index]) {
                leftMaxVal = elements[index];
            }
            leftMax[index] = leftMaxVal;
            index++;
        }
        return leftMax;
    }

    default int[] returnRightMax(int[] elements){
        int[] rightMax = new int[elements.length];
        int index = elements.length - 1;
        int rightMaxVal = Integer.MIN_VALUE;
        while (index >= 0) {
            if (rightMaxVal < elements[index]) {
                rightMaxVal = elements[index];
            }
            rightMax[index] = rightMaxVal;
            index--;
        }
        return rightMax;
    }
    default String getProblem() {
        return "";
    }

    default String getApproach() {
        return "";
    }

    default void displayProblemAndApproach() {
        System.out.println(RED_COLOR + "\nProblem: " + this.getProblem());
        System.out.println(GREEN_COLOR + "Solution: " + this.getApproach());
    }
    default void endCard(){
        System.out.println(GREEN_COLOR + "\nPlease like the video if you find it useful!");
    }

    default void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }

    default  void delay(int milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException ie) {
            System.out.println("running thread got interrupted:" + ie.getMessage());
        }
    }

    default String getColor(int index){
        return COLOR_MAP.get(index);
    }
}

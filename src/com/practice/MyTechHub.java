package com.practice;

public interface MyTechHub {

    String GREEN_COLOR="\u001B[32m";
    String RED_COLOR="\u001B[31m";
    String BLACK_COLOR="\u001b[30m";
    String YELLOW_COLOR="\u001b[33m";
    String BLUE_COLOR="\u001b[34m";
    String MAGENTA="\u001b[35m";
    String CYAN_COLOR="\u001b[36m";
    String WHITE_COLOR="\u001b[37m";

    default void displayProblemAndApproach() {
        System.out.println(RED_COLOR + "\nProblem: " + this.getProblem());
        System.out.println(GREEN_COLOR + "Solution: " + this.getApproach());
    }
    public String getProblem();
    public String getApproach();

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

        }
    }
}

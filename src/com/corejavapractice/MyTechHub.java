package com.corejavapractice;

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
        System.out.println(RED_COLOR + "Problem: " + this.getProblem());
        System.out.println(GREEN_COLOR + "Solution: " + this.getApproach());
    }
    public String getProblem();
    public String getApproach();
}

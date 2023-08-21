package com.practice.arrays;

import com.practice.MyTechHub;

public class MinMaxOp implements MyTechHub {

    @Override
    public String getProblem() {
        return "Find the min and max " +
                "value from the given input array[]";
    }

    @Override
    public String getApproach() {
        return "comparing each element " +
                "of an array in a loop O(n)";
    }

    public void printMinMax(int[] input){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0; i< input.length; i++){
            if(min > input[i]){
                min = input[i];
            }

            if(max < input[i]){
                max = input[i];
            }
            displayRuntime(input, i);
        }
        System.out.printf("%s output[max]=%d, output[min]=%d",GREEN_COLOR, max,min);
    }
    private void displayRuntime(int[] input, int runtimeIndex) {
        clearScreen();
        for (int i = 0; i < input.length; i++) {
            if (i <= runtimeIndex) {
                System.out.printf("%s [%3d]", MAGENTA, input[i]);
            } else {
                System.out.printf("%s [%3d]", GREEN_COLOR, input[i]);
            }
        }
        delay(200);
    }
    public static void main(String[] args) {
        MinMaxOp minMaxOp = new MinMaxOp();
        minMaxOp.displayProblemAndApproach();
        minMaxOp.delay(500);
        minMaxOp.printMinMax(new int[]{2, 3, 1, 45, 6, 7, 8, 90, 3, 67, -2});
        minMaxOp.endCard();
    }
}

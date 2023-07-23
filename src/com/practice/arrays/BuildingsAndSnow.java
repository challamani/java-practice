package com.practice.arrays;


import java.util.Scanner;
import com.practice.MyTechHub;
public class BuildingsAndSnow implements MyTechHub {

    @Override
    public String getProblem() {
        return "Find the maximum captured snow quantity in between series of buildings";
    }

    @Override
    public String getApproach() {
        return "A dynamic programming," +
                " \ncalculate max building height at each index left to right and right to left,\n2nd max at each building is the peak of captured snow?";
    }
    //TODO Calculate maximum snow quantity captured in between series of buildings
    public long calculateMaximumSnowQuantity(int[] buildingHeights){
        long maxSnowQuantity=0l;
        int[] left_max = new int[buildingHeights.length];
        int[] right_max = new int[buildingHeights.length];

        int index=0;
        int leftMaxVal = Integer.MIN_VALUE;
        while (index < buildingHeights.length){
            if(leftMaxVal < buildingHeights[index]){
                leftMaxVal = buildingHeights[index];
            }
            left_max[index] = leftMaxVal;
            index++;
        }

        index = buildingHeights.length-1;
        int rightMaxVal = Integer.MIN_VALUE;
        while (index >= 0){
            if(rightMaxVal < buildingHeights[index]){
                rightMaxVal = buildingHeights[index];
            }
            right_max[index] = rightMaxVal;
            index--;
        }

        //Now calculate the maximum captured snow in between buildings
        for (int i=0; i< buildingHeights.length; i++){
            int maxTwo  = Math.min(left_max[i], right_max[i]);
            maxSnowQuantity += (maxTwo - buildingHeights[i]);
        }
        displayBuildingBlocks(buildingHeights, left_max, right_max);
        return  maxSnowQuantity;
    }
    private void displayBuildingBlocks(int[] buildingHeights, int[] leftMax, int[] rightMax) {
        clearScreen();
        int verticalMax = Math.max(leftMax[leftMax.length - 1], rightMax[0]);
        int horizontalMax = buildingHeights.length;
        int index = 0;
        while (index < verticalMax) {
            for (int i = 0; i < horizontalMax; i++) {
                delay(50);
                int maxTwo = Math.min(leftMax[i], rightMax[i]);
                if (buildingHeights[i] >= (verticalMax - index)) {
                    System.out.print(GREEN_COLOR + "[H]");
                } else if (maxTwo >= verticalMax - index) {
                    System.out.print(RED_COLOR + "[@]");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
            index++;
        }

        index = 0;
        int snowQuantity = 0;
        while (index < horizontalMax) {
            int maxTwo = Math.min(leftMax[index], rightMax[index]);

            snowQuantity += (maxTwo - buildingHeights[index]);
            System.out.printf(MAGENTA + "[%d]", (maxTwo - buildingHeights[index]));
            index++;
        }
        System.out.printf(GREEN_COLOR + " = [%3d]", snowQuantity);
    }

    public static void main(String[] args) {

        BuildingsAndSnow buildingsAndSnow = new BuildingsAndSnow();
        buildingsAndSnow.displayProblemAndApproach();

        Scanner scanner = new Scanner(System.in);
        System.out.println(GREEN_COLOR + "Number of building in series:?");
        int numberOfBuilds = scanner.nextInt();
        int[] buildingHeights = new int[numberOfBuilds];
        int index = 0;
        System.out.println("Enter corresponding heights:");
        while (index < numberOfBuilds) {
            buildingHeights[index] = scanner.nextInt();
            index++;
        }

        System.out.printf("\nSnow quantity: [%d]\n", buildingsAndSnow.calculateMaximumSnowQuantity(buildingHeights));
        scanner.close();
    }
}

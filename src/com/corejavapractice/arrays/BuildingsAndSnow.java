package com.corejavapractice.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class BuildingsAndSnow {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Number of building in series:?");
        int numberOfBuilds = sc.nextInt();
        int[] buildingHeight = new int[numberOfBuilds];

        int[] leftMax = new int[numberOfBuilds];
        int[] rightMax = new int[numberOfBuilds];

        int index = 0;
        int left_max = Integer.MIN_VALUE;
        System.out.println("Enter corresponding heights:");
        while (index < numberOfBuilds) {
            buildingHeight[index] = sc.nextInt();
            if (left_max < buildingHeight[index]) {
                left_max = buildingHeight[index];
            }
            leftMax[index] = left_max;
            index++;
        }

        index = numberOfBuilds - 1;
        int right_max = Integer.MIN_VALUE;
        while (0 < index) {
            if (right_max < buildingHeight[index]) {
                right_max = buildingHeight[index];
            }
            rightMax[index] = right_max;
            index--;
        }

        System.out.println("<<< building heights :: " + Arrays.toString(buildingHeight));
        System.out.println("<<< left max :: " + Arrays.toString(leftMax));
        System.out.println("<<< right max ::" + Arrays.toString(rightMax));

        long snowQuantity = 0l;
        index = 0;
        while (index < numberOfBuilds) {

            int min = Math.min(leftMax[index], rightMax[index]);
            System.out.println(String.format("min: %d and (%d-%d) = %d",
                    min, buildingHeight[index], min, min - buildingHeight[index]));

            snowQuantity += (min - buildingHeight[index]);
            index++;
        }
        System.out.println("snow quantity :" + snowQuantity);
    }
}

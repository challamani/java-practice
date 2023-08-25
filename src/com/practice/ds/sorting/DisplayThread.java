package com.practice.ds.sorting;


import com.practice.MyTechHub;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DisplayThread extends Thread
        implements MyTechHub {

    public void sortElements(int[] elements) {
        int[] left_max = new int[elements.length];
        int[] right_max = new int[elements.length];

        int index = 0;
        int leftMaxVal = Integer.MIN_VALUE;
        while (index < elements.length) {
            if (leftMaxVal < elements[index]) {
                leftMaxVal = elements[index];
            }
            left_max[index] = leftMaxVal;
            index++;
        }

        index = elements.length - 1;
        int rightMaxVal = Integer.MIN_VALUE;
        while (index >= 0) {
            if (rightMaxVal < elements[index]) {
                rightMaxVal = elements[index];
            }
            right_max[index] = rightMaxVal;
            index--;
        }
        //bubbleSort(elements,left_max,right_max);
        selectionSort(elements,left_max,right_max);
    }

    private void selectionSort(int[] elements,
                            int[] left_max,
                            int[] right_max){
        int minIndex;
        int temp;
        int length = elements.length;

        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i+1; j < length; j++) {
                if (elements[j] < elements[minIndex]) {
                    minIndex=j;
                }
            }
            if (minIndex != i) {
                displayArray(elements, left_max, right_max, i, minIndex);
                temp = elements[minIndex];
                elements[minIndex] = elements[i];
                elements[i] = temp;
                displayArray(elements, left_max, right_max);
            }
        }
    }


    private void bubbleSort(int[] elements,
                            int[] left_max,
                            int[] right_max){
        boolean swapped;
        int temp;
        int length = elements.length;

        for (int i = 0; i < length - 1; i++) {
            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    displayArray(elements, left_max, right_max, j, j + 1);
                    temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                    displayArray(elements, left_max, right_max);
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    private void displayArray(int[] heights,
                              int[] leftMax,
                              int[] rightMax){
        clearScreen();
        int verticalMax = Math.max(leftMax[leftMax.length - 1], rightMax[0]);
        int index = 0;
        char escChar = 0x1B;
        int row = 1;
        int column;
        while (index < verticalMax) {
            for (int i = 0; i < heights.length; i++) {
                column = (i + 1) * 2;
                if (heights[i] >= (verticalMax - index)) {
                    System.out.printf("%s%c[%d;%df%c",
                            GREEN_COLOR,
                            escChar,
                            row,
                            column,
                            '▓');
                } else {
                    System.out.printf("%s%c[%d;%df %c",
                            GREEN_COLOR,
                            escChar,
                            row,
                            column,
                            ' ');
                }
            }
            row++;
            index++;
        }
    }
    private void displayArray(int[] heights,
                               int[] leftMax,
                               int[] rightMax,
                               int x,
                               int y) {
        clearScreen();
        int verticalMax = Math.max(leftMax[leftMax.length - 1], rightMax[0]);
        int index = 0;
        char escChar = 0x1B;
        int row = 1;
        int column;
        while (index < verticalMax) {
            for (int i = 0; i < heights.length; i++) {
                column = (i + 1) * 2;
                if (heights[i] >= (verticalMax - index)) {
                    System.out.printf("%s%c[%d;%df%c",
                            GREEN_COLOR,
                            escChar,
                            row,
                            column,
                            '▓');
                } else {
                    System.out.printf("%s%c[%d;%df %c",
                            GREEN_COLOR,
                            escChar,
                            row,
                            column,
                            ' ');
                }
            }
            row++;
            index++;
        }

        System.out.print("\u001B[0;");
        DisplayCompareItem displayCompareItem = new DisplayCompareItem(
                heights,
                Arrays.asList(x, y),
                verticalMax);
        displayCompareItem.start();
        try {
            displayCompareItem.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        DisplayThread displayThread = new DisplayThread();
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREEN_COLOR + "Enter number of elements:?");
        int size = scanner.nextInt();
        int[] elements = new int[size];
        List<Integer> listElements = IntStream.range(3, 25)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(listElements);
        for (int i = 0; i < size; i++) {
            elements[i] = listElements.get(i);
        }
        displayThread.sortElements(elements);
        scanner.close();
    }
}

class DisplayCompareItem extends Thread
        implements MyTechHub{

    private final int[] heights;
    private final List<Integer> targetIndexes;
    private final int verticalMax;

    public DisplayCompareItem(int[] heights,
                              List<Integer> targetIndexes,
                              int verticalMax) {
        this.heights = heights;
        this.targetIndexes = targetIndexes;
        this.verticalMax = verticalMax;
    }
    @Override
    public void run() {
        String color;
        for (int i = 0; i < heights.length; i++) {
            int innerIndex = 0;
            char escCode = 0x1B;
            int column=(i+1)*2;

            while (++innerIndex <= verticalMax) {
                color = targetIndexes.contains(i)?
                        MyTechHub.WHITE_COLOR:MyTechHub.GREEN_COLOR;
                if (innerIndex + heights[i] > verticalMax) {
                    System.out.printf("%s%c[%d;%df%c",
                            color,
                            escCode,
                            innerIndex,
                            column,
                            '▓');
                } else {
                    System.out.printf("%s%c[%d;%df%c",
                            color,
                            escCode,
                            innerIndex,
                            column,
                            ' ');
                }
            }

            if(targetIndexes.contains(i)) {
                delay(150);
            }
        }
    }
}

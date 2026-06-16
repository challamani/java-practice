package com.practice.ds.sorting;


import com.practice.MyTechHub;

import java.util.Arrays;

public class DisplayThread extends Thread
        implements MyTechHub {

    public void displayArray(int[] heights,
                              int[] leftMax,
                              int[] rightMax){
        //clearScreen();
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
    public void displayArray(int[] heights,
                               int[] leftMax,
                               int[] rightMax,
                               int x,
                               int y) {
        displayArray(heights,leftMax, rightMax);
        int verticalMax = Math.max(leftMax[leftMax.length - 1], rightMax[0]);

        //System.out.print("\u001B[0;");
        HighlightElements highlightElements = new HighlightElements(
                heights,
                Arrays.asList(x, y),
                verticalMax);
        highlightElements.start();
        try {
            highlightElements.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

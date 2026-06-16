package com.practice.ds.sorting;

import com.practice.MyTechHub;

import java.util.List;

public class HighlightElements extends Thread
        implements MyTechHub {

    private final int[] heights;
    private final List<Integer> targetIndexes;
    private final int verticalMax;

    public HighlightElements(int[] heights,
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
                delay(300);
            }
        }
    }
}

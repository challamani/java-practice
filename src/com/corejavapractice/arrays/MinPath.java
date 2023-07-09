package com.corejavapractice.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinPath {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 3, 1},
                {1, 1, 7},
                {4, 2, 1}
        };
        calculateDistance(data);
    }
    private static int calculateDistance(int[][] data) {
        int maxRows = data.length;
        int maxCols = data[0].length;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e[0]));
        priorityQueue.add(new int[]{data[0][0], 0, 1});
        priorityQueue.add(new int[]{data[0][0], 1, 0});

        int distance[][] = new int[maxRows][maxCols];
        int minDistance = Integer.MAX_VALUE;

        while (!priorityQueue.isEmpty()) {

            int[] record = priorityQueue.poll();

            int targetRow = record[1];
            int targetColumn = record[2];
            if (targetRow >= maxRows || targetColumn >= maxCols) {
                continue;
            }

            int currentDistance = record[0];
            int newHeight = currentDistance + data[targetRow][targetColumn];
            System.out.println("<<<< queue item :: " + Arrays.toString(record));
            System.out.println(String.format("<<<< current distance : [%d] " +
                    " and new position (%d, %d) and its weight: %d and final new distance: ==>> [%d] ",currentDistance,targetRow,targetColumn,
                    data[targetRow][targetColumn], newHeight)
                );

            if(maxRows-1 == targetRow && maxCols-1 == targetColumn && minDistance > newHeight){
                minDistance = newHeight;
            }
            distance[targetRow][targetColumn] = newHeight;
            priorityQueue.add(new int[]{(newHeight), targetRow, targetColumn + 1});
            priorityQueue.add(new int[]{(newHeight), targetRow + 1, targetColumn});
        }

        System.out.println("<<<< " + minDistance);
        return minDistance;
    }
}

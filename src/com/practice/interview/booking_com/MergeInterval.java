package com.practice.interview.booking_com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval {

    public int[][] mergeInterval(int[][] intervals){

        if(intervals.length <= 1){
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new LinkedList<>();

        int[] currentInterval = intervals[0];
        merged.add(currentInterval);
        for(int[] nextInterval: intervals){
            int currentEnd = currentInterval[1];
            int nextStart = nextInterval[0];
            int nextEnd  =  nextInterval[1];
            if (nextStart <= currentEnd) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = nextInterval;
                merged.add(currentInterval);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        MergeInterval solver = new MergeInterval();
        int[][] input = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = solver.mergeInterval(input);
        System.out.println(Arrays.deepToString(result));
    }
}

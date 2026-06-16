package com.practice.arrays;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    public static void main(String[] args) {

        int[] input = new int[]{1, 2, 3, 0, 8, 9};
        int k = 3;

        Map<Integer, Integer> currentSumFrequency = new HashMap<>();
        currentSumFrequency.put(0, 1);

        int count = 0;
        int currentSum = 0;
        for (int num : input) {
            currentSum += num;
            if (currentSumFrequency.containsKey(currentSum - k)) {
                count += currentSumFrequency.get(currentSum - k);
            }
            currentSumFrequency.put(currentSum, currentSumFrequency.getOrDefault(currentSum, 0) + 1);
        }

        System.out.println("number of sub-arrays: "+count);
    }
}

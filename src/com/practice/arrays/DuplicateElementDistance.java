package com.practice.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class DuplicateElementDistance {

    public static void main(String[] args) {

        int[] input = new int[]{1, 2, 3, 1, 4, 0, 1, 5};
        Map<Integer, Integer> dupEleDistance = new HashMap<>();

        AtomicInteger totalDistance = new AtomicInteger(0);
        IntStream.range(0, input.length)
                .forEach(i -> {
                    int item = input[i];
                    if (dupEleDistance.containsKey(item)) {
                        int previousIndex = dupEleDistance.get(item);
                        totalDistance.addAndGet(i - previousIndex);
                    }
                    dupEleDistance.put(item, i);
                });

        System.out.println("total distance: "+totalDistance.get());
    }
}

package com.practice.arrays;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DuplicateElements {

    public static void main(String[] args) {

        int[] input = new int[]{1, 2, 1, 3, 4, 0, 4, 9, 13, 14, 9, 0};

        Set<Integer> visited = new HashSet<>();
        Set<Integer> duplicate = new HashSet<>();

        IntStream.of(input).forEach(item -> {

            if (!visited.contains(item))
                visited.add(item);
            else
                duplicate.add(item);

        });

        System.out.println("duplicates: "+duplicate);
    }
}

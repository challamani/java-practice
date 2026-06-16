package com.practice.arrays;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MinimumMoves {

    public static void main(String[] args) {

        int[] input = new int[] {1, 3, 2, 4, 5};

        int[] sorted = input.clone();
        Arrays.sort(sorted);

        AtomicInteger correctPositionCnt = new AtomicInteger();
        IntStream.range(0, input.length).forEach(i -> {

            if(input[i] == sorted[i]){
                correctPositionCnt.getAndIncrement();
            }
        });

        System.out.println("Number of moves: "+ (input.length - correctPositionCnt.get()));
    }
}

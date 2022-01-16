package com.core_java_practice.multithread.examples.sorting;

import java.util.Arrays;
import java.util.List;

public class MultiThreadSort {

    public static void main(String[] args) {
        Integer[] input = {100, 20, 30, 4, 101, 108, 56, 7079, 1081, 17, 909, 45, 67, 8, 12, 3, 100, 20, 30, 4, 111, 56, 7777, 1001, 1, 23, 36, 99, 9999, 45, 67, 8, 12, 3};
        MergeSort mergeSort = new MergeSort();

        Integer[] firstInput = Arrays.copyOfRange(input, 0, input.length / 2);
        Integer[] secondInput = Arrays.copyOfRange(input, input.length / 2, input.length);

        SortThread firstSegment = new SortThread(firstInput, mergeSort);
        SortThread secondSegment = new SortThread(secondInput, mergeSort);
        Thread first = new Thread(firstSegment, "merge-sort-001");
        Thread second = new Thread(secondSegment, "merge-sort-002");

        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread :: " + first.getName() + "::" + "first-segment :: " + firstSegment.getOutput().toString());
        System.out.println("Thread :: " + second.getName() + "::" + "second-segment :: " + secondSegment.getOutput().toString());

        Integer[] firstOutputArray = firstSegment.getOutput().toArray(new Integer[firstSegment.getOutput().size()]);
        Integer[] secondOutputArray = secondSegment.getOutput().toArray(new Integer[secondSegment.getOutput().size()]);
        Integer[] finalOutput = new Integer[firstOutputArray.length + secondOutputArray.length];

        System.out.println("Before :: "+Arrays.asList(finalOutput).toString());
        Thread forwardThread = new Thread(new ForwardMerge(firstOutputArray, secondOutputArray, finalOutput), "forward-thread");
        Thread backwardThread = new Thread(new BackwardMerge(firstOutputArray, secondOutputArray, finalOutput), "backward-thread");
        try {
            forwardThread.start();
            backwardThread.start();

            forwardThread.join();
            backwardThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("After :: "+Arrays.asList(finalOutput).toString());
    }
}

class SortThread implements Runnable {

    Sort<Integer> sort;
    Integer[] input;
    List<Integer> output;

    public SortThread(Integer[] input, Sort sort){
        this.sort = sort;
        this.input = input;
    }
    @Override
    public void run() {
         output = sort.sort(Arrays.asList(input));
    }

    public List<Integer> getOutput(){
        return this.output;
    }
}

class ForwardMerge implements Runnable{

    Integer[] first;
    Integer[] second;
    Integer[] output;

    public ForwardMerge(Integer[] first,Integer[] second, Integer[] output){
        this.first = first;
        this.second = second;
        this.output = output;
    }

    @Override
    public void run() {
        int mid = (first.length + second.length) / 2;

        for (int i=0,p=0,q=0; i<=mid;i++) {

            if (first[p] <= second[q]) {
                output[i] = first[p];
                p++;
            } else {
                output[i] = second[q];
                q++;
            }
        }
    }
}

class BackwardMerge implements Runnable {

    Integer[] first;
    Integer[] second;
    Integer[] output;

    public BackwardMerge(Integer[] first,Integer[] second, Integer[] output){
        this.first = first;
        this.second = second;
        this.output = output;
    }

    @Override
    public void run() {

        int mid = (first.length + second.length) / 2;
        for (int i = ((first.length + second.length) - 1), p = (first.length - 1), q = (second.length - 1); i > mid; i--) {

            if (first[p] >= second[q]) {
                output[i] = first[p];
                p--;
            } else {
                output[i] = second[q];
                q--;
            }
        }
    }
}




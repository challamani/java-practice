package com.practice.ds.sorting;

import com.practice.MyTechHub;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort implements MyTechHub {

    private final DisplayThread displayThread;
    public MergeSort(DisplayThread displayThread){
        this.displayThread = displayThread;
    }

    private void sort(int[] elements)  {
        int[] leftMax = returnLeftMax(elements);
        int[] rightMax = returnRightMax(elements);

        mergeSort(elements, 0,
                elements.length -1,
                leftMax,
                rightMax);
    }

    private void mergeSort(int[] elements, int minIndex, int maxIndex
        ,int[] leftMax,int[] rightMax) {

        if (minIndex < maxIndex && (maxIndex - minIndex) > 1) {
            int mid = (minIndex + maxIndex) / 2;
            mergeSort(elements, minIndex, mid, leftMax, rightMax);
            mergeSort(elements, (mid + 1), maxIndex, leftMax, rightMax);

            mergeValues(elements, minIndex, maxIndex,
                    ((minIndex + maxIndex) / 2),
                    leftMax, rightMax);

        } else if (elements[minIndex] > elements[maxIndex]) {
            displayThread.displayArray(elements, leftMax, rightMax, minIndex, maxIndex);
            int value = elements[minIndex];
            elements[minIndex] = elements[maxIndex];
            elements[maxIndex] = value;
            displayThread.displayArray(elements, leftMax, rightMax);
        }
    }

    private void mergeValues(int[] input,
                             int start,
                             int end,
                             int mid,
                             int[] leftMax,
                             int[] rightMax) {
        int[] sortedArray = new int[(end - start) + 1];
        try {
            int left = start;
            int right = (mid + 1);
            int index = 0;

            while (index <= (end - start)) {

                //int[] newArray =  getCustomMerge(start,end,index,left,right,mid,sortedArray,input);

                if ((left <= mid && right <= end)
                        && (input[left] > input[right])) {
                    displayThread.displayArray(input, leftMax, rightMax,
                            left, right);
                    sortedArray[index] = input[right];
                    right++;
                } else if ((left <= mid && right <= end)
                        && (input[left] <= input[right])) {
                    displayThread.displayArray(input, leftMax, rightMax,
                            left, right);
                    sortedArray[index] = input[left];
                    left++;
                } else if (right <= end) {
                    sortedArray[index] = input[right];
                    right++;
                } else if (left <= mid) {
                    sortedArray[index] = input[left];
                    left++;
                }
                index++;
            }

            for (int ind = start, i = 0; ind <= end; ind++, i++) {
                input[ind] = sortedArray[i];
            }
            displayThread.displayArray(input, leftMax, rightMax);

        } catch (Exception e) {
            System.out.println("failed at merge sorting: " + e.getMessage());
        }
    }

    private int[] getCustomMerge(int start,
                                 int end,
                                 int index,
                                 int left,
                                 int right,
                                 int mid,
                                 int[] sortedArray,
                                 int[] source) {

        int[] newArray = new int[source.length];
        for (int i=0; i < source.length; i++){
            newArray[i] = source[i];
        }

        for (int ind = start, i = 0; i < index; ind++, i++) {
            newArray[ind] = sortedArray[i];
        }

        for (int ind = left; ind < mid; ind++) {
            newArray[ind] = source[ind];
        }

        for (int ind = right; ind < end; ind++) {
            newArray[ind] = source[ind];
        }
        return newArray;
    }

    public static void main(String[] args) {

        DisplayThread displayThread = new DisplayThread();
        MergeSort mergeSort = new MergeSort(displayThread);
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREEN_COLOR + "Enter number of elements:?");
        int size = scanner.nextInt();
        int[] elements = new int[size];
        List<Integer> listElements = IntStream.range(3, 40)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(listElements);
        for (int i = 0; i < size; i++) {
            elements[i] = listElements.get(i);
        }
        mergeSort.sort(elements);
        scanner.close();
    }

}

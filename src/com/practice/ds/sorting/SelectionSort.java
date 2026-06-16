package com.practice.ds.sorting;

import com.practice.MyTechHub;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectionSort implements MyTechHub {

    private final DisplayThread displayThread;
    public SelectionSort(DisplayThread displayThread){
        this.displayThread = displayThread;
    }
    private void sort(int[] elements){
        int minIndex;
        int temp;
        int length = elements.length;
        int[] leftMax = returnLeftMax(elements);
        int[] rightMax = returnRightMax(elements);

        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i+1; j < length; j++) {
                if (elements[j] < elements[minIndex]) {
                    minIndex=j;
                }
            }
            if (minIndex != i) {
                displayThread.displayArray(elements, leftMax, rightMax, i, minIndex);
                temp = elements[minIndex];
                elements[minIndex] = elements[i];
                elements[i] = temp;
                displayThread.displayArray(elements, leftMax, rightMax);
            }
        }
    }

    public static void main(String[] args) {

        DisplayThread displayThread = new DisplayThread();
        SelectionSort selectionSort = new SelectionSort(displayThread);
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
        selectionSort.sort(elements);
        scanner.close();
    }
}

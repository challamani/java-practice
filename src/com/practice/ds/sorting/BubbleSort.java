package com.practice.ds.sorting;

import com.practice.MyTechHub;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BubbleSort implements MyTechHub {

    private final DisplayThread displayThread;
    public BubbleSort(DisplayThread displayThread){
        this.displayThread =  displayThread;
    }
    private void sort(int[] elements){
        boolean swapped;
        int temp;
        int length = elements.length;
        int[] leftMax = returnLeftMax(elements);
        int[] rightMax  = returnRightMax(elements);

        for (int i = 0; i < length - 1; i++) {
            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    displayThread.displayArray(elements, leftMax, rightMax,
                            j, j + 1);
                    temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                    displayThread.displayArray(elements, leftMax, rightMax);
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        DisplayThread displayThread = new DisplayThread();
        BubbleSort bubbleSort = new BubbleSort(displayThread);
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
        bubbleSort.sort(elements);
        scanner.close();
    }
}

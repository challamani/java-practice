package com.practice.threads.misc;

import com.practice.MyTechHub;

import java.util.Arrays;

public class MergeArrays implements MyTechHub {

    public static void main(String[] args) {

        int[] array1 =  new int[101];
        int index=0;
        for(int i=1; i<=210 && index <= 100; i+=2){
            array1[index++]=i;
        }

        int[] array2 =  new int[101];
        index=0;
        for(int i=2; i<=210 && index <= 100; i+=2){
            array2[index++]=i;
        }

        ArraySource arraySource = new ArraySource(array1, array2);

        Thread backwardMerger =  new Thread(
                new BackwardMergerThread(arraySource));
        backwardMerger.start();

        Thread forwardMerger =  new Thread(
                new ForwardMergerThread(arraySource));
        forwardMerger.start();
    }

    public static void printWithColor(ArraySource arraySource){
        int firstIndexOfZero=Integer.MAX_VALUE;
        StringBuilder stringBuilder =  new StringBuilder(MyTechHub.YELLOW_COLOR);

        for(int i = 0; i< arraySource.getResultArray().length; i++) {
            if (arraySource.getResultArray()[i] != 0) {
                stringBuilder.append(String.format("%3d",
                        arraySource.getResultArray()[i])).append(",");
            } else {
                firstIndexOfZero = i;
                stringBuilder.append("|").append(MyTechHub.WHITE_COLOR);
                break;
            }
        }

        int lastIndexOfZero = firstIndexOfZero;
        for(int i = firstIndexOfZero; i < arraySource.getResultArray().length; i++){
            if(arraySource.getResultArray()[i] == 0){
                stringBuilder.append(arraySource.getResultArray()[i]).append(",");
            }else{
                lastIndexOfZero = i;
                break;
            }
        }

        stringBuilder.append(MyTechHub.GREEN_COLOR).append("|");
        for(int i = lastIndexOfZero; i < arraySource.getResultArray().length ; i++) {
            stringBuilder.append(String.format("%3d",
                    arraySource.getResultArray()[i])).append(",");
        }
        System.out.println(stringBuilder);
    }
}

class ArraySource {
    private final int[] array1;
    private final int[] array2;
    private final int[] resultArray;

    public ArraySource(int[] inputArray1, int[] inputArray2) {
        array1 = inputArray1;
        array2 = inputArray2;
        resultArray = new int[array1.length + array2.length];
        Arrays.fill(resultArray, 0);
    }

    public int[] getArray1() {
        return array1;
    }

    public int[] getArray2() {
        return array2;
    }

    public int[] getResultArray() {
        return resultArray;
    }
}

class ForwardMergerThread implements Runnable, MyTechHub{

    private final ArraySource arraySource;
    public ForwardMergerThread(ArraySource arraySource) {
        this.arraySource = arraySource;
    }
    @Override
    public void run() {

        int limit = (arraySource.getResultArray().length / 2);
        int p = 0;
        int q = 0;

        for (int i = 0; i < limit; i++) {
            delay(100);
            if (arraySource.getArray1()[p] <= arraySource.getArray2()[q]) {
                arraySource.getResultArray()[i] = arraySource.getArray1()[p];
                ++p;
                continue;
            }
            arraySource.getResultArray()[i] = arraySource.getArray2()[q];
            ++q;

            clearScreen();
            MergeArrays.printWithColor(arraySource);
        }
        clearScreen();
        MergeArrays.printWithColor(arraySource);
    }
}

/*
* TODO
*  Merge two sorted arrays using threads
*  One thread is responsible for forward merging
*  One thread is responsible for backward merging
* */

class BackwardMergerThread implements Runnable, MyTechHub {

    private final ArraySource arraySource;
    public BackwardMergerThread(ArraySource arraySource){
        this.arraySource = arraySource;
    }

    @Override
    public void run(){
        int[] result = arraySource.getResultArray();
        int limit = result.length / 2;

        int[] left = arraySource.getArray1();
        int[] right = arraySource.getArray2();
        int l = left.length -1;
        int r = right.length -1;

        for (int i=result.length-1; i>=limit; i--){
            if(left[l] >= right[r]){
                result[i] = left[l];
                l--;
                continue;
            }
            result[i] = right[r];
            r--;
            clearScreen();
            MergeArrays.printWithColor(arraySource);
            delay(100);
        }
        clearScreen();
        MergeArrays.printWithColor(arraySource);
    }
}






























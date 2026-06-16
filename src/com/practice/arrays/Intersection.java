package com.practice.arrays;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

    public static void main(String[] args) {

        int[] input1 = new int[] {1, 2, 3, 4, 5, 9};
        int[] input2 = new int[] {4, 5, 19, 20};

        int i=0;
        int j=0;
        List<Integer> result = new ArrayList<>();

        while(i < input1.length && j < input2.length){

            if(input1[i] == input2[j] ){
                result.add(input1[i]);
                i++;
                j++;
            }else if (input1[i] < input2[j]){
                i++;
            }else {
                j++;
            }
        }

        System.out.println("intersection elements: "+result);
    }
}

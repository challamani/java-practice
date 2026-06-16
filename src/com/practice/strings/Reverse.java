package com.practice.strings;

import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) {

        System.out.println("Enter a string: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        sc.close();
        char[] chars = input.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left <= right) {

            char temp = chars[right];
            chars[right] = chars[left];
            chars[left] = temp;
            left++;
            right--;
        }

        System.out.println("Reverse string of the given is:" + String.valueOf(chars));
    }
}

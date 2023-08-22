package com.practice.misc;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any integer:");
        int value = scanner.nextInt();
        StringBuilder derived = new StringBuilder();
        int actual = value;
        while (value !=0){
            derived.append(value%10);
            value = value/10;
        }
        if(Integer.toString(actual)
                .equals(derived.toString())){
            System.out.printf("\nThe given number %d" +
                    " is a Palindrome\n", actual);
        }else{
            System.out.printf("\nThe given number %d" +
                    " is not a Palindrome\n", actual);
        }
    }
}

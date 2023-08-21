package com.practice.arrays;


import java.util.Scanner;

public class EvenOrOdd{

    public static void main(String[] args) {
        String[] evenOrOdd = {"Even", "Odd"};
        Scanner scanner = new Scanner(System.in);
        String option;
        do{
            //One line code to find even or odd;
            System.out.println("Enter any integer value:");
            int value = scanner.nextInt();
            System.out.printf("The given number %d is %s",
                    value,
                    evenOrOdd[value % 2]);
            System.out.println("\nDo you wanna continue!");
            option = scanner.next();
        }while (option.equals("Y"));

    }
}

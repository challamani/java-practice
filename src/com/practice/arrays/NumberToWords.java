package com.practice.arrays;

import java.util.Scanner;

public class NumberToWords {

    static String[][] words = {
            {"zero", "one", "two", "three", "four",
                    "five", "six", "seven", "eight",
                    "nine", "ten", "eleven", "twelve",
                    "thirteen", "fourteen", "fifteen",
                    "sixteen", "seventeen", "eighteen",
                    "nineteen"},

            {" ", "ten", "twenty", "thirty", "forty",
                    "fifty", "sixty", "seventy",
                    "eighty", "ninety"},

            {" ", " ", "ten", "hundred", "thousand",
                    "thousand", "thousand", "million",
                    "million", "million", "billion",
                    "billion"}
    };
    static Integer[] divisor = {0, 1, 10, 100, 1000, 1000,
            1000, 1000000, 1000000, 1000000, 1000000000};


    public static String recursive(long value,
                                   StringBuilder wordBuilder) {
        if (value == 0) {
            return "";
        }

        if (value >= 100) {
            int len = Integer.toString((int) value)
                    .length();
            int quotient = (int) (value / divisor[len]);

            wordBuilder.append(" ")
                    .append(recursive(quotient,
                            new StringBuilder()).trim())
                    .append(" ")
                    .append(words[2][len]);

            wordBuilder.append(" ")
                    .append(recursive(
                            value % divisor[len],
                            new StringBuilder()).trim())
                    .append(" ");

        } else if (value >= 20) {
            int quotient = (int) (value / 10);
            wordBuilder.append(words[1][quotient]);
            int reminder = (int) (value % 10);
            if (reminder != 0) {
                wordBuilder.append(" ")
                        .append(words[0][reminder]);
            }
        } else {
            return words[0][(int) value];
        }
        return wordBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long value = scanner.nextLong();
        String result = recursive(value,
                new StringBuilder());
        System.out.println(result);
    }
}

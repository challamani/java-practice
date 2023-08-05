package com.practice.arrays;

import com.practice.MyTechHub;

import java.util.Scanner;


public class PascalTriangle implements MyTechHub {

    public void printPascalTriangle(int range){

        for(int n=0; n<range; n++){
            //display management
            for(int j=0; j<range-n; j++){
                System.out.print(" ");
            }
            //Calculate the nCr value
            //n!/(n-r)!*r!
            for(int r=0; r<=n; r++){
                System.out.print(" "+factorial(n)/
                        (factorial(n-r)*factorial(r)));
            }
            System.out.println();
        }
    }
    //recursive function to calculate the factorial
    //n!= n*(n-1)*(n-2)*....1
    private int factorial(int n){
        if(n == 0){
            return 1;
        }
        return n * factorial(n-1);
    }

    //Print pascal triangle using arrays
    public void approach2(int range){
        //declare an array
        int[][] ncr = new int[range][];
        //init array size for each row
        for(int i=0; i<range; i++){
            ncr[i] = new int[i+1];
        }

        //calculate the pascal triangle values
        for(int n=0; n<range; n++){
            for(int r=0; r<=n; r++){
                if(r ==0 || r == n){
                    ncr[n][r] = 1;
                }else{
                    ncr[n][r] = (ncr[n-1][r-1]+ncr[n-1][r]);
                }
            }
        }

        //print - pascal triangle values
        for(int n=0; n<range; n++){
            //display alignment
            for(int x=0; x<range-n; x++){
                System.out.print(" ");
            }
            for (int r=0; r<=n; r++){
                System.out.print(" "+ncr[n][r]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int range = scanner.nextInt();
        new PascalTriangle()
                .approach2(range);
    }
}

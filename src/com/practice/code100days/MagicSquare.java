package com.practice.code100days;

import com.practice.MyTechHub;

import java.util.Scanner;

public class MagicSquare implements MyTechHub {

    public MagicSquare(){
    }

    public int[][] createMagicMatrix(int size){
        //init two-dimensional array
        int[][] matrix = new int[size][size];

        int element=1;
        int maxElement =  size*size;
        //find the position for starting element
        int row = size-1;
        int column = size/2;
        matrix[row][column] = element;
        while (++element <= maxElement){
            //in this approach we look for downwards diagonal position
            row += 1;
            column += 1;
            //row out of range
            if(row >= size && column < size){
                row = 0;
            }else if(column >= size && row < size){
                //when column out of range
                column=0;
            }else if(row >= size && column >= size){
                //column and row out of range case
                row -= 2;
                column -= 1;
            }
            //if already cell filled with any element
            if(matrix[row][column] != 0){
                row -= 2;
                column -= 1;
            }
            matrix[row][column] = element;
            printMatrix(matrix,row, column);
        }
        return matrix;
    }

    protected void printMatrix(int[][] matrix, int row, int col){
        delay(200);
        clearScreen();
        System.out.print(WHITE_COLOR);
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(row == i && col == j){
                    System.out.print(GREEN_COLOR+String.format(" |%3d| ", matrix[i][j]));
                    System.out.print(WHITE_COLOR);
                }else {
                    System.out.printf(" |%3d| ", matrix[i][j]);
                }
            }
            System.out.println("\n");
        }
    }

    protected void validateMatrix(int[][] matrix){
        clearScreen();
        System.out.print(WHITE_COLOR);
        int[] colSum = new int[matrix.length];

        for(int i=0; i< matrix.length; i++) {
            int rowSum = 0;
            System.out.print(WHITE_COLOR);
            for (int j = 0; j < matrix[i].length; j++) {
                colSum[j] += matrix[i][j];
                rowSum += matrix[i][j];
                System.out.printf(" |%3d| ", matrix[i][j]);
            }
            System.out.print(GREEN_COLOR);
            System.out.printf(" %3d", rowSum);
            System.out.println("\n");
        }

        for(int i=0; i<colSum.length; i++){
            System.out.printf(" |%3d| ",colSum[i]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter magic matrix size :");
        int size = scanner.nextInt();
        System.out.printf("Magic Matrix size %d%n", size);
        MagicSquare magicSquare = new MagicSquare();
        int[][] matrix = magicSquare.createMagicMatrix(size);
        magicSquare.validateMatrix(matrix);
        scanner.close();
    }
}

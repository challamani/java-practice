package com.practice.ds.dp;

public class MinimumPath2D {

    public int calculateAndReturnMinimumPath(int[][] grid){

        int rowLength = grid.length;
        int colLength = grid[0].length;

        int[][] dp = new int[rowLength][colLength];
        dp[0][0] = grid[0][0];

        for(int row=1; row<rowLength; row++){
            dp[row][0] = dp[row-1][0] + grid[row][0];
        }

        for(int col=1; col<colLength;col++){
            dp[0][col] = dp[0][col-1] + grid[0][col];
        }

        for(int row=1; row<rowLength; row++){
            for(int col=1; col<colLength; col++){
                dp[row][col] = Math.min(dp[row-1][col], dp[row][col-1]) + grid[row][col];
            }
        }
        return dp[rowLength-1][colLength-1];
    }

    public static void main(String[] args){

        MinimumPath2D minimumPath2D = new MinimumPath2D();
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int result = minimumPath2D.calculateAndReturnMinimumPath(grid);
        System.out.println("Minimum path sum is: "+result);
    }
}

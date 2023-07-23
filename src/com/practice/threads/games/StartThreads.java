package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartThreads implements MyTechHub {
    @Override
    public String getProblem() {
        return "Start threads to see impressive movement on console";
    }

    @Override
    public String getApproach() {
        return "Start arbitrary threads with a dedicated role";
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : " + ex.getMessage());
        }
        List<Integer> columns = new ArrayList<>();
        for (int col = 2; col <= 50; col += 3) {
            columns.add(col);
        }
        Collections.shuffle(columns);
        for(int col: columns){
            Thread topToBottom = new VerticalThread(
                    "down",
                    1,
                    col,
                    '0',
                    5);
            topToBottom.start();
            Thread.sleep(100);

            Thread bottomToTop = new VerticalThread(
                    "up",
                    40,
                    col+1,
                    '1',
                    5);
            bottomToTop.start();
        }
    }
}

class VerticalThread extends  Thread implements MyTechHub {

    private final String direction;
    private final int row;
    private final int column;
    private final char ch;
    private final int maxIterations;

    public VerticalThread(String direction, int row,
                          int column,
                          char ch,
                          int maxIterations){
        this.direction = direction;
        this.row = row;
        this.column = column;
        this.maxIterations = maxIterations;
        this.ch = ch;
    }

    @Override
    public void run(){
        int index=0;
        char escChar = 0x1B;
        while (++index <= maxIterations){
            int localRow = this.row;
            String color = getColor(index);
            if(this.direction.equals("up")){
                while (localRow-- >= 1){
                    System.out.printf("%s%c[%d;%df %c",
                            color,
                            escChar,
                            localRow,
                            column,
                            ch);
                    delay(50);
                }
            }else{
                while (localRow++ <= 40){
                    System.out.printf("%s%c[%d;%df %c",
                            color,
                            escChar,
                            localRow,
                            column,
                            ch);
                    delay(50);
                }
            }
        }
    }

    @Override
    public String getProblem() {
        return null;
    }

    @Override
    public String getApproach() {
        return null;
    }

}


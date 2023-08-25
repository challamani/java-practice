package com.practice.threads.games;

import com.practice.MyTechHub;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CharRenderThread extends Thread
 implements  MyTechHub{

    private final int row;
    private final int col;
    private final int maxIterations;
    private final String color;

    public CharRenderThread(int row,
                            int col,
                            int maxIterations,
                            String color){
        this.row = row;
        this.col = col;
        this.maxIterations = maxIterations;
        this.color = color;
    }

    @Override
    public void run(){
        int index=0;
        char escCode =  0x1B;
        List<Integer> intValues = IntStream.range(32,126)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(intValues);
        int size =  intValues.size();
        while (++index <= maxIterations){
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    row,
                    col,
                    (char) intValues.get(index % size)
                            .intValue());
            delay(30);
            System.out.printf("%s %c[%d;%df %c",
                    color,
                    escCode,
                    row,
                    col,
                    ' ');
        }
    }

    public static void main(String[] args) {
        //TODO let us start 1200 threads
        IntStream.range(1, 40).forEach(row ->{
            for(int col=2; col<=60; col+=2){
                String color = COLOR_MAP.get(
                        new Random().nextInt(9)
                );
                new CharRenderThread(row,col,
                        2000,color)
                        .start();
                try{
                    Thread.sleep(30);
                }catch (InterruptedException e){

                }
            }
        });
    }

}
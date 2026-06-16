package com.practice.threads.flags;

import com.practice.MyTechHub;
import com.practice.threads.util.PaintThread;
import com.practice.threads.util.TinyToy;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Janasena implements MyTechHub {

    public static void main(String[] args) throws InterruptedException {

        //మనల్ని ఎవడ్రా ఆపేది
        int row=10;
        int colum=10;
        char escCode=0x1B;
        for(String str: new String[]{"J", "A", "N", "A", "S", "E", "N", "A"}) {
            colum +=2;
            System.out.printf("%s %c[%d;%df %s",
                    MyTechHub.WHITE_COLOR,
                    escCode,
                    row,
                    colum,
                    str);
            Thread.sleep(500);
        }
        System.out.println();
        String[] teluguLetters = {"మ","న","ల్ని","  ","ఎ","వ","డ్రా"," ","ఆ","పే","ది "};
        //System.out.println(Arrays.toString(teluguLetters));
        Thread special = new JanasenaSpecial(teluguLetters, 15, 10);
        special.start();
        special.join();

        Thread.sleep(1000);
        clear();
        Thread poll  = new PaintThread(50,
                15,"▓",
                "UP",
                MyTechHub.WHITE_COLOR,
                100,
                45,1);
        poll.start();
        poll.join();

        new PaintThread(5,
                16,"✡",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();
        new PaintThread(6,
                16,"✡",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();
        new PaintThread(7,
                16,"✡",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();


        new PaintThread(9,
                16,"✡",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();
        new PaintThread(10,
                16,"✡",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();
        new PaintThread(11,
                16,"✡",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();

        new PaintThread(13,
                16,"✡",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();
        new PaintThread(14,
                16,"✡",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();

        new PaintThread(15,
                16,"✡",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();
    }

    private static void clear() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }
}

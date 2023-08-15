package com.practice.threads.flags;

import com.practice.MyTechHub;
import com.practice.threads.util.ArrowThread;
import com.practice.threads.util.PaintThread;
import com.practice.threads.util.TinyToy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class India implements MyTechHub {

    public static void main(String[] args) throws InterruptedException {

        char[] chars = "HAPPY INDEPENDENCE DAY".toCharArray();
        Thread independenceDay = new IndependenceDay(chars, 25, 10);
        independenceDay.start();

        independenceDay.join();
        clear();

        Thread poll  = new PaintThread(50,
                15,"▓▓▓",
                "UP",
                MyTechHub.WHITE_COLOR,
                100,
                45,1);
        poll.start();
        poll.join();

        new PaintThread(5,
                17,"▓",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();
        new PaintThread(6,
                17,"▓",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();
        new PaintThread(7,
                17,"▓",
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                100,
                15,2).start();


        new PaintThread(9,
                17,"▓",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();
        new PaintThread(10,
                17,"▓",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();
        new PaintThread(11,
                17,"▓",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                100,
                15,2).start();

        new PaintThread(13,
                17,"▓",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();
        new PaintThread(14,
                17,"▓",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();

        new PaintThread(15,
                17,"▓",
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                100,
                15,2).start();


        IntStream.of(22,28,34,40,46,52).forEach(col -> {
            IntStream.of(18,21,24).forEach(row -> new TinyToy(row, col, 30,
                    MyTechHub.YELLOW_COLOR)
                    .start());
            IntStream.of(27,30,33).forEach(row -> new TinyToy(row, col, 30,
                    MyTechHub.WHITE_COLOR)
                    .start());
            IntStream.of(36,39,42).forEach(row -> new TinyToy(row, col, 30,
                    MyTechHub.GREEN_COLOR)
                    .start());
        });

    }

    private static void clear() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }

    private static void delaySec(int milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException ie) {
            System.out.println("running thread got interrupted:" + ie.getMessage());
        }
    }
}

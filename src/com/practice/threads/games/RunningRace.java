package com.practice.threads.games;

import com.practice.MyTechHub;
import com.practice.threads.util.*;

import java.util.concurrent.atomic.AtomicInteger;


public class RunningRace implements MyTechHub {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger(0);

        int range = 27;
        Thread poll  = new PaintThread(40,
                28,"▓",
                "UP",
                MyTechHub.WHITE_COLOR,
                30,
                40,1);

        poll.start();
        poll.join();
        Thread line1  = new PaintThread(7,
                2,"-",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                10,
                15,2);

        line1.start();
        Thread line2  = new PaintThread(11,
                2,"-",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                10,
                15,2);
        line2.start();

        Thread line3  = new PaintThread(15,
                2,"-",
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                10,
                15,2);
        line3.start();

        line1.join();
        line2.join();
        line3.join();

        RunningPlayer player1 = new RunningPlayer(
                5,
                2,
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                range,
                atomicInteger);
        player1.start();

        RunningPlayer player2 = new RunningPlayer(
                9,
                2,
                "RIGHT",
                MyTechHub.YELLOW_COLOR,
                range,
                atomicInteger);
        player2.start();

        RunningPlayer player3 = new RunningPlayer(
                13,
                2,
                "RIGHT",
                MyTechHub.GREEN_COLOR,
                range,
                atomicInteger);
        player3.start();

        RunningPlayer player4 = new RunningPlayer(
                17,
                2,
                "RIGHT",
                MyTechHub.RED_COLOR,
                range,
                atomicInteger);
        player4.start();

        player3.join();
        player4.join();
        player2.join();
        player1.join();

        Thread.sleep(300);
    }
}



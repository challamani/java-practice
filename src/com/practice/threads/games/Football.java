package com.practice.threads.games;

import com.practice.MyTechHub;
import com.practice.threads.util.DiagonalThread;
import com.practice.threads.util.PlayerThread;
import com.practice.threads.util.SharedItem;


public class Football implements MyTechHub {

    public static void main(String[] args) {

        //웃유
        //⚽🏀
        PlayerThread player1 = new PlayerThread(
                        5,
                        10,
                        "RIGHT",
                        MyTechHub.WHITE_COLOR,
                        30,
                        25);
        player1.start();
        PlayerThread player2 = new PlayerThread(
                30,
                35,
                "LEFT",
                MyTechHub.WHITE_COLOR,
                30,
                25);
        player2.start();
        //football object
        SharedItem sharedItem = new SharedItem("\uD83C\uDFC0");
        DiagonalThread football = new DiagonalThread(
                5,
                10,
                sharedItem,
                "DOWN",
                MyTechHub.WHITE_COLOR,
                131,
                25);

        football.start();
        football = new DiagonalThread(
                30,
                35,
                sharedItem,
                "UP",
                MyTechHub.WHITE_COLOR,
                131,
                25);
        football.start();
    }
}



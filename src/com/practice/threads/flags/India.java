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

    public static void main(String[] args) {

        IntStream.of(5,10,15,20,25,30,35,40,45).forEach(col -> {

            IntStream.of(5,8,11,14).forEach(row -> new TinyToy(row, col, 45,
                    MyTechHub.YELLOW_COLOR)
                    .start());

            IntStream.of(18,21,24,27).forEach(row -> new TinyToy(row, col, 45,
                    MyTechHub.WHITE_COLOR)
                    .start());

            IntStream.of(31,34,37,40).forEach(row -> new TinyToy(row, col, 45,
                    MyTechHub.GREEN_COLOR)
                    .start());

        });

      /*  List.of('▼','⬇','ꘜ','▼','⟱').forEach(value -> {
            ArrowThread yellow = new ArrowThread(5,
                    18,value,
                    "DOWN",
                    MyTechHub.YELLOW_COLOR,
                    50,
                    50);

            ArrowThread white = new ArrowThread(5,
                    26,value,
                    "DOWN",
                    MyTechHub.WHITE_COLOR,
                    50,
                    50);

            ArrowThread green = new ArrowThread(5,
                    36,value,
                    "DOWN",
                    MyTechHub.GREEN_COLOR,
                    50,
                    50);

            yellow.start();
            white.start();
            green.start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        List<Integer> intValues =
                IntStream.range(18, 43).boxed().collect(Collectors.toList());
        intValues.addAll(IntStream.range(18, 43).boxed().toList());
        Collections.shuffle(intValues);
        intValues.forEach(column -> {
            if (column < 25) {
                new PaintThread(5,
                        15,"❄❄❄✡❋❄❄✡",
                        "DOWN",
                        MyTechHub.YELLOW_COLOR,
                        100,
                        50,1).start();

            } else if (column <= 33) {
                new PaintThread(5,
                        25,"❄❄❄✡❋❄❄✡",
                        "DOWN",
                        MyTechHub.WHITE_COLOR,
                        100,
                        50,1).start();
            } else {
                new PaintThread(5,
                        35,"❄❄❄✡❋❄❄✡",
                        "DOWN",
                        MyTechHub.GREEN_COLOR,
                        100,
                        50,1).start();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        new PaintThread(5,
                15,"        ",
                "DOWN",
                MyTechHub.YELLOW_COLOR,
                100,
                50,1).start();
        new PaintThread(5,
                25,"        ",
                "DOWN",
                MyTechHub.WHITE_COLOR,
                100,
                50,1).start();

        new PaintThread(5,
                35,"         ",
                "DOWN",
                MyTechHub.GREEN_COLOR,
                100,
                50,1).start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Thread poll  = new PaintThread(50,
                15,"▓▓▓",
                "UP",
                MyTechHub.WHITE_COLOR,
                100,
                45,1);
        poll.start();
        try {
            poll.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
                15,2).start();*/

    }
}

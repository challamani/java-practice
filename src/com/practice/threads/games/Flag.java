package com.practice.threads.games;

import com.practice.MyTechHub;
import com.practice.threads.util.CharPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Flag implements MyTechHub {

    public static void main(String[] args)
            throws InterruptedException {

        Map<String, List<CharPosition>> mapOfPositions = new HashMap<>();
        mapOfPositions.put("top", new CopyOnWriteArrayList<>());
        mapOfPositions.put("middle", new CopyOnWriteArrayList<>());
        mapOfPositions.put("bottom", new CopyOnWriteArrayList<>());

        buildFlag(mapOfPositions, '₹');

        FallThread fallThread = new FallThread(mapOfPositions.values()
                .stream().flatMap(List::stream).collect(Collectors.toList()));
        fallThread.start();
        fallThread.join();
        for (Thread thread : fallThread.getChildThreads()) {
            thread.join();
        }

        buildFlag(mapOfPositions, '♥');
        Thread.sleep(3000);
    }

    private static void buildFlag(Map<String, List<CharPosition>> mapOfPositions
    ,char displayChar) throws InterruptedException {
        Thread top = new BuildRectangle(5, 50,
                10, 5, mapOfPositions.get("top"), "\u001B[1;33m",
                displayChar, false);

        Thread middle = new BuildRectangle(5, 50,
                16, 5, mapOfPositions.get("middle"), "\u001B[37m",
                displayChar, true);

        Thread bottom = new BuildRectangle(5, 50,
                22, 5, mapOfPositions.get("bottom"), "\u001B[32m",
                displayChar, false);
        top.start();
        middle.start();
        bottom.start();

        top.join();
        middle.join();
        bottom.join();
    }
}

class BuildRectangle extends Thread implements MyTechHub{

    private final int height;
    private final int width;
    private final int row;
    private final int col;
    private List<CharPosition> charPositions;
    private String color;
    private char ch;
    private boolean fillInOpposite;
    public BuildRectangle(int height, int width, int row, int col,
                      List<CharPosition> charPositions, String color,
                          char ch,
                          boolean isOpposite){
        this.height=height;
        this.width=width;
        this.row=row;
        this.col=col;
        this.charPositions =  charPositions;
        this.color = color;
        this.ch = ch;
        this.fillInOpposite = isOpposite;
    }
    @Override
    public void run() {
        char escChar = 0x1B;
        int index = 0;
        clearScreen();

        int localRow = row;
        while (index++ <= height) {
            if (fillInOpposite) {
                for (int i = (col + width); i >= col; i -= 2) {
                    System.out.printf("%s %c[%d;%df %c",
                            color,
                            escChar,
                            localRow,
                            i, ch);
                    charPositions.add(new CharPosition(localRow, i, ch, color));
                    delay(30);
                }
            } else {
                for (int i = col; i <= (col + width); i += 2) {
                    System.out.printf("%s %c[%d;%df %c",
                            color,
                            escChar,
                            localRow,
                            i, ch);
                    charPositions.add(new CharPosition(localRow, i, ch, color));
                    delay(30);
                }
            }
            localRow += 1;
        }
    }
}

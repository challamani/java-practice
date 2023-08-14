package com.practice.threads.util;

public class CharPosition {
    private int row;
    private int col;
    private char ch;
    private String color;

    public CharPosition(int row, int col, char ch, String color) {
        this.row = row;
        this.col = col;
        this.ch = ch;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getCh() {
        return ch;
    }
}

package com.practice.threads.util;

public class SharedItem {
    private final String display;
    public SharedItem(String display){
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
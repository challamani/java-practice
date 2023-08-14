package com.practice.code100days;

import com.practice.MyTechHub;

import java.util.Scanner;

public class Stack implements MyTechHub {

    //Stack implementation using arrays
    private final int size;
    private int seekIndex;
    private String[] data;

    public Stack(int size){
        this.size =size;
        this.seekIndex = -1;
        this.data = new String[size];
    }

    public void push(String element){
        if(this.seekIndex == size-1){
            throw new RuntimeException("Stack is overflow!");
        }
        if(null != element){
            data[++seekIndex] = element;
        }
    }

    public String pop(){
        if(seekIndex == -1){
            throw new RuntimeException("Stack is empty!");
        }
        return data[seekIndex--];
    }

    public String peek(){
        if(seekIndex == -1){
            throw new RuntimeException("Stack is empty!");
        }
        return data[seekIndex];
    }

    public void display(){
        System.out.println(MyTechHub.GREEN_COLOR+"Stack: ");
        for (int index=seekIndex; index>=0; index--){
            System.out.printf("|  %s |", data[index]);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(MyTechHub.GREEN_COLOR+"Enter stack size: ");

        int size = scanner.nextInt();
        Stack stack = new Stack(size);

        int option;
        do {
            System.out.println("\n1. Push" +
                    " 2. Pop 3. Peek 4. Display" +
                    " 5. Exit\nYour option:");
            option = scanner.nextInt();
            try {
                switch (option) {
                    case 1:
                        System.out.println("Enter element to push:");
                        String element = scanner.next();
                        stack.push(element);
                        break;
                    case 2:
                        System.out.println("Pop element: [" + stack.pop() + ']');
                        break;
                    case 3:
                        System.out.println("Peek/Top element: [" + stack.peek() + ']');
                        break;
                    case 4:
                        stack.display();
                        break;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } while (option <= 4);
    }
}

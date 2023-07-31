package com.practice.ds;

import com.practice.MyTechHub;

import java.util.Scanner;


public class Stack implements MyTechHub {

    public String getProblem() {
        return "Implement the Stack using LinkedList";
    }

    public String getApproach() {
        return "Head node is to define the peek element";
    }

    private Node head;

    public Stack(String[] elements) {
        if (elements == null || elements.length < 1) {
            throw new RuntimeException("Elements not found!");
        }
        head = new Node(elements[elements.length - 1], null);
        Node current = head;
        for (int i = elements.length - 2; i >= 0; i--) {
            Node next = new Node(elements[i], null);
            current.setNext(next);
            current = next;
        }
    }

    public void push(String element) {
        Node node = new Node(element, null);
        node.setNext(head);
        head = node;
    }

    public String pop() {
        if (head == null) {
            return "NULL";
        }
        Node popNode = head;
        head = popNode.getNext();
        return popNode.getData();
    }

    //Peek returns the top element but doesn't remove
    public String peek() {
        //As stack works on LIFO
        if (head != null) {
            return head.getData();
        }
        return "NULL";
    }

    public void display() {
        Node current = head;
        System.out.println("Stack: \n");
        while (current != null) {
            System.out.printf("| %s |\n", current.getData());
            System.out.println("--------");
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(new String[]{"JAVA", "RUBY"});
        stack.displayProblemAndApproach();

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n1. Push" +
                    "\n2. Pop \n3. Peek \n4. Display" +
                    "\n5. Exit\nYour option:");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("\nEnter element to push:");
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
        } while (option <= 4);
        stack.endCard();
    }
}

class Node {
    private String data;
    private Node next;

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

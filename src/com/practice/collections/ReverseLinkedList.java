package com.practice.collections;

public class ReverseLinkedList {


    public static void main(String[] args) {
        Node head = new Node("Apple", null);
        Node current = head;
        String[] elements = {"Orange", "Banana", "Mango", "Grape", "Guava"};

        for (String s : elements) {
            Node temp = new Node(s, null);
            current.setNext(temp);
            current = temp;
        }

        current = head;
        while (current.getNext() != null) {
            System.out.print(current.getData().concat("-->"));
            current = current.getNext();
        }
        System.out.println(current.getData());


        //reverse the linked-list
        current = head;
        Node previous = null;
        while (current != null) {
            Node next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }

        current = previous;
        while (current.getNext() != null) {
            System.out.print(current.getData().concat("-->"));
            current = current.getNext();
        }
        System.out.println(current.getData());
    }
}


class Node{
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
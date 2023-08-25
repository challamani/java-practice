package com.practice.ds;


import com.practice.MyTechHub;

public class ReverseLinkedList implements MyTechHub {

    public String getProblem() {
        return "Reverse the given linked list";
    }

    public String getApproach() {
        return "Traverse the entire linked list and \n" +
                " change the next element accordingly.";
    }

    public Node createLinkedList(String[] elements) {
        Node head = new Node(elements[0], null);
        Node current = head;
        for (int index = 1; index < elements.length; index++) {
            Node next = new Node(elements[index], null);
            current.setNext(next);
            current = next;
        }
        current = head;
        return current;
    }

    public Node reverseLinkedList(Node head) {

        Node current = head;
        Node previous = null;
        while (current != null) {
            Node next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        current = previous;
        return current;
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        String[] elements = {"Apple",
                "Banana",
                "Grapes",
                "Mango",
                "Orange"};

        reverseLinkedList.displayProblemAndApproach();
        Node current = reverseLinkedList.createLinkedList(elements);
        System.out.println("\n LinkedList: \n" + current);

        current = reverseLinkedList.reverseLinkedList(current);
        System.out.println("\n Reversed LinkedList: \n" + current);
        reverseLinkedList.endCard();
    }

}





















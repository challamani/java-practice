package com.practice.interview.roku;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache {

    private class Node {

        private int key;
        private int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private ReentrantLock lock;

    public LRUCache(int capacity, Map<Integer, Node> cache) {
        this.capacity = capacity;
        this.cache = cache;

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        this.lock = new ReentrantLock();
    }

    public int get(int key) {
        lock.lock();
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            moveToHead(node);
            lock.unlock();
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        lock.lock();
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);

            if (cache.size() > capacity) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                cache.remove(tailPrev.key);
            }
        }
        lock.unlock();
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2, new java.util.HashMap<>());

        //parallel multithreaded test

        Runnable task1 = () -> {
            lruCache.put(1,  1);
            System.out.println("Thread 1: put(1, 1)");
            lruCache.put(2, 2);
            System.out.println("Thread 1: put(2, 2)");
            System.out.println("Thread 1: get(1) = " + lruCache.get(1)); // returns 1
        };
        new Thread(task1).start();

        Runnable task2 = () -> {
            lruCache.put(3, 3); // evicts key 2
            System.out.println("Thread 2: put(3, 3)");
            System.out.println("Thread 2: get(2) = " + lruCache.get(2)); // returns -1 (not found)
            lruCache.put(4, 4); // evicts key 1
            System.out.println("Thread 2: put(4, 4)");
            System.out.println("Thread 2: get(1) = " + lruCache.get(1)); // returns -1 (not found)
            System.out.println("Thread 2: get(3) = " + lruCache.get(3)); // returns 3
            System.out.println("Thread 2: get(4) = " + lruCache.get(4)); // returns 4
        };
        new Thread(task2).start();
    }
}

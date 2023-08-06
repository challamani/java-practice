package com.practice.threads;


import com.practice.MyTechHub;

public class InterprocessCommunication {

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject(50);
        EvenThread evenThread = new EvenThread(sharedObject);
        evenThread.setName("Even Thread");

        OddThread oddThread = new OddThread(sharedObject);
        oddThread.setName("Odd Thread ");
        oddThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        evenThread.start();
    }
}

class SharedObject {
    private final int range;
    public SharedObject(int range){
        this.range = range;
    }

    public int getRange(){
        return range;
    }
}

class OddThread extends Thread implements MyTechHub {

    //This thread only for printing odd numbers
    private final SharedObject sharedObject;

    public OddThread(SharedObject sharedObject){
        this.sharedObject = sharedObject;
    }

    @Override
    public void run(){
        String color = getColor(3);
        for(int i=1; i<=sharedObject.getRange(); i+= 2){
            System.out.printf("%s%s [%d]",
                    color,
                    Thread.currentThread().getName(),
                    i
            );
            System.out.println();
            delay(100);
            synchronized (sharedObject) {
                //This notifies to even thread
                sharedObject.notify();
                try {
                    if(i != sharedObject.getRange())
                        sharedObject.wait();
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                }
            }
        }
    }
}

class EvenThread extends Thread implements MyTechHub {

    //This thread only for printing odd numbers
    private final SharedObject sharedObject;

    public EvenThread(SharedObject sharedObject){
        this.sharedObject = sharedObject;
    }

    @Override
    public void run(){
        String color = getColor(1);
        for(int i=2; i<=sharedObject.getRange(); i+= 2){
            System.out.printf("%s%s [%d]",
                    color,
                    Thread.currentThread().getName(),
                    i
            );
            System.out.println();
            delay(100);
            synchronized (sharedObject) {
                //This notifies to odd thread
                sharedObject.notify();
                try {
                    if(i != sharedObject.getRange())
                        sharedObject.wait();
                } catch (InterruptedException ie) {
                    throw new RuntimeException(ie);
                }
            }
        }
    }
}
















































































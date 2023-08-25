package com.practice.misc;

import com.practice.MyTechHub;
import com.practice.threads.util.DiagonalThread;
import com.practice.threads.util.PlayerThread;
import com.practice.threads.util.SharedItem;


public class QndA implements MyTechHub {
    public static void main(String[] args) {

        //String questionStr = "Do you know what is the purpose of record keyword in Java";
        //Question question = new Question(questionStr);
        //QuestionThread questionThread = new QuestionThread(question);

        PlayerThread player1 = new PlayerThread(
                5,
                10,
                "RIGHT",
                MyTechHub.WHITE_COLOR,
                60,
                10);
        player1.start();
        //questionThread.start();

        PlayerThread player2 = new PlayerThread(
                10,
                20,
                "LEFT",
                MyTechHub.YELLOW_COLOR,
                60,
                10);
        player2.start();
    }
}

record Question(String data) {

}
class QuestionThread extends Thread implements MyTechHub {
    private final Question question;
    public QuestionThread(Question question){
        this.question = question;
    }

    @Override
    public void run(){

        for (char ch:question.data().toCharArray()) {
            System.out.print(ch);
            delay(20);
        }
        System.out.println();
    }
}



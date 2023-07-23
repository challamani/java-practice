package com.practice.exception;

public class ChainException {

    public ChainException() {

        try {
            doCheckException(1);
        } catch (Exception e) {
            System.out.println("Parent :: " + e.getMessage());
        }
    }

    private void doCheckException(int attempts) {


        try {

            int a=0;
            int b=100;
            int c = b/a;
            System.out.println("Value :: "+c);
        } catch (ArithmeticException ae) {

            System.out.println("ArithmeticException :: attempts ::" + attempts);
            if (attempts < 3) {
                doCheckException(++attempts);
            }
        } catch (Exception e) {
            System.out.println("Exception :: attempts :: " + attempts);
            if (attempts < 3) {
                doCheckException(++attempts);
            }
        }
    }

    public static void main(String[] args){
        new ChainException();
    }

}

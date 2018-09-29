package com.thinkinjava.chapter21.exercise;

public class Exercise1 implements Runnable{
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Exercise1(){
        System.out.println("Printer started, ID = " + id);
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            System.out.println("Stage " + i +"... ID = "+ id);
            Thread.yield();
        }
        System.out.println("Printer ended, ID = " + id);
    }

    public static void main(String[] args){
        for (int i = 0; i < 5; i++){
            new Thread(new Exercise1()).start();
        }
    }
}

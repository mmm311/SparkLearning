package com.thinkinjava.chapter21.base;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.io.File;

public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount ++;

    public LiftOff(){}

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status() {

        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!" )+ "), ";
    }

    @Override
    public void run()  {
        while (countDown-- > 0){
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args){
//        for (int i = 0; i < 5; i++){
//            new Thread(new LiftOff()).start();
//        }
//        System.out.println("Waiting for LiftOff");
        SecurityManager securityManager = System.getSecurityManager();
    }



}

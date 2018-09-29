package com.thinkinjava.chapter21;

import java.io.IOException;

/**
 * main线程执行结束时，所有的守护线程同时结束
 */
public class ResponsiveUI extends Thread{
    private static volatile double d = 1;

    public ResponsiveUI(){
        setDaemon(true);
        start();
    }

    public void run(){
        while (true){
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException{
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}

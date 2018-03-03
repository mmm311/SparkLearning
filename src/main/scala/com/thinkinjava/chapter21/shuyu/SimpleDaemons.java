package com.thinkinjava.chapter21.shuyu;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try{
            while (true){
                TimeUnit.MICROSECONDS.sleep(1);
                System.out.println(Thread.currentThread() + " " + this);
            }
        }catch (InterruptedException e){
            System.out.println("Sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started ");
        TimeUnit.MICROSECONDS.sleep(175);
    }
}

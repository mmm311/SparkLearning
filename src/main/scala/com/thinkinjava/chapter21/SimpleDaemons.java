package com.thinkinjava.chapter21;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable{

    volatile int i = 0;
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                i = i + 1;
                System.out.println(Thread.currentThread() + " " + i + " " + this);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}

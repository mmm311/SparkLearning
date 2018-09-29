package com.hdu.edu.encrypt;

import org.apache.derby.impl.sql.catalog.SYSPERMSRowFactory;

import java.util.concurrent.CountDownLatch;

public class WaitingTask implements Runnable {
    CountDownLatch latch;
    long startTime = 0;
    int core;
    public WaitingTask(CountDownLatch latch, long startTime, int core){
        this.latch = latch;
        this.startTime = startTime;
        this.core = core;
    }

    @Override
    public void run() {
        try{
            latch.await();
            long end = System.currentTimeMillis();
            System.out.println("执行时间：" + (end - startTime) * 1. / 1000 + " core" + core);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

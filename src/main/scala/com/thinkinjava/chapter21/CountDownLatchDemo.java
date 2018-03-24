package com.thinkinjava.chapter21;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import scala.tools.cmd.gen.AnyVals;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskPortion implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this + "completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

class WaitingTask implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        try{
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d ",id);
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(SIZE);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            service.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++){
            service.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all task");
        service.shutdown();
    }
}

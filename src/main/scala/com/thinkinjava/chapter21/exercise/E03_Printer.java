package com.thinkinjava.chapter21.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Printer implements Runnable{
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Printer(){
        System.out.println("task" + id + " start ...");
    }
    @Override
    public void run() {
        for (int i = 0; i <= 3; i++){
            System.out.println("Stage " + i +" start,id = " + id);
            Thread.yield();
        }
        System.out.println("task" + id + " end ...");

    }
}
public class E03_Printer {
    public static void executor(ExecutorService service){
        System.out.println(service.getClass().getName());
        for (int i = 0; i < 5; i++){
            service.execute(new Printer());
        }
        service.shutdown();
        System.out.println();
    }
    public static void main(String[] args) throws InterruptedException{
        ExecutorService service = null;
        // 执行 CachedThreadPool
        service = Executors.newCachedThreadPool();
        executor(service);
        Thread.sleep(100);

        // 执行 singleThread
        service = Executors.newSingleThreadExecutor();
        executor(service);
        Thread.sleep(100);

        // 执行 fixedThrad
        service = Executors.newFixedThreadPool(5);
        executor(service);

    }

}

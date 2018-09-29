package com.thinkinjava.chapter21;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class RunableDemo implements Runnable{
    private Thread t;
    private String threadName;

    RunableDemo(String name){
        threadName = name;
        System.out.println("Creating " + threadName);
    }
    @Override
    public void run() {
        System.out.println("Runing " + threadName);
        try{
            for (int i = 0; i < 4; i++){
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public void start(){
        System.out.println("Starting " + threadName);
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String[] args){
        RunableDemo r1 = new RunableDemo("Thread-1");
        r1.start();

        RunableDemo r2 = new RunableDemo("Thread-2");
        r2.start();
    }
}

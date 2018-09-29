package com.java.concurrency;

import com.thinkinjava.chapter21.RunableDemo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    public static final Exchanger<String> exchanger = new Exchanger<>();
    public static ExecutorService service = Executors.newFixedThreadPool(4);

    public static void main(String[] args){
        service.execute(new Runnable() {
            String A = "银行流水A";
            @Override
            public void run() {
                try{
                    exchanger.exchange(A);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            String B = "银行流水B";
            @Override
            public void run() {
                try{
                    Thread.sleep(100);
                    String  A = exchanger.exchange(B);
                    System.out.println("A和B数据是否一致" + A.equals(B) + ".A录入的是：" + A + ". B录入的是：" + B);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            String C = "银行流水C";
            @Override
            public void run() {
                try{
                    Thread.sleep(10);
                    String  D = exchanger.exchange(C);
                    System.out.println("A和B数据是否一致" + D.equals(C) + ".D录入的是：" + D + ". 录入的是：" + C);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            String D= "银行流水D";
            @Override
            public void run() {
                try{
                    String  C = exchanger.exchange(D);
                    System.out.println("A和B数据是否一致" + C.equals(D) + ".C录入的是：" + C + ". 录入的是：" + D);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }


}

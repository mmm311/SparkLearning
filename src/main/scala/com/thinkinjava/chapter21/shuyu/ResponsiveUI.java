package com.thinkinjava.chapter21.shuyu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

class UnresponsvieUI{
    private volatile double d = 1;
    public  UnresponsvieUI() throws Exception{
        while (d > 0){
            d = d + (Math.PI + Math.E) / d;
        }
        System.in.read();
    }
}
public class ResponsiveUI implements Callable<Integer> {
    private static volatile double d = 1;
    private int id;

    public ResponsiveUI(int id){
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        return id * id;
    }

    public static void main(String[] args) throws Exception{
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        List<Future<Integer>> results = new ArrayList<>();
        int result = 0;
        for(int i = 0; i < 100; i++)
        {
           result = executor.submit(new ResponsiveUI(i)).get();
           System.out.print("result " + result + ", ");
           if ((i + 1) % 10 == 0){
               System.out.println();
           }
        }
        System.out.println("核心线程数 " + executor.getCorePoolSize());
        System.out.println("线程池数 " + executor.getPoolSize());
        System.out.println("队列任务数　"+ executor.getQueue().size());
        executor.shutdown();


    }


}

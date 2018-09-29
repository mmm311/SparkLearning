package com.thinkinjava.chapter21.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FibonacciWithResult implements Generator, Callable<String>{
    private int count = 0;
    private final int n;

    public FibonacciWithResult(int n){
        this.n = n;
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    @Override
    public String call() throws Exception {
        int [] seqence = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++){
            seqence[i] = next();
            sum += seqence[i];
        }
        return Arrays.toString(seqence) + ", sum =" + sum +".";
    }

    public int fib(int count){
        if (count < 2){
            return 1;
        }else{
            return fib(count - 1) + fib(count - 2);
        }
    }
}

public class E05_FibonacciWithResult {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            results.add(service.submit(new FibonacciWithResult(i)));
        }
        for (Future<String> fs : results){
            System.out.println(fs.get());
        }
    }
}

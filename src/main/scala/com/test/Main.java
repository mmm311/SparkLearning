package com.test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    synchronized public void test(){
        synchronized (this){
            Lock lock = new ReentrantLock();
        }
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] number = new int[N];
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < N; i++) {
            number[i]= in.nextInt();
            valueToIndex.put(number[i], i);
        }
        int count = 0;
        Arrays.sort(number);
        for (int i = 0; i < N; i++) {
            System.out.print(number[i] + " ");
        }
        for (int i = N - 1; i > 0; i--) {
            if (valueToIndex.get(number[i]) < valueToIndex.get(number[i - 1])) {
                count++;
            }
        }

        System.out.println(count);






    }
}

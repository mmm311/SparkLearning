package com.java.colllections;

import java.lang.reflect.Type;

public class TableForTest implements Comparable {
    final static int MAXIMUM_CAPACITY = 100000;
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("n >>> 1: " + (n >>> 1));
        n |= n >>> 1;
        System.out.println("n |= n >>> 1: " +n);
        n |= n >>> 2;
        System.out.println("n >>> 2: "+ (n >>> 2));
        System.out.println("n |= n >>> 2: " +n);
        n |= n >>> 4;
        System.out.println("n >>> 4: "+ (n >>> 4));
        System.out.println("n |= n >>> 4: " +n);
        n |= n >>> 8;
        System.out.println("n >>> 8: "+ (n >>> 8));
        System.out.println("n |= n >>> 8: " +n);
        n |= n >>> 16;

        System.out.println("n >>> 16: "+ (n >>> 16));
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args){
        TableForTest tableForTest = new TableForTest();
        Class<?> c = tableForTest.getClass();
        Type[] ts = c.getGenericInterfaces();
        for (int i = 0; i < ts.length; i++){
            System.out.print(ts[i]);
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

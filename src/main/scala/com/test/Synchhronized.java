package com.test;

public class Synchhronized {
    public static void main(String[] args) {
        synchronized (Synchhronized.class) {
            m();
        }
    }

    public static synchronized void m () {}
}

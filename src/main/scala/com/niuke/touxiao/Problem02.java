package com.niuke.touxiao;

import java.util.Scanner;

public class Problem02 {
    public static boolean valid(int value) {
        for (int i = 2; i <= Math.sqrt(value); i++) {
        if (value % i == 0) {
            return false;
        }
    }
        return true;
}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int N = Integer.valueOf(s.split("=")[1]);
        long sum = 0;
        int value = 0;
        for (int i = 1; i < N; i++) {
            value = i - 1 + i;
            if (valid(value)) {
                sum = sum + i - 1 + i;
            }
        }
        System.out.println(sum);
    }
}

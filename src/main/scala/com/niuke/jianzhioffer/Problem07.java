package com.niuke.jianzhioffer;

public class Problem07 {
    public int Fibonacci(int n) {
        int[] result = new int[n + 1];

        if (n == 0) {
            return 0;
        }else if (n == 1) {
            return  1;
        }else if (n == 2) {
            return 1;
        }else {
            result[1] = 1;
            result[2] = 1;
            for (int i = 3; i <= n; i++) {
                result[i] = result[i - 1] + result[i - 2];
            }
        }
        return result[n];
    }
}

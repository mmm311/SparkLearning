package com.leetcode.dp;

public class LargestSumofAverages813 {
    public double largestSumOfAverages(int[] A, int K) {
        int Alength = A.length;
        int []sum = new int[Alength + 1];

        if (Alength == 0){
            return 0.0;
        }
        for (int i = 0; i < Alength; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        if (K == 0) {
            return sum[Alength - 1] * 1.0 / Alength;
        }

        double [][]dp = new double[K + 1][Alength];
        for (int i = 0; i < Alength; i++) {
            dp[1][i] = (sum[i + 1] * 1.0) / (i + 1) ;
        }

        for (int k = 2; k <= K; k++) {
            for (int i = 0; i < Alength; i++) {
                for (int j = 0; j < i; j++) {
                    double avg = (sum[i + 1] - sum[j + 1]) * 1.0 / (i - j);
                    dp[k][i] = Math.max(dp[k - 1][j] + avg, dp[k][i]);
                }
            }
        }
        return dp[K][Alength - 1];
    }

}

package com.leetcode.dp;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class MaximumSubarray718 {

    public static int findLength(int[] A, int[] B) {
        int ALength = A.length;
        int BLength = B.length;
        int [][] dp = new int[ALength + 1][BLength + 1];

        for (int i = 0; i <= ALength; i++){
            dp[i][0] = 0;
        }

        for (int i = 0; i <= BLength; i++){
            dp[0][i] = 0;
        }

        int ans = 0;
        for (int i = 0; i < ALength; i++) {
            for (int j = 0; j < BLength; j++){
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }

                if (dp[i + 1][j + 1] >= ans){
                    ans = dp[i + 1][j + 1];
                }
            }
        }
        for (int i = 0; i <= ALength; i++){
            for (int j = 0; j <= BLength; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args){
        int []A = {0, 1, 1, 1, 1};
        int []B = {1, 0};

        findLength(A, B);
    }

}

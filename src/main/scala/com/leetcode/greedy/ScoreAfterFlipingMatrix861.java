package com.leetcode.greedy;

public class ScoreAfterFlipingMatrix861 {
    public int matrixScore(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int ans = 0;

       for (int j = 0; j < n; j++) {
           int col = 0;
           for (int i = 0; i < m; i++) {
               col += A[i][j] ^ A[i][0];
           }
           ans += Math.max(m - col, col) * ( 1 << (n - 1 - j));
       }
       return ans;
    }
}

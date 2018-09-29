package com.algorithm.dp;

/**
 * @author 1034
 */

public class MatrixChainOrder {
    public static int[][] matrixChainOrder(int[] p) {
        int n = p.length;
        int[][] m = new int[n][n];

        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }

        for (int l = 1; l < n; l++) {
            for (int i = l; l < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;

                    }
                }
            }
        }

        return m;
    }
    public static void main(String[] args) {

    }
}

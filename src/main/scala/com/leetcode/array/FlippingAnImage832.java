package com.leetcode.array;

public class FlippingAnImage832 {
    public int[][] flipAndInvertImage(int[][] A){
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n / 2; j++){
                int temp = A[i][j];
                A[i][j] = A[i][n - j - 1] ;
                A[i][n - j - 1] = temp;
            }
        }

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    A[i][j] = 0;
                }else{
                    A[i][j] = 1;
                }
            }
        }
        return A;
    }
}

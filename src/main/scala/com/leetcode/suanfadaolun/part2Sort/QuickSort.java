package com.leetcode.suanfadaolun.part2Sort;

public class QuickSort {
    // 快排算法
    public static void quickSort(int[] A, int p, int r){
        if (p < r){
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }

    public static void quickSort(int[] A){
        int end = A.length - 1;
        int start = 0;
        quickSort(A, start, end);
    }

    public static void exchange(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1 ; j ++){
            if (A[j] <= x){
                i = i + 1;
                exchange(A, i, j);
            }
        }
        exchange(A, i + 1, r);
        return i + 1;

    }

    public static void main(String[] args){
        int[] A = new int[] {0,  14, 8, 9, 12, 11, 13};
        quickSort(A);
        int Alength = A.length;
        for (int i = 0; i < Alength; i++){
            System.out.print(A[i] + " ");
        }
    }
}

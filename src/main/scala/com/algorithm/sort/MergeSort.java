package com.algorithm.sort;

public class MergeSort {

    public static void mergeSort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(A, p, q);
            mergeSort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    public static void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for(int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        L[n1] = Integer.MAX_VALUE;
        for (int i = 0; i < n2; i++) {
            R[i] = A[q + 1 + i];
        }
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] < R[j]) {
                A[k] = L[i];
                i++;
            }else{
                A[k] = R[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] {1, 2,4,3, 6,5};
        mergeSort(A, 0, 5);
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
    }
}

package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class AdvantagShuffle870 {

    public static int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        PriorityQueue<int[]> pq= new PriorityQueue<>((a, b)->b[0]-a[0]);
        for (int i=0; i<B.length; i++) pq.add(new int[]{B[i], i});
        int lo=0, hi=A.length-1, res[] = new int[A.length];
        while(!pq.isEmpty()) res[pq.peek()[1]]=pq.poll()[0]<A[hi]?A[hi--]:A[lo++];
        return res;
    }

    public static void main(String[] args) {
        int[] A = new int[] {2, 7, 11, 15};
        int[] B = new int[] {1, 10, 4, 11};
        int[] new_A = advantageCount(A, B);
        for (int i = 0; i < A.length; i++) {
            System.out.print(new_A[i] + " ");
        }

    }
}

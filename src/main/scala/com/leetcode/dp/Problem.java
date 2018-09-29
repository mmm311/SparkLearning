package com.leetcode.dp;

import java.util.HashSet;
import java.util.Set;

public class Problem {

    public int lenLongestFibSubseq(int[] A){
        int N = A.length;
        Set<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int temp = y;
                    y += x;
                    x = temp;
                    ans = Math.max(ans, length ++);
                }
            }
        }
        return ans >= 3 ? ans: 0;
    }
}

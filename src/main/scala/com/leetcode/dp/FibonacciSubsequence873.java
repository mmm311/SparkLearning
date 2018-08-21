package com.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class FibonacciSubsequence873 {
    public int lenLongestFibSubseq(int[] A) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            index.put(A[i], i);
        }
        int N = A.length;
        int ans = 0;
        Map<Integer, Integer> longest = new HashMap<>();
        for (int k = 0; k < A.length; k++){
            for (int j = 0; j < k; j++) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    int sum = longest.getOrDefault(i * N + j, 2 ) + 1;
                    longest.put(j * N + k, sum);
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans >= 3 ? ans: 0;
    }
}

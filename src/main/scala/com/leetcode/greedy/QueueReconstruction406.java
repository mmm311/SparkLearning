package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstruction406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] left, int[] right) {
                int res = right[0] - left[0];
                if (res == 0) {
                    return left[1] - right[1];
                }
                return res;
            }
        });

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            int[] cur = people[i];
            list.add(cur[1], cur);
        }
        return list.toArray(new int[people.length][]);
    }

}

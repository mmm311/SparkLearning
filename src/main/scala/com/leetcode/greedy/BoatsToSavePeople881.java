package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoatsToSavePeople881 {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }
}

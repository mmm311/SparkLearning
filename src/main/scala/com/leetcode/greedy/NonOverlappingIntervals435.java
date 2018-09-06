package com.leetcode.greedy;

import java.util.Arrays;

class Interval {
    int start;
    int end;
    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public class NonOverlappingIntervals435 {

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> (a.end - b.end));
        int end = intervals[0].end;
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i].start) {
                count++;
                continue;
            }
            end = intervals[i].end;
        }


        return count;
    }
}

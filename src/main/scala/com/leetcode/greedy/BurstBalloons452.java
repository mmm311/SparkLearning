package com.leetcode.greedy;

import java.util.Arrays;

public class BurstBalloons452 {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowNum = 0, arrow = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (arrow >= points[i][0]) {
                continue;
            }
            arrowNum++;
            arrow = points[i][1];
        }

        return arrowNum;
    }
}

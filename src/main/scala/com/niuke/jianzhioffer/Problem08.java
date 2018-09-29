package com.niuke.jianzhioffer;

public class Problem08 {

    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }

        if (target == 2) {
            return 2;
        }

        int[] result = new int[target + 1];
        result[1] = 1;
        result[2] = 2;
        for (int i = 3; i <= target; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[target];
    }
}

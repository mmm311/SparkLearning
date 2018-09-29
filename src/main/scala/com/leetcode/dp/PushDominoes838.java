package com.leetcode.dp;

public class PushDominoes838 {
    public String pushDominoes(String dominoes) {
        char[] dominoes_ch = dominoes.toCharArray();
        int index = 0;
        int length = dominoes_ch.length;
        boolean[] flag = new boolean[length];

        while (true) {
            index = 0;
            for (int i = 0; i < length; i++) {
                if (dominoes_ch[i] == '.') {
                    if (i - 1 > 0 && i + 1 < length
                            && dominoes_ch[i - 1] == 'R'
                            && dominoes_ch[i + 1] == 'L') {
                        dominoes_ch[i] = '.';
                    }
                }

                if (dominoes_ch[i] == 'R') {
                    if (i + 1 < length && dominoes_ch[i + 1] == '.') {
                        dominoes_ch[i + 1] = 'R';
                    }
                    index++;
                }

                if (dominoes_ch[i] == 'L') {
                    if (i - 1 >= 0 && dominoes_ch[i - 1] == '.') {
                        dominoes_ch[i - 1] = 'L';
                    }
                    index++;
                }
            }
            if (index == 0) {
                break;
            }
        }
        return new String(dominoes_ch);
    }
}

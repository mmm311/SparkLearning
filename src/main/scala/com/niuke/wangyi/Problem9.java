package com.niuke.wangyi;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 奖学金
 */
public class Problem9 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int r = in.nextInt();
            int avg = in.nextInt();
            int [][] scores = new int[n][2];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < 2; j++){
                    scores[i][j] = in.nextInt();
                }
            }
            Arrays.sort(scores, new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    int[] value1 = (int[])o1;
                    int[] value2 = (int[])o2;
                    if (value1[1] == value2[1] ){
                        return value1[0] - value2[0];
                    }
                    return value1[1] - value2[1];
                }
            });
            int scoreHaveGet = 0;
            for (int i = 0; i < n; i++){
                scoreHaveGet += scores[i][0];
            }
            int resetScore = n * avg - scoreHaveGet;
            int day = 0;
            for (int i = 0; i < n; i++){
                if (resetScore > 0) {
                    day += (Math.min(r - scores[i][0], resetScore)) * scores[i][1];
                    resetScore = resetScore - (r - scores[i][0]);
                }
            }
            System.out.println(day);
        }
    }
}

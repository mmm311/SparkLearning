package com.niuke.wangyi;

import java.util.Scanner;
import java.util.Stack;
import com.sun.tools.javac.main.*;

public class Problem04 {

    public static int solve(char[][] point, boolean[][] point_flag, int h, int w) {
        int start_i = 0, start_j = 0;
        int end_i = 0, end_j = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (point[i][j] == 'S') {
                    start_i = i; start_j = j;

                }
                if (point[i][j] == 'E') {
                    end_i = i; end_j = j;
                }
            }
        }
        int result = 1;
        int search_i = start_i, search_j = start_j;
        // 方向 : 向左，向上，向下，向上
        int[][] fangxiang = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        Stack<int[]> set = new Stack();
        set.add(new int[]{start_i, end_i});
        int prev_index = 0;
        while (!set.isEmpty()) {
            boolean flag = false;

           for (int i = 0; i < 4; i++) {
               int index = (i + prev_index) % 4;
               int new_search_i = search_i + fangxiang[index][0];
               int new_search_j = search_j + fangxiang[index][1];
               if (0 <= new_search_i && new_search_i < w && 0 <= new_search_j
                       && new_search_j < h && !point_flag[new_search_i][new_search_j]) {
                   if (point[new_search_i][new_search_j] == '.') {
                       search_i = search_i + fangxiang[i][0];
                       search_j = search_j + fangxiang[i][1];
                       prev_index = i;
                       flag = true;
                       set.push(new int[] {search_i, search_j});
                       result++;
                       break;
                   }
               }
           }
           if (!flag){
               point_flag[search_i][search_j] = true;
               int [] position = set.pop();
               search_i = position[0];
               search_j = position[1];
               result++;

           }
           System.out.println(search_i + " "+ search_j + " " + end_i + " "+end_j);
           if (search_i == end_i && search_j == end_j) {
               break;
           }
        }
    return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        int n = 2;
        for (int i = 0; i < n; i++) {
//            int w = in.nextInt();
//            int h = in.nextInt();
            int w = 5;
            int h = 5;
            char [][] point = new char[h][w];
            boolean[][] flag = new boolean[h][w];
            String[] strs = {
                    "#####",
                    "#...#",
                    "#...#",
                    "#.#.#",
                    "#E#S#"};

            for (int j = 0; j < h; j++) {
                point[j] = strs[j].toCharArray();
            }

            System.out.print("start");
            int result = 0;
            result = solve(point, flag, h, w);
            System.out.println(result);
        }

    }
}

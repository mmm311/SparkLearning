package com.niuke.jianzhioffer;

public class Problem01_01 {
    public static boolean Find(int target, int[][] array){

        int n = array.length;
        int m = array[0].length;
        if (n == 0 || m == 0){
            return false;
        }
        for (int i = 0; i < n; i++){
            if (array[i][m - 1] >= target){
                for (int j = m - 1; j > 0; j--){
                    if (target == array[i][j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String []args){
        int [][] array = {{}};
        System.out.print(array.length);
        System.out.print(Find(16, array));
    }
}

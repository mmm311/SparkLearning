package com.niuke.jianzhioffer;

public class Problem01 {
    public static boolean Find(int target, int[][] array){
        int n = array.length;
        int m = array[0].length;
        for (int i = 0; i < n; i++){
            int low = 0;
            int high =  m - 1;
            while (low <= high){
                int mid = (low + high) / 2;
                if (array[i][mid] == target){
                    return true;
                }else if (array[i][mid] < target){
                    low = mid +  1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        int [][] array = {
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}};
        int target = 7;
        System.out.print(Find(target, array));
    }
}

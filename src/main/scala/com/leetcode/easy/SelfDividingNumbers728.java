package com.leetcode.easy;

import org.apache.spark.sql.sources.In;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNumbers728 {

    public static  boolean selfDividingNumber(int num){
        boolean flag = true;
        int numCopy = num;

        while (numCopy > 0){
            if ((numCopy % 10 == 0) || (num % (numCopy % 10) != 0)){
                flag = false;
                break;
            }
            numCopy = numCopy / 10;

        }
        return flag;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> results = new ArrayList<>();

        for (int i = left; i <= right; i++){
            if (selfDividingNumber(i)){
                results.add(i);
            }
        }
        return results;
    }

    public static void main(String[] args){
        for (int i = 1; i < 22; i++){
            System.out.println(selfDividingNumber(i));
        }
    }
}

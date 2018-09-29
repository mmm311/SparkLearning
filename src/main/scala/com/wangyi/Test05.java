package com.wangyi;

public class Test05 {
    public static int test() {
        try{
            int i = 1 / 0;
            return 1;
        }catch (Exception e) {
            return 2;
        }finally {
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}

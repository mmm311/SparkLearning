package com.thinkinjava.chapter09;

import java.util.Random;
/**
 * @author liu
 */

/**
 * 接口的域 static final 类型 必须要初始化
 */
interface RandVals {
    Random RAND = new Random(4);
    int RANDOM_INT = RAND.nextInt(10);
    long RANDOM_LONG = RAND.nextLong() * 10;
    float RADNOM_FLOAT = RAND.nextFloat() * 10;
    double RADNOM_DOUBLE = RAND.nextDouble() * 10 ;
}
public class TestRandVals{

    public static void main(String[] args){
        System.out.println(RandVals.RAND);
        System.out.println(RandVals.RADNOM_DOUBLE);
        System.out.println(RandVals.RAND);
    }

}

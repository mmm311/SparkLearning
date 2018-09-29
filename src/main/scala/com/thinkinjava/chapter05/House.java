package com.thinkinjava.chapter05;

/**
 * @author liu
 * 测试 static 类型的两次初始化
 */

class Window
{
    int i = 1;
    public Window(int marked){
        i++;
        System.out.println("i " + i);
        System.out.println("Window(" + marked + ")" );
    }
}

public class House
{
    static  Window w1 = new Window(1);

    public House(){
        w1 = new Window(11);
    }

    public static void main(String[] args){
        new House();
    }
}

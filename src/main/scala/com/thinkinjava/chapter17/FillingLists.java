package com.thinkinjava.chapter17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 填充容器
 */
class StringAddres{
    private String s;

    public StringAddres(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + " " + s;
    }
}

public class FillingLists {
    public static void main(String[] args){
        List<StringAddres> list = new ArrayList<>(Collections.nCopies(3, new StringAddres("hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddres("World!"));
        System.out.println(list);
    }
}

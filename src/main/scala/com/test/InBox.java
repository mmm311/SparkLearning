package com.test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeMap;

public class InBox {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        TreeMap<String, String> treeMap = new TreeMap<>();
        HashSet<String> set = new HashSet<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("123", "123");
        map.put("123", "123");
        set.add("123");
        set.add("123");
        System.out.println(set.size());


    }
}

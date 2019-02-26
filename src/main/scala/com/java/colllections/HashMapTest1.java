package com.java.colllections;

import java.util.HashMap;
import java.util.Map;

class HashMapTest{
    public static void main(String[] args) {
        Map map = new HashMap<String, String>();
        map.put(null, null);
        System.out.println("null" + ":" + map.get(null));
    }
}
package com.thinkinjava.chapter17;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class AssociativeArray<K, V> {
    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length){
        pairs = new Object[length][2];
    }

    public void put(K key, V value){
        if (index >= pairs.length){
            throw new ArrayIndexOutOfBoundsException();
        }else{
            pairs[index++] = new Object[]{key, value};
        }
    }

    public V get(K key){
        for (int i = 0; i < index; i++){
            if (key.equals(pairs[i][0])){
                return (V)pairs[i][1];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++){
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());

            if (i < index - 1){
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * 和其他map比较
     */
    public static void comparedOtherMap(Map map){
        long start = System.currentTimeMillis();
        map.put("ssky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warn");
        map.get("ocean");
        long end = System.currentTimeMillis();
        System.out.println("时间： " + (end - start) + ", " + "result: "+map);
    }
    static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);
    public static void main(String[] args){
//        AssociativeArray<String, String> map = new AssociativeArray<>(6);
//        long start = System.currentTimeMillis();
//        map.put("ssky", "blue");
//        map.put("grass", "green");
//        map.put("ocean", "dancing");
//        map.put("tree", "tall");
//        map.put("earth", "brown");
//        map.put("sun", "warn");
//        map.get("ocean");
//        long end = System.currentTimeMillis();
//        System.out.println("时间： " + (end - start)+ ", " + "result:" + map);
//
//        Map hashMap = new HashMap();
//
//        Map treeMap = new TreeMap();
//        Map linkedHashMap = new LinkedHashMap();
//        AssociativeArray.comparedOtherMap(hashMap);
//        AssociativeArray.comparedOtherMap(treeMap);
//        AssociativeArray.comparedOtherMap(linkedHashMap);
//        Object str = "11222222";
//        int h = str.hashCode();
//        System.out.println(h);
//        System.out.println(h ^ (h >>> 16));

        map.put(5, "C");
        new Thread("Thread1"){
            public void run(){
                map.put(7,"B");
                System.out.println(map);
            };
        }.start();

        new Thread("Thread2"){
            public void run(){
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();





    }
}

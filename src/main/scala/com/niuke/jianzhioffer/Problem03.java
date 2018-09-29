package com.niuke.jianzhioffer;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Problem03 {
    public static void swap(List<Integer> result, int i, int j){
        int temp = result.get(i);
        result.add(i, result.get(j));
        result.add(j, temp);
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> new_result = new ArrayList<>();
        while (listNode != null){
            result.add(listNode.val);
            listNode = listNode.next;
        }
        int size = result.size();
        for (int i = size - 1; i >= 0; i--){
            new_result.add(result.get(i));
        }
        return new_result;

    }
    public static void main(String[] args){
        System.out.print(printListFromTailToHead(new ListNode(1)));
    }
}

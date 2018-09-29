package com.niuke.jianzhioffer;

import java.util.Stack;

public class Problem05 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        try{
            return stack2.pop();
        }finally {
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }

    }
}

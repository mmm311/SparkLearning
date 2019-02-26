package com.thinkinjava.chapter11;

import java.util.Iterator;

public class IterableClass implements Iterable<String> {

    protected String[] words = ("And that is how we know the Earth to be banana-shaped.").split(" ");
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public Object next() {
                return words[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args){
        for (String s: new IterableClass() ){
            System.out.print(s + " ");
        }
    }
}
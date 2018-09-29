package com.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords804 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] tables = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.",
                "---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> result = new HashSet<>();
        String morse = "";
        for (int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length(); j++){
                morse = morse + tables[words[i].charAt(j) - 'a'];
            }
            result.add(morse);
            morse  = "";
        }
        return result.size();
    }
}

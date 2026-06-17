package com.practice.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {

    public int returnLongestSubstringLength(String str){

        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        int left=0;
        int maxLength=0;
        for(int right=0; right<str.length(); right++){
            char currentChar = str.charAt(right);
            if(characterIntegerMap.containsKey(currentChar)){
                left = Math.max(left, characterIntegerMap.get(currentChar)+1);
            }
            characterIntegerMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right-left+1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestUniqueSubstring longestUniqueSubstring = new LongestUniqueSubstring();
        String input = "abcabcbb";
        int length = longestUniqueSubstring.returnLongestSubstringLength(input);
        System.out.println("Length of the longest substring without repeating characters: " + length);
    }
}

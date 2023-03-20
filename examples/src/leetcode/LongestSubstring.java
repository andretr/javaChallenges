package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Andre Ticona.
 * Date: 3/20/2023
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
 */
public class LongestSubstring {

    public static void main(String[] args) {
        var longestSubstring = new LongestSubstring();

        System.out.println(longestSubstring.lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(longestSubstring.lengthOfLongestSubstring("bbbbb"));//1
        System.out.println(longestSubstring.lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(longestSubstring.lengthOfLongestSubstring(" "));//1
        System.out.println(longestSubstring.lengthOfLongestSubstringMap("dvdf"));//3
    }

    //O(N*2) time complexity
    public int lengthOfLongestSubstringN2(String s) {
        int max_counter = 0;
        for (int i = 0; i< s.length(); i++) {
            HashSet<Character> letterSet = new HashSet();
            for (int j = i; j< s.length(); j++){
                if(letterSet.contains(s.charAt(j)))
                    break;
                letterSet.add(s.charAt(j));
            }
            max_counter = Math.max(max_counter, letterSet.size());
        }
        return max_counter;
    }

    //O(N) time complexity, Sliding window with Map
    public int lengthOfLongestSubstringMap(String s) {
        int max_counter = 0;
        HashMap<Character, Integer> letterMap = new HashMap<>();
        for (int right = 0, left = 0; right< s.length(); right++) {
            char currentCharacter = s.charAt(right);
            if(letterMap.containsKey(currentCharacter) &&
            letterMap.get(currentCharacter) >= left) {
                left = letterMap.get(currentCharacter) + 1;
            }
            letterMap.put(currentCharacter, right);
            max_counter = Math.max(max_counter, right - left + 1);
        }
        return max_counter;
    }

    //O(N) time complexity, Sliding window with indexOf
    public int lengthOfLongestSubstring(String s) {
        int max_counter = 0;
        for (int right = 0, left = 0; right< s.length(); right++) {
            int index = s.indexOf(s.charAt(right), left);
            if(index != right) {
                left = index + 1;
            }
            max_counter = Math.max(max_counter, right - left + 1);
        }
        return max_counter;
    }
}




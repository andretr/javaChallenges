package leetcode.arrays;

import java.util.Arrays;

/**
 * Created by Andre Ticona.
 * Date: 4/5/2023
 * https://leetcode.com/problems/valid-anagram/
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 */
public class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram v = new  ValidAnagram();
        System.out.println(v.isAnagram("anagram","nagaram"));
    }
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        char[] tArray = t.toCharArray();
        char[] sArray = s.toCharArray();
        Arrays.sort(tArray);
        Arrays.sort(sArray);
        return new String(tArray).equals(new String(sArray)) ;
    }
}

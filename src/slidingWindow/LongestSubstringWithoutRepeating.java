package array.slidingWindow;

// Given a string, S. Find the length of the longest substring without repeating characters.

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeating {
    static int longestNonRepeatingSubstring(String s){
        HashSet<Character> charSet = new HashSet<>();
        int l = 0, res = 0;
        for (int r = 0; r < s.length(); r++) {
            while (charSet.contains(s.charAt(r))) {
                charSet.remove(s.charAt(l));
                l++;
            }
            charSet.add(s.charAt(r));
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    static int longestNonRepeatingSubstringOptimal(String s) {
        HashMap<Character, Integer> mp = new HashMap<>();
        int l = 0, res = 0;
        for (int r = 0; r < s.length(); r++) {
            if (mp.containsKey(s.charAt(r))) {
                l = Math.max(mp.get(s.charAt(r)) + 1, l);
            }
            mp.put(s.charAt(r), r);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "cadbzabcd";
        int result1 = longestNonRepeatingSubstring(s);
        int result2 = longestNonRepeatingSubstringOptimal(s);
        System.out.println("The maximum length is: " + result1);
        System.out.println("The maximum length is: " + result2);
    }
}

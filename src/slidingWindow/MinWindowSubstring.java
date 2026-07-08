package array.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    static String minWindow(String s, String t){
        if (t.length() > s.length()) return "";

        Map<Character, Integer> targetMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int have = 0, need = targetMap.size();
        int[] res = {-1, -1};
        int minLen = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                have++;
            }
            while (have == need) {
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    res[0] = left;
                    res[1] = right;
                }
                windowMap.put(s.charAt(left), windowMap.get(s.charAt(left)) - 1);
                if (targetMap.containsKey(s.charAt(left)) &&
                        windowMap.get(s.charAt(left)).intValue() < targetMap.get(s.charAt(left)).intValue()) {
                    have--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    }

    public static void main(String[] args) {
        String s = "OUZODYXAZV", t = "XYZ";
        System.out.println(minWindow(s,t));
    }
}

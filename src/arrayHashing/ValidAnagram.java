package array;

public class ValidAnagram {
    static final int MAX_CHAR = 26;

    static boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length()) return false;

        int[] freq = new int[MAX_CHAR];

        for (int i = 0; i < s1.length(); i++)
            freq[s1.charAt(i) - 'a']++;

        for (int i = 0; i < s2.length(); i++)
            freq[s2.charAt(i) - 'a']--;

        for (int count : freq) {
            if (count != 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = "racecar";
        String str2 = "carrace";
        System.out.println(isAnagram(str1, str2));
    }
}

// Approach 1 : Sorting, time : O(n log n), space : O(n)
// Approach 2 : HashMap, time : O(n), space : O(n)
// Approach 3 : Array, time : O(n), space : O(1)

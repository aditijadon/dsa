package array.slidingWindow;

/*
You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k
characters of the string and replace them with any other uppercase English character. After performing at most k
replacements, return the length of the longest substring which contains only one distinct character.
*/

public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k){
        int[] counts = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            counts[index]++;
            maxFreq = Math.max(maxFreq, counts[index]);
            while ((right - left + 1) - maxFreq > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "AAABABB";
        int k = 1;
        int result = characterReplacement(s, k);
        System.out.println("The length of longest substring containing only 1 distinct char is: " + result);
    }
}

package array.twoPointer;

import java.util.Locale;

public class Palindrome {
    static boolean isPalindrome(String str) {
        String s = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ROOT);
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "Was it a car or a cat I saw?";
        System.out.println(isPalindrome(str));
    }
}

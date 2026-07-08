package array.slidingWindow;

public class Permutation {
    public static boolean checkInclusion(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 > n2) return false;
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];
        for (int i = 0; i < n1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n2 - n1; i++) {
            if (matches(s1Count, windowCount)) return true;
            windowCount[s2.charAt(i + n1) - 'a']++;
            windowCount[s2.charAt(i) - 'a']--;
        }
        return matches(s1Count, windowCount);
    }

    static boolean matches(int[] s1Count, int[] windowCount) {
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] != windowCount[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "abc", s2 = "lecabee";
        System.out.println("The maximum length is: " + checkInclusion(s1, s2));
    }
}

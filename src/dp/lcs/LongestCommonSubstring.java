package dp.lcs;

// Given two strings s1 and s2, return the length of their longest common substring (must be contiguous)

public class LongestCommonSubstring {

    // Recursive - Time: O(2^(m+n)), Space: O(m+n)
    static int lcsRecursion(String s1, String s2, int m, int n, int count) {
        if (m == 0 || n == 0) return count;
        if (s1.charAt(m-1) == s2.charAt(n-1)) return lcsRecursion(s1, s2, m-1, n-1, count + 1);
        return Math.max(count, Math.max(
                lcsRecursion(s1, s2, m-1, n, 0),
                lcsRecursion(s1, s2, m, n-1, 0)
        ));
    }

    // Memoization is complex for this variant (due to 'count' parameter), so we focus on DP

    // Bottom-up 2D DP - Time: O(m*n), Space: O(m*n)
    static int lcsDP(String s1, String s2, int m, int n, StringBuilder result) {
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i;  // end position in s1
                    }
                } else {
                    dp[i][j] = 0;  // Reset when characters don't match
                }
            }
        }
        for (int i = endIndex - maxLength; i < endIndex; i++) {
            result.append(s1.charAt(i));
        }
        return maxLength;
    }

    // Space Optimized 1D DP - Time: O(m*n), Space: O(n)
    static int lcsSpaceOptimised(String s1, String s2, int m, int n) {
        int[] prev = new int[n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    curr[j] = prev[j-1] + 1;
                    maxLength = Math.max(maxLength, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            prev = curr;
        }
        return maxLength;
    }

    static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "abe";
        int m = s1.length();
        int n = s2.length();
        StringBuilder lcsResult = new StringBuilder();
        System.out.println("Recursion              : " + lcsRecursion(s1, s2, m, n, 0));
        System.out.println("2D DP                  : " + lcsDP(s1, s2, m, n, lcsResult));
        System.out.println("1D                     : " + lcsSpaceOptimised(s1, s2, m, n));
        System.out.println("LCS                    : " + lcsResult);
    }
}

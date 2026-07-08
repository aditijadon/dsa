package dp.lcs;

// Given two strings text1 and text2, return the length of their longest common subsequence.

public class LongestCommonSubsequence {

    // Recursive - Time: O(2^(m+n)), Space: O(m+n)
    static int lcsRecursion(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (s1.charAt(m-1) == s2.charAt(n-1)) return 1 + lcsRecursion(s1, s2, m-1, n-1);
        return Math.max(lcsRecursion(s1, s2, m-1, n), lcsRecursion(s1, s2, m, n-1));
    }

    // Memoization - Time: O(m*n), Space: O(m*n) + O(m+n) recursion stack
    static int lcsMemo(String s1, String s2, int m, int n, int[][] t) {
        if (m == 0 || n == 0) return 0;
        if (t[m][n] != -1) return t[m][n];
        if (s1.charAt(m-1) == s2.charAt(n-1)) return t[m][n] = 1 + lcsMemo(s1, s2, m-1, n-1, t);
        return t[m][n] = Math.max(lcsMemo(s1, s2, m-1, n, t), lcsMemo(s1, s2, m, n-1, t));
    }

    // Bottom-up 2D DP - Time: O(m*n), Space: O(m*n)
    static int lcsDP(String s1, String s2, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    // Space Optimized 1D DP - Time: O(m*n), Space: O(n)
    static int lcsSpaceOptimised(String s1, String s2, int m, int n) {
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    curr[j] = 1 + prev[j-1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }
            // Swap references
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }

    static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";

        int m = s1.length();
        int n = s2.length();
        int[][] t = new int[m + 1][n + 1];
        for (int[] row : t) {
            java.util.Arrays.fill(row, -1);
        }
        System.out.println("Recursion              : " + lcsRecursion(s1, s2, s1.length(), s2.length()));
        System.out.println("Memoization            : " + lcsMemo(s1, s2, m, n, t));
        System.out.println("2D DP                  : " + lcsDP(s1, s2, m, n));
        System.out.println("Space Optimized (1D)   : " + lcsSpaceOptimised(s1, s2, m, n));
    }
}

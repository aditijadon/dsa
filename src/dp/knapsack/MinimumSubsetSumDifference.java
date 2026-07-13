package dp.knapsack;

import java.util.Arrays;

// Given an array of positive integers, partition the array into two
// subsets so the absolute difference of the sums of the 2 subsets is minimized. Return the minimum possible difference.

public class MinimumSubsetSumDifference {

    // Recursive - Time: O(2^n * n), Space: O(n)
    static int minDiffRecursion(int[] arr, int n, int s1, int total) {
        if (n == 0) return Math.abs(total - 2 * s1);  // s2 = total - s1 -> s2 - s1 -> total - 2 s1
        return Math.min(
                minDiffRecursion(arr, n-1, s1 + arr[n-1], total),
                minDiffRecursion(arr, n-1, s1, total)
        );
    }

    // s1 = (total - diff) / 2
    // s2 = total - s1 = total/2 + diff/2

    // Memoization - Time: O(n*target), Space: O(n*target) + O(n) recursion stack
    static int minDifferenceMemo(int[] arr, int n, int s1, int total, int[][] t) {
        if (n == 0) return Math.abs(total - 2 * s1);
        if (t[n][s1] != -1) return t[n][s1];
        return t[n][s1] = Math.min(minDifferenceMemo(arr, n-1, s1 + arr[n-1], total, t),
                minDifferenceMemo(arr, n-1, s1, total, t));
    }

    // Bottom-up 2D DP - Time: O(n*target), Space: O(n*target)
    static int minSubsetSumDiffDP(int[] arr, int n, int total) {
        int target = total / 2;
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]];
            }
        }
        for (int s = target; s >= 0; s--) { // Find the largest s <= target such that dp[n][s] is true
            if (dp[n][s]) return total - 2 * s;
        }
        return total; // fallback
    }

    // Space Optimized 1D DP - Time: O(n*target), Space: O(target)
    static int minSubsetSumDiffDPSpaceOptimised(int[] arr, int n, int total){
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= arr[i-1]; j--) {
                dp[j] = dp[j] || dp[j - arr[i-1]];
            }
        }
        for (int s = target; s >= 0; s--) { // Find closest possible sum to total/2
            if (dp[s]) return total - 2 * s;
        }
        return total;
    }


    static void main(String[] args) {
        int[] arr = {1, 6, 11, 5};
        int n = 4;
        int total = 0;
        for (int x : arr) total += x;
        int[][] t = new int[n+1][total + 1];
        for (int[] row : t) Arrays.fill(row, -1);
        System.out.println(minDiffRecursion(arr, n, 0, total));  // 1
        System.out.println(minDifferenceMemo(arr, n, 0, total, t));
        System.out.println(minSubsetSumDiffDP(arr, n, total));
        System.out.println(minSubsetSumDiffDPSpaceOptimised(arr, n, total));
    }
}

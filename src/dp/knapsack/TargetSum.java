package dp.knapsack;

import java.util.Arrays;

// LeetCode 494 : You are given an integer array nums and an integer target. You want to build an expression out of
// nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
// Return the number of different expressions that evaluate to target.

public class TargetSum {

    // Recursive - Time: O(2^n), Space: O(n)
    static int findTargetSumWaysRecursion(int[] arr, int n, int target) {
        if (n == 0) return target == 0 ? 1 : 0;
        return findTargetSumWaysRecursion(arr, n-1, target + arr[n-1])
                + findTargetSumWaysRecursion(arr, n-1, target - arr[n-1]);
    }

    // Memoization - Time: O(n*sum), Space: O(n*sum)
    static int findTargetSumWaysMemo(int[] arr, int n, int target, Integer[][] t, int offset) {
        if (n == 0) return target == 0 ? 1 : 0;
        if (t[n][target + offset] != null) return t[n][target + offset];
        return t[n][target + offset] = findTargetSumWaysMemo(arr, n-1, target + arr[n-1], t, offset)
                + findTargetSumWaysMemo(arr, n-1, target - arr[n-1], t, offset);
    }

    // Bottom-up 2D DP (using Count Subsets logic)
    static int findTargetSumWaysDP(int[] arr, int target, int n) {
        int total = 0;
        for (int num : arr) total += num;
        if ((total + target) % 2 != 0 || Math.abs(target) > total) return 0;
        int subsetSum = (total + target) / 2;
        int[][] dp = new int[n + 1][subsetSum + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= subsetSum; j++) {
                if (arr[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] + dp[i-1][j - arr[i-1]];
            }
        }
        return dp[n][subsetSum];
    }

    // Space Optimized 1D DP
    static int findTargetSumWaysSpaceOptimised(int[] arr, int target, int n) {
        int total = 0;
        for (int num : arr) total += num;
        if ((total + target) % 2 != 0 || Math.abs(target) > total) return 0;
        int subsetSum = (total + target) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = subsetSum; j >= arr[i-1]; j--) {
                dp[j] = dp[j] + dp[j - arr[i-1]];
            }
        }
        return dp[subsetSum];
    }

    static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        int target = 3;
        int n = arr.length;
        int total = 0;
        for (int num : arr) total += num;
        Integer[][] t = new Integer[n + 1][2 * total + 1];
        int offset = total;
        System.out.println("Recursion              : " + findTargetSumWaysRecursion(arr, n, target));
        System.out.println("Memoization            : " + findTargetSumWaysMemo(arr, n, target, t, offset));
        System.out.println("2D DP                  : " + findTargetSumWaysDP(arr, target, n));
        System.out.println("Space Optimized (1D)   : " + findTargetSumWaysSpaceOptimised(arr, target, n));
    }
}

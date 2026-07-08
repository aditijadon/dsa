package dp.knapsack;

import java.util.Arrays;

// Count of Subsets with Given Sum. Given an array arr[] of non-negative integers and a target sum S, find the number
// of subsets that sum up to exactly S.

public class CountOfSubset {

    // Recursive - Time: O(2^n), Space: O(n)
    static int countSubsetsRecursion(int[] arr, int n, int sum) {
        if (sum == 0) return 1;
        if (n == 0) return 0;
        if (arr[n - 1] > sum) return countSubsetsRecursion(arr, n - 1, sum);
        return countSubsetsRecursion(arr, n - 1, sum) + countSubsetsRecursion(arr, n - 1, sum - arr[n - 1]);
    }

    // Memoization - Time: O(n*sum), Space: O(n*sum) + O(n)
    static int countSubsetsMemo(int[] arr, int n, int sum, int[][] t) {
        if (sum == 0) return 1;
        if (n == 0) return 0;
        if (t[n][sum] != -1) return t[n][sum];
        if (arr[n - 1] > sum) return t[n][sum] = countSubsetsMemo(arr, n - 1, sum, t);
        return t[n][sum] = countSubsetsMemo(arr, n - 1, sum, t) +
                countSubsetsMemo(arr, n - 1, sum - arr[n - 1], t);
    }

    // Bottom-up 2D DP - Time: O(n*sum), Space: O(n*sum)
    static int countSubsetsDP(int[] arr, int n, int sum) {
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
            }
        }
        return dp[n][sum];
    }

    // Space Optimized 1D DP - Time: O(n*sum), Space: O(sum)
    static int countSubsetsDPSpaceOptimised(int[] arr, int n, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;  // One way to make sum 0

        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= arr[i - 1]; j--) {
                dp[j] = dp[j] + dp[j - arr[i - 1]];
            }
        }
        return dp[sum];
    }


    static void main(String[] args) {
        int[] arr = {2, 3, 5, 6, 8, 10};
        int sum = 10;
        int n = arr.length;
        int[][] t = new int[n + 1][sum + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        System.out.println("Count Subsets Recursion: " + countSubsetsRecursion(arr, n, sum));
        System.out.println("Count Subsets Memoization: " + countSubsetsMemo(arr, n, sum, t));
        System.out.println("Count Subsets 2D DP: " + countSubsetsDP(arr, n, sum));
        System.out.println("Count Subsets Space Optimized: " + countSubsetsDPSpaceOptimised(arr, n, sum));
    }

}

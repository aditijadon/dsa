package dp.knapsack;

// Given a set of non-negative integers arr[] and a target sum S. Determine if there is a subset
// of arr[] that sums exactly to S.

import java.util.Arrays;

public class SubsetSum {

    // Recursive - Time: O(2^n), Space: O(n)
    static boolean isSubsetSumRecursion(int[] arr, int n, int sum) {
        if (sum == 0) return true;
        if (n == 0) return false;
        if (arr[n-1] > sum) return isSubsetSumRecursion(arr, n-1, sum);
        return isSubsetSumRecursion(arr, n-1, sum) || isSubsetSumRecursion(arr, n-1, sum - arr[n-1]);
    }

    // Memoization - Time: O(n*sum), Space: O(n*sum) + O(n) recursion stack
    static boolean isSubsetSumMemo(int[] arr, int n, int sum, Boolean[][] t) {
        if (sum == 0) return true;
        if (n == 0) return false;
        if (t[n][sum] != null) return t[n][sum];
        if (arr[n-1] > sum) return t[n][sum] = isSubsetSumMemo(arr, n-1, sum, t);
        return t[n][sum] = isSubsetSumMemo(arr, n-1, sum, t) || isSubsetSumMemo(arr, n-1, sum - arr[n-1], t);
    }

    // Bottom-up 2D DP - Time: O(n*sum), Space: O(n*sum)
    static boolean isSubsetSumDP(int[] arr, int sum, int n) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]];
            }
        }
        return dp[n][sum];
    }

    // Space Optimized 1D DP - Time: O(n*sum), Space: O(sum)
    static boolean isSubsetSumDPSpaceOptimised(int[] arr, int sum, int n) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= arr[i - 1]; j--) { // backward
                dp[j] = dp[j] || dp[j - arr[i - 1]];
            }
        }
        return dp[sum];
    }


    static void main(String[] args) {
        int arr[] = {3, 34, 4, 12, 5, 2};
        int target = 9;
        int n = 6;
        Boolean[][] t = new Boolean[n + 1][target + 1];
        System.out.println(isSubsetSumRecursion(arr, n, target));
        System.out.println(isSubsetSumMemo(arr, n, target, t));
        System.out.println(isSubsetSumDP(arr, target, n));
        System.out.println(isSubsetSumDPSpaceOptimised(arr, target, n));
    }
}

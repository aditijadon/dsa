package dp.knapsack;

import java.util.Arrays;

// Given N items with values val[] and weights wt[], and knapsack capacity W. Find the maximum value you can achieve
// without exceeding W (each item can be taken at most once).

public class Knapsack01 {

    // Recursive - Time: O(2^n), Space: O(n)
    static int knapsackRecursion(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0) return 0;
        if (wt[n - 1] > W) return knapsackRecursion(W, wt, val, n - 1);
        else return Math.max(val[n - 1] + knapsackRecursion(W - wt[n - 1], wt, val, n - 1),
                knapsackRecursion(W, wt, val, n - 1));
    }

    // Memoization - Time: O(n*W), Space: O(n*W) + O(n) recursion stack
    static int knapsackMemo(int W, int[] wt, int[] val, int n, int[][] t) {
        if (n == 0 || W == 0) return 0;
        if (t[n][W] != -1) return t[n][W];
        if (wt[n - 1] > W) return t[n][W] = knapsackMemo(W, wt, val, n - 1, t);
        else return t[n][W] = Math.max(val[n - 1] + knapsackMemo(W - wt[n - 1], wt, val, n - 1, t),
                    knapsackMemo(W, wt, val, n - 1, t));
    }

    // Bottom-up 2D DP - Time: O(n*W), Space: O(n*W)
    static int knapsackDP(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (wt[i - 1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
            }
        }
        return dp[n][W];
    }

    // Space Optimized 1D DP - Time: O(n*W), Space: O(W)
    static int knapsackDPSpaceOptimised(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = W; j >= wt[i - 1]; j--) {  // backwards to avoid reuse of numbers
                dp[j] = Math.max(val[i - 1] + dp[j - wt[i - 1]], dp[j]);
            }
        }
        return dp[W];
    }

 // W  0 1 2 3 ----      10                          20                  30
    // 0 0 0 0 0 0 0 0 0 60 60 60 60 60 --------------------------------------------
    // 0 0 0 0 0 0 0 0 0 60 60 60 60 -----  -------- 100 100 100         160 ------


    static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        int n = val.length;
        int[][] t = new int[n + 1][W + 1];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        System.out.println("Knapsack Recursion: " + knapsackRecursion(W, wt, val, n));
        System.out.println("Knapsack Memoization: " + knapsackMemo(W, wt, val, n, t));
        System.out.println("Knapsack 2D DP: " + knapsackDP(W, wt, val, n));
        System.out.println("Knapsack Space Optimized: " + knapsackDPSpaceOptimised(W, wt, val, n));
    }
}

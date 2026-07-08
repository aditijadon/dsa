package dp.unboundedKnapsack;

// Given N items with values val[] and weights wt[], and knapsack capacity W. Find the maximum value you can achieve
// without exceeding W (each item can be taken unlimited times).

public class UnboundedKnapsack {

    // Recursive - Time: O(2^n), Space: O(n)  [Exponential - not efficient]
    static int unboundedKnapsackRecursion(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0) return 0;
        if (wt[n-1] > W) return unboundedKnapsackRecursion(W, wt, val, n-1);
        return Math.max(val[n-1] + unboundedKnapsackRecursion(W - wt[n-1], wt, val, n),
                unboundedKnapsackRecursion(W, wt, val, n-1));
    }

    // Memoization - Time: O(n*W), Space: O(n*W) + O(n)
    static int unboundedKnapsackMemo(int W, int[] wt, int[] val, int n, int[][] t) {
        if (n == 0 || W == 0) return 0;
        if (t[n][W] != -1) return t[n][W];
        if (wt[n-1] > W) return t[n][W] = unboundedKnapsackMemo(W, wt, val, n-1, t);
        return t[n][W] = Math.max(val[n-1] + unboundedKnapsackMemo(W - wt[n-1], wt, val, n, t),
                unboundedKnapsackMemo(W, wt, val, n-1, t));
    }

    // Bottom-up 2D DP - Time: O(n*W), Space: O(n*W)
    static int unboundedKnapsackDP(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (wt[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(val[i-1] + dp[i][j - wt[i-1]], dp[i-1][j]);
            }
        }
        return dp[n][W];
    }

    // Space Optimized 1D DP - Time: O(n*W), Space: O(W)
    static int unboundedKnapsackSpaceOptimised(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = wt[i-1]; j <= W; j++) {   // Forward iteration (key difference)
                dp[j] = Math.max(dp[j], val[i-1] + dp[j - wt[i-1]]);
            }
        }
        return dp[W];
    }

    static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        int n = val.length;
        int[][] t = new int[n + 1][W + 1];
        for (int[] row : t) {
            java.util.Arrays.fill(row, -1);
        }
        System.out.println("Recursion              : " + unboundedKnapsackRecursion(W, wt, val, n));
        System.out.println("Memoization            : " + unboundedKnapsackMemo(W, wt, val, n, t));
        System.out.println("2D DP                  : " + unboundedKnapsackDP(W, wt, val, n));
        System.out.println("Space Optimized (1D)   : " + unboundedKnapsackSpaceOptimised(W, wt, val, n));
    }
}

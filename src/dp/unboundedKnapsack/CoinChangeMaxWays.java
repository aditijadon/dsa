package dp.unboundedKnapsack;

// Given an array of coins and a target amount. Return the number of combinations that make up that amount.
// (Order of coins does not matter → combinations, not permutations)

public class CoinChangeMaxWays {

    // Recursive - Time: O(2^n), Space: O(n)
    static int coinChangeWaysRecursion(int[] coins, int n, int amount) {
        if (amount == 0) return 1;
        if (n == 0) return 0;
        if (coins[n-1] > amount) return coinChangeWaysRecursion(coins, n-1, amount);
        return coinChangeWaysRecursion(coins, n, amount - coins[n-1])
                + coinChangeWaysRecursion(coins, n-1, amount);
    }

    // Memoization - Time: O(n*amount), Space: O(n*amount) + O(n)
    static int coinChangeWaysMemo(int[] coins, int n, int amount, int[][] t) {
        if (amount == 0) return 1;
        if (n == 0) return 0;
        if (t[n][amount] != -1) return t[n][amount];
        if (coins[n-1] > amount) return t[n][amount] = coinChangeWaysMemo(coins, n-1, amount, t);
        return t[n][amount] = coinChangeWaysMemo(coins, n, amount - coins[n-1], t)
                + coinChangeWaysMemo(coins, n-1, amount, t);
    }

    // Bottom-up 2D DP - Time: O(n*amount), Space: O(n*amount)
    static int coinChangeWaysDP(int[] coins, int n, int amount) {
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;  // One way to make amount 0
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i][j - coins[i-1]] + dp[i-1][j];
            }
        }
        return dp[n][amount];
    }

    // Space Optimized 1D DP - Time: O(n*amount), Space: O(amount)
    static int coinChangeWaysSpaceOptimised(int[] coins, int n, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;  // Base case

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i-1]; j <= amount; j++) {   // Forward iteration
                dp[j] = dp[j] + dp[j - coins[i-1]];
            }
        }
        return dp[amount];
    }

    static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 4;
        int n = coins.length;
        int[][] t = new int[n + 1][amount + 1];
        for (int[] row : t) {
            java.util.Arrays.fill(row, -1);
        }
        System.out.println("Recursion              : " + coinChangeWaysRecursion(coins, n, amount));
        System.out.println("Memoization            : " + coinChangeWaysMemo(coins, n, amount, t));
        System.out.println("2D DP                  : " + coinChangeWaysDP(coins, n, amount));
        System.out.println("Space Optimized (1D)   : " + coinChangeWaysSpaceOptimised(coins, n, amount));
    }
}

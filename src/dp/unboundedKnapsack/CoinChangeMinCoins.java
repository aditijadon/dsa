package dp.unboundedKnapsack;

// Given an array of coins and a target amount. Return the fewest number of coins needed to make up that amount.
// If impossible, return -1.

public class CoinChangeMinCoins {

    // Recursive - Time: O(2^n), Space: O(n)  [Very slow]
    static int coinChangeMinRecursion(int[] coins, int n, int amount) {
        if (amount == 0) return 0;
        if (n == 0) return Integer.MAX_VALUE - 1; // invalid
        if (coins[n-1] > amount) return coinChangeMinRecursion(coins, n-1, amount);
        int take = coinChangeMinRecursion(coins, n, amount - coins[n-1]);
        int notTake = coinChangeMinRecursion(coins, n-1, amount);
        if (take != Integer.MAX_VALUE - 1) take += 1;
        return Math.min(take, notTake);
    }

    // Memoization - Time: O(n*amount), Space: O(n*amount) + O(n)
    static int coinChangeMinMemo(int[] coins, int n, int amount, int[][] t) {
        if (amount == 0) return 0;
        if (n == 0) return Integer.MAX_VALUE - 1;
        if (t[n][amount] != -1) return t[n][amount];
        if (coins[n-1] > amount) return t[n][amount] = coinChangeMinMemo(coins, n-1, amount, t);
        int take = coinChangeMinMemo(coins, n, amount - coins[n-1], t);
        int notTake = coinChangeMinMemo(coins, n-1, amount, t);
        if (take != Integer.MAX_VALUE - 1) take += 1;
        return t[n][amount] = Math.min(take, notTake);
    }

    // Bottom-up 2D DP - Time: O(n*amount), Space: O(n*amount)
    static int coinChangeMinDP(int[] coins, int n, int amount) {
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0; // 0 coins for amount 0
        }
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE - 1; // impossible
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int take = dp[i][j - coins[i-1]] == Integer.MAX_VALUE - 1 ?
                            Integer.MAX_VALUE - 1 : dp[i][j - coins[i-1]] + 1;
                    dp[i][j] = Math.min(take, dp[i-1][j]);
                }
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    // Space Optimized 1D DP - Time: O(n*amount), Space: O(amount)
    static int coinChangeMinSpaceOptimised(int[] coins, int n, int amount) {
        int[] dp = new int[amount + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = coins[i-1]; j <= amount; j++) {
                if (dp[j - coins[i-1]] != Integer.MAX_VALUE - 1) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i-1]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int n = coins.length;
        int rec = coinChangeMinRecursion(coins, n, amount);
        int[][] t = new int[n + 1][amount + 1];
        for (int[] row : t) java.util.Arrays.fill(row, -1);
        int memo = coinChangeMinMemo(coins, n, amount, t);
        System.out.println("Recursion              : " + (rec == Integer.MAX_VALUE - 1 ? -1 : rec));
        System.out.println("Memoization            : " + (memo == Integer.MAX_VALUE - 1 ? -1 : memo));
        System.out.println("2D DP                  : " + coinChangeMinDP(coins, n, amount));
        System.out.println("Space Optimized (1D)   : " + coinChangeMinSpaceOptimised(coins, n, amount));
    }
}

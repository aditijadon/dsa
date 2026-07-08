package dp.unboundedKnapsack;

// Given a rod of length N and a price table where price[i] denotes the price of a piece of length i+1.
// Find the maximum value obtainable by cutting up the rod and selling the pieces.

public class RodCuttingProblem {

    // Recursive - Time: O(2^n), Space: O(n)
    static int rodCuttingRecursion(int[] price, int n, int N) {
        if (n == 0 || N == 0) return 0;
        if (n > N) return rodCuttingRecursion(price, n-1, N);
        return Math.max(price[n-1] + rodCuttingRecursion(price, n, N - n),
                rodCuttingRecursion(price, n-1, N));
    }

    // Memoization - Time: O(n*N), Space: O(n*N) + O(n)
    static int rodCuttingMemo(int[] price, int n, int N, int[][] t) {
        if (n == 0 || N == 0) return 0;
        if (t[n][N] != -1) return t[n][N];
        if (n > N) return t[n][N] = rodCuttingMemo(price, n-1, N, t);
        return t[n][N] = Math.max(price[n-1] + rodCuttingMemo(price, n, N - n, t),
                rodCuttingMemo(price, n-1, N, t));
    }

    // Bottom-up 2D DP - Time: O(n*N), Space: O(n*N)
    static int rodCuttingDP(int[] price, int n, int N) {
        int[][] dp = new int[n + 1][N + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= N; j++) {
                if (i > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(price[i-1] + dp[i][j - i], dp[i-1][j]);
            }
        }
        return dp[n][N];
    }

    // Space Optimized 1D DP - Time: O(n*N), Space: O(N)
    static int rodCuttingSpaceOptimised(int[] price, int n, int N) {
        int[] dp = new int[N + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= N; j++) {   // Forward direction
                dp[j] = Math.max(dp[j], price[i-1] + dp[j - i]);
            }
        }
        return dp[N];
    }

    static void main(String[] args) {
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20};  // price[i] for length i+1
        int N = 8;  // Rod length
        int n = price.length;
        int[][] t = new int[n + 1][N + 1];
        for (int[] row : t) {
            java.util.Arrays.fill(row, -1);
        }
        System.out.println("Recursion              : " + rodCuttingRecursion(price, n, N));
        System.out.println("Memoization            : " + rodCuttingMemo(price, n, N, t));
        System.out.println("2D DP                  : " + rodCuttingDP(price, n, N));
        System.out.println("Space Optimized (1D)   : " + rodCuttingSpaceOptimised(price, n, N));
    }
}

package dp.knapsack;

// Given an array nums of positive integers, return true if you can partition the array into two subsets such that
// the sum of the elements in both subsets are equal.

public class EqualSumPartition {

    // Recursive - Time: O(2^n), Space: O(n)
    static boolean isEqualSumRecursion(int[] arr, int n, int target) {
        if (target == 0) return true;
        if (n == 0) return false;
        if (arr[n-1] > target) return isEqualSumRecursion(arr, n-1, target);
        return isEqualSumRecursion(arr, n-1, target) || isEqualSumRecursion(arr, n-1, target - arr[n-1]);
    }

    // Memoization - Time: O(n*sum), Space: O(n*sum) + O(n) recursion stack
    static boolean isEqualSumMemo(int[] arr, int n, int target, Boolean[][] t) {
        if (target == 0) return true;
        if (n == 0) return false;
        if (t[n][target] != null) return t[n][target];
        if (arr[n-1] > target) return t[n][target] = isEqualSumMemo(arr, n-1, target, t);
        return t[n][target] = isEqualSumMemo(arr, n-1, target, t) || isEqualSumMemo(arr, n-1, target - arr[n-1], t);
    }

    // Bottom-up 2D DP - Time: O(n*sum), Space: O(n*sum)
    static boolean isEqualSumDP(int[] arr, int target, int n) {
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]];
            }
        }
        return dp[n][target];
    }

    // Space Optimized 1D DP - Time: O(n*target), Space: O(target)
    static boolean isEqualSumDPSpaceOptimised(int[] arr, int target, int n) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= arr[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - arr[i - 1]];
            }
        }
        return dp[target];
    }


    static void main(String[] args) {
        int arr[] = {1, 5, 11, 5};
        int n = arr.length;
        int totalSum = 0;
        for (int num : arr) totalSum += num;
        if (totalSum % 2 != 0) {
            System.out.println("Total sum is odd → false");
            return;
        }
        int target = totalSum / 2;
        Boolean[][] t = new Boolean[n + 1][target + 1];
        System.out.println("Equal Sum Partition Recursion: " + isEqualSumRecursion(arr, n, target));
        System.out.println("Equal Sum Partition Memoization: " + isEqualSumMemo(arr, n, target, t));
        System.out.println("Equal Sum Partition 2D DP: " + isEqualSumDP(arr, target, n));
        System.out.println("Equal Sum Partition Space Optimized: " + isEqualSumDPSpaceOptimised(arr, target, n));
    }
}

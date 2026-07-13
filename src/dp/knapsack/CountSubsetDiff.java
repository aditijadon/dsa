package dp.knapsack;

import java.util.Arrays;

public class CountSubsetDiff {
    // Sum(S1) - Sum(S2) = diff
    // Sum(S1) + Sum(S2) = Sum(arr)
    // Sum(S1) = (Sum(arr) + diff) / 2
    // Sum(S2) = (Sum(arr) - diff) / 2

    static int getCountRecursion(int[] arr, int target, int n) {
        if (target == 0) return 1;
        if (n == 0) return 0;
        if (arr[n - 1] > target) return getCountRecursion(arr, target, n - 1);
        else return getCountRecursion(arr, target, n - 1) + getCountRecursion(arr, target - arr[n - 1], n - 1);
    }

    static int getCountMemo(int[] arr, int target, int n, int[][] t) {
        if (target == 0) return 1;
        if (n == 0) return 0;
        if (t[n][target] != -1) return t[n][target];
        if (arr[n - 1] > target) return t[n][target] = getCountMemo(arr, n - 1, target, t);
        return t[n][target] = getCountMemo(arr, n - 1, target, t)
                + getCountMemo(arr, n - 1, target - arr[n - 1], t);
    }

    static int getCountSubsetDiffDp(int[] arr, int target, int n){
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for(int i=1; i<n+1; i++){
            for(int j=1; j<target+1; j++){
                if(arr[i-1] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
            }
        }
        return dp[n][target];
    }

    static int getCountSubsetDiffSpaceOptimised(int[] arr, int n, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= arr[i - 1]; j--) {
                dp[j] = dp[j] + dp[j - arr[i - 1]];
            }
        }
        return dp[target];
    }

    static void main(String[] args) {
        int[] arr = {1,1,2,3};
        int n = arr.length;
        int diff = 1;
        int sum = Arrays.stream(arr).sum();
        int target = (sum + diff) / 2;
        int[][] t = new int[n+1][target+1];
        System.out.println("Count of Subsets With difference (Recursion): " + getCountRecursion(arr, target, n));
        System.out.println("Count of Subsets With difference (Memoization): " + getCountMemo(arr, target, n, t));
        System.out.println("Count of Subsets With difference (DP): " + getCountSubsetDiffDp(arr, target, n));
        System.out.println("Count of Subsets With difference (Space Optimiezed): " + getCountSubsetDiffSpaceOptimised(arr, target, n));
    }
}

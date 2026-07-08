package array.binarySearch;

public class NthRoot {
    public static int NthRoot(int n, int m) {
        int low = 1, high = m;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midPower = checkPower(mid, n, m);

            if (midPower == 1) { // mid^n == m
                return mid;
            } else if (midPower == 0) { // mid^n < m
                low = mid + 1;
            } else { // mid^n > m
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int checkPower(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;
            if (ans > m) return 2; // Too big
        }
        if (ans == m) return 1; // Perfect match
        return 0; // Too small
    }

    public static void main(String[] args) {
        int m = 27, n = 3;
        System.out.println(NthRoot(n,m));
    }
}

package array.binarySearch;

import java.util.Arrays;

/*
You are given an array 'arr' of size 'n' which denotes the position of stalls. You are also given an integer 'k'
which denotes the number of aggressive cows. You are given the task of assigning stalls to 'k' cows such that the
minimum distance between any two of them is the maximum possible. Find the maximum possible minimum distance.
*/

public class AggressiveCows {

    static int findMaxMinDistance(int n, int k, int[] arr){           // O(n log n + n log({10 - 0})
        Arrays.sort(arr);
        int low = 1;
        int high = arr[n - 1] - arr[0]; // 10 - 0 = 10
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(arr, k, mid)) {
                ans = mid;
                low = mid + 1; // Try to see if a larger distance is possible
            } else {
                high = mid - 1; // Distance too large, try smaller
            }
        }
        return ans;
    }

    private static boolean canPlace(int[] arr, int k, int dist) {
        int count = 1; // Place first cow
        int lastPos = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - lastPos >= dist) {
                count++;
                lastPos = arr[i];
            }
            if (count >= k) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 6, k = 4, arr[] = {0,3,4,7,10,9};
        System.out.println(findMaxMinDistance(n, k, arr));
    }
}

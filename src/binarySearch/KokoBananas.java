package array.binarySearch;

import java.util.Arrays;

/*
You are given an integer array piles where piles[i] is the number of bananas in the ith pile. You are also given an
integer h, which represents the number of hours you have to eat all the bananas.You may decide your bananas-per-hour
eating rate of k. Each hour, you may choose a pile of bananas and eats k bananas from that pile. If the pile has less
than k bananas, you may finish eating the pile but you can not eat from another pile in the same hour. Return the
minimum integer k such that you can eat all the bananas within h hours.
*/

public class KokoBananas {
    static int findK(int[] piles, int h){   // O(log(max of piles) * n) and brute = O(max of piles * n)
        int left = 1, right = Arrays.stream(piles).max().getAsInt();
        int ans = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, h, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canFinish(int[] piles, int h, int speed) {
        long hoursSpent = 0;
        for (int p : piles) {
            hoursSpent += Math.ceil(p/speed);  // (pile + rate - 1) / rate
            if (hoursSpent > h) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int piles[] = {1,4,3,2};
        int hours = 9;
        System.out.println(findK(piles, hours));
    }
}

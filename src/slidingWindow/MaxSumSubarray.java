package array.slidingWindow;

public class MaxSumSubarray {
    public static int maxSubArraySum(int[] arr, int k) {
        int maxVal = 0, currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (i >= k - 1) {
                maxVal = Math.max(maxVal, currentSum);
                currentSum -= arr[i - (k - 1)];
            }
        }
        return maxVal;
    }
    public static void main(String[] args) {
        int arr[] = {2,4,7,8,6,1,0};
        int k = 3;
        System.out.println(maxSubArraySum(arr, k));
    }
}

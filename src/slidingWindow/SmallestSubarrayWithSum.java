package array.slidingWindow;

public class SmallestSubarrayWithSum {
    public static int smallestSubarray(int[] arr, int k){
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right];
            while (currentSum >= k) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= arr[left];
                left++;
            }
        }
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 1, 2, 4, 3};
        int k = 7;
        System.out.println(smallestSubarray(arr, k));
    }
}

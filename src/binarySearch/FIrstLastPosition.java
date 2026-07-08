package array.binarySearch;

public class FIrstLastPosition {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        result[0] = findBound(nums, target, true);  // Find first
        result[1] = findBound(nums, target, false); // Find last
        return result;
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (isFirst) right = mid - 1; // Keep looking left
                else left = mid + 1;         // Keep looking right
            } else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int arr[] = {5,8,8,8,8,10};
        int target = 8;
        System.out.println(searchRange(arr, target));
    }
}

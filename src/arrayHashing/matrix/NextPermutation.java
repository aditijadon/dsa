package miscellaneous;

public class NextPermutation {
//    For Brute Force:
//    Find all possible permutations --> Sort the permutations --> Print the next permutation
//    O(N!*N)


    static void getNextPermutation(int[] nums){   // longest prefix match
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {   // Find the first decreasing element from end
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            reverse(nums, 0, nums.length - 1); // Reverse the entire array
            return;
        }

        for (int i = nums.length - 1; i > index; i--) {   // Find just larger element
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break;
            }
        }

        reverse(nums, index + 1, nums.length - 1);  // Reverse part after index
    }

    // Helper to reverse array
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // Helper to swap
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {3,2,7,6,5,1};
        getNextPermutation(arr);
    }
}

package array.binarySearch;

public class RotatedSortedArray {
    static int search(int[] arr, int target){
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            // Identify the sorted half
            if (arr[left] <= arr[mid]) { // Left half is sorted
                if (target >= arr[left] && target < arr[mid]) right = mid - 1;
                else left = mid + 1;
            } else { // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }

    static int findMin(int[] arr){                       //Same for pivot
        int left = 0, right = arr.length - 1;
        if (arr[left] <= arr[right]) return arr[left];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int arr[] = {4,5,6,7,1,2,3};
        int target = 6;
        System.out.println(search(arr, target));
        System.out.println(findMin(arr));
    }
}
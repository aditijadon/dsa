package array.binarySearch;

public class BinarySearch {
    static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = left + (right - left) / 2;     // mid = (left + right) / 2
            if(arr[mid] == target) return mid;
            else if(arr[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {2,4,9,10,15,20,22};
        int target = 15;
        System.out.println(binarySearch(arr, target));
    }
}

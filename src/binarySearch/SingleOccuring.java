package array.binarySearch;

public class SingleOccuring {
    public static int singleNonDuplicate(int[] arr){
        int n = arr.length;

        if (n == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        int low = 1, high = n-2;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(arr[mid] != arr[mid-1] && arr[mid] != arr[mid+1]) return mid;

            if ((mid % 2 == 0 && arr[mid] == arr[mid + 1]) ||
                    (mid % 2 == 1 && arr[mid] == arr[mid - 1])) {
                // We are in the left half, single element is to the right
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        System.out.println("The single element is: " + singleNonDuplicate(arr));
    }
}

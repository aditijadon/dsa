package array.twoPointer;

public class TwoSumSorted {
    static int[] twoSum(int[] arr, int target){
        int i = 0;
        int j = arr.length-1;

        while(i < j){
            if(arr[i] + arr[j] > target) j--;
            else if(arr[i] + arr[j] < target) i++;
            else return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = {-5, -2, 3, 4, 6};  // sorted
        int target = -1;
        System.out.println(twoSum(arr, target)[0] + " " + twoSum(arr, target)[1]);
    }
}

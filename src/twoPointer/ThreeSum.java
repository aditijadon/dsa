package array.twoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    static List<List<Integer>> threeSum(int[] arr){
        List<List<Integer>> triplets = new ArrayList<>();
        if (arr == null || arr.length < 3) return triplets;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length-2; i++) {  // fix first element
            if (arr[i] > 0) break;  // Early termination: if smallest number is already > 0 -> no solution
            if (i > 0 && arr[i] == (arr[i - 1])) continue;  // skip duplicates

            int left = i + 1;
            int right = arr.length - 1;

            while(left < right){
                int sum = arr[i] + arr[left] + arr[right];
                if (sum > 0) right--;
                else if (sum < 0) left++;
                else {
                    triplets.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    while (left < right && arr[left] == arr[left + 1]) {  // Skip duplicates for left
                        left++;
                    }
                    while (left < right && arr[right] == arr[right - 1]) {   // Skip duplicates for right
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};   //--> {-4,-1,-1,0,1,2}
        System.out.println(threeSum(arr));
    }
}

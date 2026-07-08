package miscellaneous;

import java.util.Arrays;

public class Sort012 {
    static int[] sortBruteForce(int[] arr){  // O(n + n)
        int count0 = 0, count1 = 0, count2 = 0;
        for(int num : arr) {
            if(num == 0) count0++;
            else if(num == 1) count1++;
            else count2++;
        }

        int index = 0;
        while(count0-- > 0) {
            arr[index++] = 0;
        }

        while(count1-- > 0) {
            arr[index++] = 1;
        }

        while(count2-- > 0) {
            arr[index++] = 2;
        }
        return arr;
    }

    static int[] sort(int[] arr){      // Dutch National Flag algorithm -->  O(n)
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            }
            else if (arr[mid] == 1) {
                mid++;
            }
            else {
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};     //  0,0,2,1,1,2  -->  0,0,1,1,2,2
//        sortBruteForce(arr);
        System.out.println(Arrays.toString(sort(arr)));
    }
}

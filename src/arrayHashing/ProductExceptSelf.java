package array;

public class ProductExceptSelf {
    private static int[] findProductTwoArrays(int[] arr){
        int n = arr.length;
        int[] output = new int[n];
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];

        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * arr[i - 1];
        }

        rightProducts[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            rightProducts[i] = rightProducts[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < n; i++) {
            output[i] = leftProducts[i] * rightProducts[i];
        }
        return output;
    }

    private static int[] findProductOneArray(int[] arr){
        int n = arr.length;
        int[] output = new int[n];

        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * arr[i - 1];
        }

        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] *= rightProduct;
            rightProduct *= arr[i];
        }
        return output;
    }

    public static void main(String[] args) {
        int arr[] = {7,3,0,5,18,3,9,0};
        int num = 3;
        System.out.println(findProductTwoArrays(arr)[num]);
        System.out.println(findProductOneArray(arr)[num]);
    }
}



// Approach 1: Brute Force, time O(n*n), space O(1)
// Approach 2: Using division, time O(n), space O(1), does not work with zero
// Approach 3: Left right product (2 array), time O(n), space O(n)
// Approach 4: Left right product (1 array), time O(n), space O(n)
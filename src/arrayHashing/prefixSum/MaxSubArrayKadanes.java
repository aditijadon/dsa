package arrayHashing;

public class MaxSubArrayKadanes {
    static int maxSubArrayBruteForce(int[] nums){
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    static int maxSubArrayBetter(int[] nums){
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    static int maxSubArray(int[] nums){
        int maxi = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxi) maxi = sum;
            if (sum < 0) sum = 0;
        }
        return maxi;
    }

    public static void main(String[] args) {
        int[] arr = { -2, -3, -1, -5};
        System.out.println(maxSubArray(arr));
    }
}

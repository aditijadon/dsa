package array.twoPointer;

public class TrappingRainWater {
    static int trap(int[] height) {
        int n = height.length;
        int maxWater = 0;

        for (int i = 1; i < n - 1; i++) {
            int maxLeft = 0;
            for (int j = 0; j < i; j++) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            int maxRight = 0;
            for (int j = i + 1; j < n; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            int water = Math.min(maxLeft, maxRight) - height[i];
            if (water > 0) {
                maxWater += water;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        int[] height = {0,2,0,3,1,0,1,3,2,1};
        System.out.println(trap(height));
    }
}

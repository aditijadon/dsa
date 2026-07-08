package array.twoPointer;

public class ContainerWithMostWater {
    static int maxArea(int[] heights) {
        int left = 0, right = heights.length - 1;
        int maxWater = 0;

        while (left < right) {
            int water = Math.min(heights[left], heights[right]) * (right - left);
            maxWater = Math.max(maxWater, water);
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        int height[] = {1,7,2,5,4,7,3,6};
        System.out.println(maxArea(height));
    }
}

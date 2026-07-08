package array.binarySearch;

/*
Given a row-wise sorted matrix of size M*N, where M is no. of rows and N is no. of columns, find the median in the given matrix.
Note: M*N is odd.
*/


public class MedianMatrix {
    public static int findMedian(int[][] matrix, int R, int C) {    // O(R x log C x log(Range))
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        // Find the absolute range in the matrix
        for (int i = 0; i < R; i++) {
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][C - 1]);
        }

        int targetCount = (R * C + 1) / 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countSmallerThanMid(matrix, mid, R, C) < targetCount) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    //The median is the value where exactly half the elements in the matrix are less than or equal to it
    private static int countSmallerThanMid(int[][] matrix, int mid, int R, int C) {
        int count = 0;
        for (int i = 0; i < R; i++) {
            count += upperBound(matrix[i], mid);
        }
        return count;
    }

    private static int upperBound(int[] row, int mid) {
        int l = 0, h = row.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (row[m] <= mid) l = m + 1;
            else h = m - 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5},
                {2, 6, 9},
                {3, 6, 9}
        };

        int r = 3, c = 3;

        System.out.println(findMedian(matrix, r, c));
    }
}

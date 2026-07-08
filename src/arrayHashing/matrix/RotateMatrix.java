package arrayHashing;

public class RotateMatrix {
    /*             1   2   3   4            13   9   5   1
    *              5   6   7   8            14  10   6   2
    *              9  10   11  12           15  11   7   3
    *              13  14  15  16           16  12   8   4
    * */

    static int[][] rotateBruteForce(int[][] matrix){  // (i,j) -> (j,n-i-1) :
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }
        return rotated;
    }

    static int[][] rotate(int[][] matrix){  // transpose (i,j) -> (j,i) then reverse every row : O(n*n)
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Swap element at (i, j) with (j, i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            // Swap elements from both ends moving toward center
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotateBruteForce(matrix);
    }
}

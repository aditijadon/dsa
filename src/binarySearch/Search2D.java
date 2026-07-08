package array.binarySearch;

public class Search2D {
    // Each row sorted + each row's 1st is greater than previous row's last

    static boolean search(int[][] matrix, int target){
        int rows = matrix.length;
        int cols = matrix[0].length;

        int top = 0, bot = rows - 1;
        while (top <= bot) {
            int midRow = (top + bot) / 2;
            if (target > matrix[midRow][cols - 1]) {
                top = midRow + 1;
            } else if (target < matrix[midRow][0]) {
                bot = midRow - 1;
            } else {
                break;
            }
        }

        if (!(top <= bot)) {
            return false;
        }

        int row = (top + bot) / 2;
        int l = 0, r = cols - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target > matrix[row][m]) {
                l = m + 1;
            } else if (target < matrix[row][m]) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int matrix[][] = {{1,2,4,8},
                          {10,11,12,13},
                          {14,20,30,40}};
        int target = 10;
        System.out.println(search(matrix, target));
    }
}

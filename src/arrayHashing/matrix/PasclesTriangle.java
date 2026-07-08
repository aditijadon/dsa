package miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasclesTriangle {
    /*                       1
    *                      1   1
    *                    1   2   1
    *                  1   3   3   1
    *                1   4   6   4   1
    * */

    public static long getElement(int row, int col) {  // For nCr = n! / r! (n-r)! never solve all the factorials
        int n = row - 1; //5
        int r = col - 1; //2
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i);
            result = result / (i + 1);      // result * (n-i)/(i+1)
        }
        return result;
    }

    public static List<Long> getRow(int n) {
        List<Long> row = new ArrayList<>();
        long val = 1;
        row.add(val);

        for (int i = 1; i < n; i++) {
            val = val * (n - i) / i;
            row.add(val);
        }
        return row;
    }

    public static List<List<Integer>> getTriangle(int n) {  // (n*n)
        List<List<Integer>> triangle = new ArrayList();
        for(int i = 0; i < n; i++){
            List<Integer> row = new ArrayList<>(Collections.nCopies(i+1, 1));
            for (int j = 1; j < i; j++) {
                row.set(j, triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
//        getElement(3,2);
        System.out.println(getRow(5));
//        getTriangle(5);
    }
}

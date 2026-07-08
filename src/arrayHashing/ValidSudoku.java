package array;

import java.util.HashSet;

public class ValidSudoku {
    static boolean isValidSudoku(char[][] board){
        // Check rows
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rowSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!rowSet.add(board[i][j])) return false;
                }
            }
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            HashSet<Character> colSet = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    if (!colSet.add(board[i][j])) return false;
                }
            }
        }

        // Check 3x3 sub-boxes
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                HashSet<Character> boxSet = new HashSet<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[boxRow + i][boxCol + j] != '.') {
                            if (!boxSet.add(board[boxRow + i][boxCol + j])) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean isValidSudoku3DArray(char[][] board) {
        boolean[][][] seen = new boolean[3][9][9]; // [unitType][index][digit-1]
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int digit = board[i][j] - '1'; // 0-based digit (0-8)
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    // Check row
                    if (seen[0][i][digit]) return false;
                    seen[0][i][digit] = true;
                    // Check column
                    if (seen[1][j][digit]) return false;
                    seen[1][j][digit] = true;
                    // Check 3x3 box
                    if (seen[2][boxIndex][digit]) return false;
                    seen[2][boxIndex][digit] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] arr = {{'1','2','.','.','3','.','.','.','.'},
                        {'4','.','.','5','.','.','.','.','.'},
                        {'.','9','8','.','.','.','.','.','3'},
                        {'5','.','.','.','6','.','.','.','4'},
                        {'.','.','.','8','.','3','.','.','5'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','.','.','.','.','.','2','.','.'},
                        {'.','.','.','4','1','9','.','.','8'},
                        {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(arr));
        System.out.println(isValidSudoku3DArray(arr));
    }
}

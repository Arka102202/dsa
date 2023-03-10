import java.util.Arrays;

public class SudokuSolver {
    static char[][] uniBoard;
    static int[][] record;
    static void solveSudoku(char[][] board) {
        char[] characters9 = {'1', '2', '3', '4','5','6','7','8','9'};
        uniBoard = new char[9][9];
        for(int i=0; i<9; i++){
            System.arraycopy(board[i], 0, uniBoard[i], 0, 9);
        }
        record = new int[board.length][board.length];
        for (int a = 0; a < board.length; a++) Arrays.fill(record[a], -1);
        solve(0, 0, characters9, board.length, board);
        for(int i=0; i<9; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("\n\n");
    }
    static boolean solve(int row, int start, char[] characters, int size, char[][] board) {
        boolean notOnce = true;
        if (start == size && row < size) {
            row += 1;
            start = 0;
        }
        if(row == size) return true;
        if (uniBoard[row][start] == '.') {
            for (int k = record[row][start] + 1; k < size; k++) { // index of number stack
                char ch = characters[k];
                if (checkValidity1(row, start, ch, size, board)) {
                    board[row][start] = ch;
                    if (k == size - 1) record[row][start] = -1;
                    else record[row][start] = k;
                    notOnce = false;
                    if(solve(row, start + 1, characters, size, board)) return true;
                }else
                    notOnce = true;
            }
            if (notOnce) record[row][start] = -1;
        } else return solve(row, start + 1, characters, size, board);
        board[row][start] = '.';
        return false;
    }
    static boolean checkValidity1(int start, int i, char ch, int size, char[][] board) {
        for (int j = 0; j < size; j++) {
            if (board[start][j] == ch) return false;
        }
        for (int j = 0; j < size; j++) {
            if (board[j][i] == ch) return false;
        }
        if (start < 3) {
            if (i < 3) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) if (board[j][k] == ch) return false;
                }
            }
            else if (i < 6) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 3; k < 6; k++) if (board[j][k] == ch) return false;
                }
            }
            else {
                for (int j = 0; j < 3; j++) {
                    for (int k = 6; k < 9; k++) if (board[j][k] == ch) return false;
                }
            }
        }
        else if (start < 6) {
            if (i < 3) {
                for (int j = 3; j < 6; j++) {
                    for (int k = 0; k < 3; k++) if (board[j][k] == ch) return false;
                }
            } else if (i < 6) {
                for (int j = 3; j < 6; j++) {
                    for (int k = 3; k < 6; k++) if (board[j][k] == ch) return false;
                }
            } else {
                for (int j = 3; j < 6; j++) {
                    for (int k = 6; k < 9; k++) if (board[j][k] == ch) return false;
                }
            }
        }
        else {
            if (i < 3) {
                for (int j = 6; j < 9; j++) {
                    for (int k = 0; k < 3; k++) if (board[j][k] == ch) return false;
                }
            }
            else if (i < 6) {
                for (int j = 6; j < 9; j++) {
                    for (int k = 3; k < 6; k++) if (board[j][k] == ch) return false;
                }
            }
            else {
                for (int j = 6; j < 9; j++) {
                    for (int k = 6; k < 9; k++) if (board[j][k] == ch) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        char[][] board1 = {{'5', '3', '.',  '.', '7', '.',  '.', '.', '.'},
                {'6', '.', '.',  '1', '9', '5',  '.', '.', '.'},
                {'.', '9', '8',  '.', '.', '.',  '.', '6', '.'},

                {'8', '.', '.',  '.', '6', '.',  '.', '.', '3'},
                {'4', '.', '.',  '8', '.', '3',  '.', '.', '1'},
                {'7', '.', '.',  '.', '2', '.',  '.', '.', '6'},

                {'.', '6', '.',  '.', '.', '.',  '2', '8', '.'},
                {'.', '.', '.',  '4', '1', '9',  '.', '.', '5'},
                {'.', '.', '.',  '.', '8', '.',  '.', '7', '9'}};

        solveSudoku(board1);

        char[][] board2 = {{'.', '.', '3',  '.', '.', '.',  '.', '.', '6'},
                           {'.', '6', '.',  '3', '2', '9',  '.', '.', '.'},
                           {'9', '.', '8',  '4', '.', '.',  '.', '.', '3'},

                           {'.', '1', '.',  '.', '.', '3',  '.', '.', '.'},
                           {'.', '.', '9',  '.', '.', '.',  '.', '8', '.'},
                           {'.', '7', '.',  '1', '9', '.',  '.', '.', '2'},

                           {'.', '.', '.',  '.', '.', '5',  '7', '.', '.'},
                           {'4', '.', '.',  '2', '.', '6',  '.', '1', '.'},
                           {'.', '.', '.',  '.', '.', '4',  '9', '.', '5'}};

        solveSudoku(board2);
        int[] i = new int[0];

    }
}

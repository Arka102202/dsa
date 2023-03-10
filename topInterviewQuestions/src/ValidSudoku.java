public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] != '.' && !checkValidity1(i,j,board[i][j], board))
                    return false;
            }
        }
        return true;
    }

    static boolean checkValidity1(int row, int col, char ch, char[][] board) {
        for (int j = 0; j < 9; j++) {
            if (j != col && board[row][j] == ch) return false;
        }
        for (int j = 0; j < 9; j++) {
            if (j != row && board[j][col] == ch) return false;
        }
        if (row < 3) {
            if (col < 3) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
            else if (col < 6) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 3; k < 6; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
            else {
                for (int j = 0; j < 3; j++) {
                    for (int k = 6; k < 9; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
        }
        else if (row < 6) {
            if (col < 3) {
                for (int j = 3; j < 6; j++) {
                    for (int k = 0; k < 3; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            } else if (col < 6) {
                for (int j = 3; j < 6; j++) {
                    for (int k = 3; k < 6; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            } else {
                for (int j = 3; j < 6; j++) {
                    for (int k = 6; k < 9; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
        }
        else {
            if (col < 3) {
                for (int j = 6; j < 9; j++) {
                    for (int k = 0; k < 3; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
            else if (col < 6) {
                for (int j = 6; j < 9; j++) {
                    for (int k = 3; k < 6; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
            else {
                for (int j = 6; j < 9; j++) {
                    for (int k = 6; k < 9; k++)
                        if (j != row && k != col && board[j][k] == ch) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{{'.','.','4','.','.','.','6','3','.'},{'.','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','9','.'},{'.','.','.','5','6','.','.','.','.'},{'4','.','3','.','.','.','.','.','1'},{'.','.','.','7','.','.','.','.','.'},{'.','.','.','5','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}}));
    }
}

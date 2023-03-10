import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordSearch2 {

    static int rowSize, columnSize;
    static boolean[][] visited;

    static String[][] memo;

    public static List<String> findWords(char[][] board, String[] words) {

        List<String> list = new ArrayList<>();
        rowSize = board.length;
        columnSize = board[0].length;
        memo = new String[rowSize+1][columnSize+1];
        for (String word : words) {
            if (exist(board, word)) list.add(word);
        }

        return list;
    }

    public static boolean exist(char[][] board, String word) {
        visited = new boolean[rowSize][columnSize];
        boolean result;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    result = processDynamically(board, i, j, word, 0);
                    if (result) {
                        memo[i][j] = word;
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }
    static boolean processDynamically(char[][] board, int i, int j, String word, int index) {

        if (index == word.length() - 1) return true;
        if(Objects.equals(memo[i][j], word.substring(index))) {
            System.out.println("used");
            return true;
        }
        boolean result = false;
        char ch = word.charAt(index + 1);
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            switch (k) {
                case 0:// right
                    if (j < columnSize - 1 && index < word.length() - 1 && board[i][j + 1] == ch && !visited[i][j+1])
                        result = processDynamically(board, i, j + 1, word, index + 1);
                    break;
                case 1:// down
                    if (i < rowSize - 1 && index < word.length() - 1 && board[i + 1][j] == ch && !visited[i+1][j])
                        result = processDynamically(board, i + 1, j, word, index + 1);
                    break;
                case 2:// left
                    if (j > 0 && index < word.length() - 1 && board[i][j - 1] == ch && !visited[i][j-1])
                        result = processDynamically(board, i, j - 1, word, index + 1);
                    break;
                case 3:// up
                    if (i > 0 && index < word.length() - 1 && board[i - 1][j] == ch && !visited[i-1][j])
                        result = processDynamically(board, i - 1, j, word, index + 1);
                    break;
            }
            if (result) return true;
        }
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(findWords(new char[][]{{'o','a','a','n'},{'e','t','r','n'},{'i','h','a','i'},{'i','f','l','v'}}, new String[]{"rain","train"}));
        System.out.println(findWords(new char[][]{{'c', 'a', 'a'}, {'a', 'a', 'a'}, {'b', 'c', 'd'}}, new String[]{"aab","aad","cbc"}));
        System.out.println(exist(new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'e', 's'}, {'a', 'd', 'e', 'e'}}, "abcefsadeese"));
        System.out.println(exist(new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}}, "abcced"));
        System.out.println(exist(new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}}, "see"));
        System.out.println(exist(new char[][]{{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'}, {'a', 'd', 'e', 'e'}}, "abcb"));
        System.out.println(exist(new char[][]{{'a', 'r', 'k', 'a', 'e'}, {'a', 'u', 'y', 'd', 'a'}, {'d', 't', 'd', 'a', 'g'}, {'i', 'i', 'k', 'r', 'a'}, {'s', 's', 'i', 'r', 'a'}}, "arkadyuti"));
        System.out.println(exist(new char[][]{{'a', 'r', 'k', 'a', 'e'}, {'a', 'u', 'y', 'd', 'a'}, {'d', 't', 'd', 'a', 'g'}, {'i', 'i', 'k', 'r', 'a'}, {'s', 's', 'i', 'r', 'a'}}, "joyeeta"));
    }
}

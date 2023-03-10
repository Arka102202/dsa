import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N_Queens {
    static List<List<String>> lists = new ArrayList<>();
    static int solveNQueens(int n) {
        lists.clear();
        boolean[] usedCol = new boolean[n];
        boolean[] RULD = new boolean[(n << 1) - 1];
        boolean[] rowLURD = new boolean[n];
        boolean[] colLURD = new boolean[n];
        generate(n, 0, usedCol, RULD, rowLURD, colLURD, new LinkedList<>());
        return lists.size();
    }
    static void generate(int n, int start, boolean[] usedCol, boolean[] RULD, boolean[] rowLURD, boolean[] colLURD,
                         LinkedList<String> list) {
        if (start == n) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int j = 0; j < n; j++) {//column
            if (!usedCol[j] && !RULD[start + j] &&
                    ((start >= j && !rowLURD[start - j]) || (start < j && !colLURD[j - start]))) {
                String str = ".".repeat(j) + "Q" + ".".repeat(n - (j + 1));
                list.add(str);
                usedCol[j] = true;
                RULD[start + j] = true;
                if (start >= j) rowLURD[start - j] = true;
                else colLURD[j - start] = true;
                generate(n, start + 1, usedCol, RULD, rowLURD, colLURD, list);
                if (list.size() > 0) list.removeLast();
                usedCol[j] = false;
                RULD[start + j] = false;
                if (start >= j) rowLURD[start - j] = false;
                else colLURD[j - start] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(1));
        System.out.println(solveNQueens(2));
        System.out.println(solveNQueens(3));
        System.out.println(solveNQueens(4));
        System.out.println(solveNQueens(5));
        System.out.println(solveNQueens(6));
        System.out.println(solveNQueens(7));
        System.out.println(solveNQueens(8));
        System.out.println(solveNQueens(9));
        System.out.println(solveNQueens(10));
        System.out.println(solveNQueens(12));
    }

//    RULD ==> right-up_left-down
//    LURD ==> left-up_right-down
}

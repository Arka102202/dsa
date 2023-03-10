import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class MaxNumberOfConnectedBlock {
    static int rowSize, columnSize;
    static HashSet<Integer> visited = new HashSet<>();
    static int[][] memo;
    static int maxNumberOfConnectedBlock(int[][] blocks) {
        visited.clear();
        TreeSet<Integer> counts = new TreeSet<>();
        rowSize = blocks.length;
        columnSize = blocks[0].length;
        memo = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) Arrays.fill(memo[i],-1);
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (blocks[i][j] == 1) counts.add(processDynamically(blocks, i, j));
            }
        }
        return counts.last();
    }
    static int processDynamically(int[][] blocks, int i, int j) {
        int maxCount = -1;
        int count = 1;
        visited.add((i + "" + j).hashCode());
        if(memo[i][j] != -1) return memo[i][j];
        for (int k = 0; k < 8; k++) {
            switch (k) {
                case 0:// right
                    if (j < columnSize - 1 && blocks[i][j + 1] == 1 && !visited.contains((i + "" + (j + 1)).hashCode()))
                        count += processDynamically(blocks, i, j + 1);
                    break;
                case 1:// right-down
                    if (i < rowSize - 1 && j < columnSize - 1 && blocks[i + 1][j + 1] == 1 && !visited.contains(((i + 1) + "" + (j + 1)).hashCode()))
                        count += processDynamically(blocks, i + 1, j + 1);
                    break;
                case 2:// down
                    if (i < rowSize - 1 && blocks[i + 1][j] == 1 && !visited.contains(((i + 1) + "" + j).hashCode()))
                        count += processDynamically(blocks, i + 1, j);
                    break;
                case 3:// left-down
                    if (i < rowSize - 1 && j > 0 && blocks[i + 1][j - 1] == 1 && !visited.contains(((i + 1) + "" + (j - 1)).hashCode()))
                        count += processDynamically(blocks, i + 1, j - 1);
                    break;
                case 4:// left
                    if (j > 0 && blocks[i][j - 1] == 1 && !visited.contains(((i) + "" + (j - 1)).hashCode()))
                        count += processDynamically(blocks, i, j - 1);
                    break;
                case 5:// left-up
                    if (i > 0 && j > 0 && blocks[i - 1][j - 1] == 1 && !visited.contains(((i - 1) + "" + (j - 1)).hashCode()))
                        count += processDynamically(blocks, i - 1, j - 1);
                    break;
                case 6:// up
                    if (i > 0 && blocks[i - 1][j] == 1 && !visited.contains(((i - 1) + "" + (j)).hashCode()))
                        count += processDynamically(blocks, i - 1, j);
                    break;
                case 7:// right-up
                    if (i > 0 && j < columnSize - 1 && blocks[i - 1][j + 1] == 1 && !visited.contains(((i - 1) + "" + (j + 1)).hashCode()))
                        count += processDynamically(blocks, i - 1, j + 1);
                    break;
            }
        }
        if (count != -1 && count > maxCount) maxCount = count;
        memo[i][j] =maxCount;
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{0, 0, 1}, {0, 0, 1}, {1, 1, 1}}));
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}}));
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{0, 0, 1}, {0, 0, 1}, {1, 1, 0}}));
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 1}}));
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{1, 1, 0, 0, 0}, {0, 1, 1, 0, 0}, {0, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 1}}));
        System.out.println(maxNumberOfConnectedBlock(new int[][]{{1,1,1,1,1,0,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,0,1,1,1,1,1,1},{1,1,1,1,1,1,0,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1}}));

    }
}

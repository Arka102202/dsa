import java.util.Arrays;

public class RotateImage {
    public static void rotate(int[][] matrix) {
        int i = 0, j = 0, temp,len = matrix.length - 1;

        while (i < (matrix.length / 2)) {
            for (int k = 0; k < len - (2 * i); k++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[len - j][i];
                matrix[len - j][i] = matrix[len - i][len - j];
                matrix[len - i][len - j] = matrix[j][len - i];
                matrix[j][len - i] = temp;
                j++;
            }
            j = i +=1;
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void main(String[] args) {

        rotate(new int[][]{{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        rotate(new int[][]{{5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}});
    }

}

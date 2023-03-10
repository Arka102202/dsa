import java.util.Arrays;

public class RepeatedCharacter {


    public static char repeatedCharacter(String s) {

        char ch = '0';
        int[] arr = new int[s.length()];
        int i = 0;

        for (char c : s.toCharArray()) {
            ch = c;
            if (arr.length > 0) {
                int position = Arrays.binarySearch(arr, c);
                if (position >= 0) break;
            }
            arr[i] = c;
            Arrays.sort(arr);


        }


        return ch;
    }


    public static int equalPairs(int[][] grid) {

        int[] stringHashRow = new int[grid.length];
        int[] stringHashCol = new int[grid.length];

        int hashCode = 0;
        int count = 0;
        int[] arr = new int[grid.length];
        int k = 0;
        int l = 0;


        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (j == 0) {
                    hashCode = Arrays.hashCode(grid[i]);
                    stringHashRow[k] = hashCode;
                    k++;
                }
                arr[i] = grid[i][j];
            }
            hashCode = Arrays.hashCode(arr);
            stringHashCol[l] = hashCode;
            l++;
        }

        for(int i:stringHashRow){
            for(int j=0; j<stringHashCol.length; j++){
                if(i == stringHashCol[j]) count++;
            }
        }




        return count;

    }


    public static void main(String[] args) {

        System.out.println(repeatedCharacter("htknlasmhg"));

        System.out.println(equalPairs(new int[][]{{3,2,1},{1,7,6},{2,7,7}}));

    }

}

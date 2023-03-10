import java.util.HashMap;
import java.util.TreeSet;

public class  MaximizeScoreAfterNOperations {

    static TreeSet<Integer> set = new TreeSet<>();
    static HashMap<Integer, Integer> memo = new HashMap<>();
    public static int maxScore(int[] nums) {

        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(maxScore1(new int[]{773274, 313112, 131789, 222437, 918065, 49745, 321270, 74163, 900218, 80160, 325440, 961730}));
//        System.out.println(h);
//        System.out.println(maxScore(new int[]{773274, 313112, 131789, 222437, 918065, 49745, 321270, 74163, 900218, 80160, 325440, 961730}));
//        System.out.println(h);
//        System.out.println(maxScore(new int[]{765621, 534896, 219248, 635069, 452624, 856729, 21830, 236322, 18733, 416616}));
//        System.out.println(maxScore(new int[]{171651, 546244, 880754, 412358}));
//        System.out.println(maxScore(new int[]{925612, 737192, 813525, 707250}));
//        System.out.println(maxScore(new int[]{697035, 181412, 384958, 575458}));
        System.out.println(maxScore1(new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(h);
        System.out.println(maxScore1(new int[]{3, 4, 6, 8}));
//        System.out.println(maxScore(new int[]{3, 4, 6, 8}));
//        System.out.println(h);
//        System.out.println(maxScore(new int[]{1, 2}));

    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    static int dfs(int[] n, int[][] dp, int i, int mask) {
        if (i > n.length / 2)
            return 0;
        if (dp[i][mask] == 0)
            for (int j = 0; j < n.length; ++j)
                for (int k = j + 1; k < n.length; ++k) {
                    int new_mask = (1 << j) + (1 << k);
                    if ((mask & new_mask) == 0) {
                        dp[i][mask] = Math.max(dp[i][mask], i * gcd(n[j], n[k]) + dfs(n, dp, i + 1, mask + new_mask));
                    }
                }
        return dp[i][mask];
    }
    static int maxScore1(int[] n) {;
        int[][] some  = new int[n.length / 2 + 1][1 << n.length];
        int a =  dfs(n, some, 1, 0);
        return a;
    }
}
























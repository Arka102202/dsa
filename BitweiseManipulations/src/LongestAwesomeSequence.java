import java.util.Arrays;

public class LongestAwesomeSequence {

    static int[] numberCountMap = new int[10];
    static String awesomeString;

    public static int longestAwesome0(String s) {
        numberCountMap = new int[10];
        boolean longestAwesomeSequence = true;
        int len = s.length();
        while (len >= 1) {
            for (int i = 0; i < s.length(); i++) {
                if (len + i <= s.length()) {
                    for (int j = i; j < len + i; j++) {
                        int num = s.charAt(j) - 48;
                        numberCountMap[num] += 1;
                    }
                    String substring = s.substring(i, len + i);
                    boolean firstOne = false;
                    for (int num = 0; num < 10; num++) {
                        if ((numberCountMap[num] & 1) != 0 && firstOne) {
                            longestAwesomeSequence = false;
                            break;
                        }
                        else if (!firstOne && (numberCountMap[num] & 1) == 1) firstOne = true;
                        else if (firstOne && (numberCountMap[num] & 1) == 1) {
                            longestAwesomeSequence = false;
                            break;
                        }
                    }
                    if (longestAwesomeSequence) {
                        awesomeString = substring;
                        return len;
                    }
                } else break;
                longestAwesomeSequence = true;
                numberCountMap = new int[10];
            }
            len -= 1;
        }
        return 0;

    }

    public static int longestAwesome1(String s) {
        int[] dp = new int[1024];
        Arrays.fill(dp, s.length());
        int res = 0, mask = 0;
        dp[0] = -1;
        for (int i = 0; i < s.length(); ++i) {
            mask ^= 1 << (s.charAt(i) - '0');
            res = Math.max(res, i - dp[mask]);
            for (int j = 0; j <= 9; ++j)
                res = Math.max(res, i - dp[mask ^ (1 << j)]);
            dp[mask] = Math.min(dp[mask], i);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(longestAwesome1(""));
        System.out.println(awesomeString);
    }

}
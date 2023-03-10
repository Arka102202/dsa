import java.util.Arrays;

public class RegularExpressionMatching {
    static Boolean[][] memo;
    public static boolean isMatch(String s, String p) {
        memo = new Boolean[s.length()+1][p.length()+1];
        for (Boolean[] b:memo) Arrays.fill(b,null);
        return checkPattern(s, p, 0,0);
    }
    static boolean checkPattern(String s, String p, int is, int ip) {
        if (ip == p.length()) {
            memo[is][ip] = s.isEmpty();
            return is == s.length();
        }
        if (memo[is][ip] != null) return memo[is][ip];
        boolean match = is < s.length() && (p.charAt(ip) == '.' || s.charAt(is) == p.charAt(ip));
        if (ip+1 < p.length() && p.charAt(ip+1) == '*') {
            if (match) {
                if (!checkPattern(s, p, is+1, ip))
                    return checkPattern(s, p, is, ip+2);
                else return memo[is][ip] = true;
            } else return checkPattern(s, p, is, ip+2);
        } else return (match && checkPattern(s, p, is+1, ip+1));
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
        System.out.println(isMatch("aab", "c*a*b*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", "a*"));
    }
}
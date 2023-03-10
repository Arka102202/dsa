import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {

    static List<String> validList = new ArrayList<>();
    public static List<String> removeInvalidParentheses(String s) {
        validList.clear();
        int r, l;
        int[] rl = invalidCalculation(s);
        l = rl[0];
        r = rl[1];
        if (r > 0 || l > 0) {
            for (int i = 0; i < s.length(); i++) {
                if (i == 0 || s.charAt(i) != s.charAt(i - 1)) generate(s, i, l, 0, r, 0, s.substring(0, i));
            }
            return validList;
        } else return List.of(s);
    }
    static void generate(String s, int start, int l, int lCount, int r, int rCount, String str) {
        if (rCount == r && lCount == l) validate(str.concat(s.substring(start)));
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) < 'a' || s.charAt(i) > 'z') {
                if (s.charAt(i) == '(' && lCount < l) {
                    lCount++;
                    generate(s, i + 1, l, lCount, r, rCount, str);
                    lCount--;
                } else if (s.charAt(i) == ')' && rCount < r) {
                    rCount++;
                    generate(s, i + 1, l, lCount, r, rCount, str);
                    rCount--;
                }
            }
            if(i < s.length()-1 && s.charAt(i) != s.charAt(i+1)) str = str.concat(s.substring(i, i + 1));
            else if (i <= s.length() - 2 ) {
                str = str.concat(s.substring(i, i + 2));
                i++;
            }
        }
    }
    static void validate(String str) {
        int r = 0, l = 0;
        boolean valid = true;
        for (int j = 0; j < str.length(); j++) {

            if (str.charAt(j) < 'a' || str.charAt(j) > 'z') {
                if (r > l || j == str.length() - 1 && l != r + 1) {
                    valid = false;
                    break;
                } else if (str.charAt(j) == '(') l++;
                else if (str.charAt(j) == ')') r++;
            }
        }
        if (valid && !validList.contains(str)) validList.add(str);
    }
    static int[] invalidCalculation(String s) {
        int l = 0, r = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') l++;
            else if (ch == ')') {
                if (l > 0) l--;
                else r++;
            }
        }
        return new int[]{l, r};
    }


    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("))))))))))))))))))))aaaaa"));
        System.out.println(removeInvalidParentheses("(()x"));
        System.out.println(removeInvalidParentheses("x("));
        System.out.println(removeInvalidParentheses("(())())()(()))(()"));
        System.out.println(removeInvalidParentheses(")("));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses("()))((()"));
        System.out.println(removeInvalidParentheses("()())()"));
    }


}

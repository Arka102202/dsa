import java.util.LinkedList;

public class LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        int maxAns = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")(((((()())()()))()(()))("));
    }


}

import java.util.Stack;

public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
        }

        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (seen[curr]) continue; // if seen continue as we need to pick one char only
            while (!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()]){
                seen[st.pop()] = false; // pop out and mark unseen
            }
            st.push(curr); // add into stack
            seen[curr] = true; // mark seen
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append((char) (st.pop() + 'a'));
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("acbac"));
        System.out.println(removeDuplicateLetters("beeaddbeb"));
        System.out.println(removeDuplicateLetters("cbac"));
        System.out.println(removeDuplicateLetters("bcaab"));
        System.out.println(removeDuplicateLetters("ccacbaba"));
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("consecutive"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("careermonk"));
        System.out.println(removeDuplicateLetters("mississippi"));


    }


}

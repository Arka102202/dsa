public class LongestPalindromicSubstring {


    public static String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        int idx1 = 0, idx2 = 0, last = 0, count1 = 0, count2 = 0, temp, maxLen = Integer.MIN_VALUE;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            temp = i;
            if (i - 1 >= last && s.charAt(i) == s.charAt(i - 1)) {
                idx1 = i - 1;
                count1 += 2;
                while (--idx1 >= last && ++i < s.length() && s.charAt(i) == s.charAt(idx1))
                    count1 += 2;
            }
            i = temp;
            if (i - 2 >= last && s.charAt(i) == s.charAt(i - 2)) {
                idx2 = i - 2;
                count2 += 3;
                while (--idx2 >= last && ++i < s.length() && s.charAt(i) == s.charAt(idx2))
                    count2 += 2;
            }
            i = temp;
            if (count1 > count2 && count1 > maxLen) {
                maxLen = count1;
                res = s.substring(idx1+1, (idx1+count1+1));
            }else if (count2>0 && count2 > maxLen){
                maxLen = count2;
                res = s.substring(idx2+1, (idx2+count2+1));
            }
            count2 = count1 = 0;
        }
        return res.length() == 0?s.substring(0,1):res;
    }


    public static void main(String[] args) {

        System.out.println(longestPalindrome("bccc"));
        System.out.println(longestPalindrome("babadbaab"));
        System.out.println(longestPalindrome("baabbabad"));


    }


}
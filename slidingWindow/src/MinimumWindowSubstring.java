import java.util.Arrays;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        String str = "";
        int min = -1, max, counter = 0, length = Integer.MAX_VALUE;
        int[] charArr = new int[52];
        boolean[] zeroFlag = new boolean[52];
        Arrays.fill(charArr, -1);
        for (char ch : t.toCharArray()) {
            if (ch >= 'a') {
                if (charArr[ch - 'a'] == -1) charArr[ch - 'a'] = 1;
                else charArr[ch - 'a'] += 1;
            } else {
                if (charArr[ch - 'A' + 26] == -1) charArr[ch - 'A' + 26] = 1;
                else charArr[ch - 'A' + 26] += 1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && charArr[ch - 'a'] != -1) {
                if (min == -1) min = i;
                if (charArr[ch - 'a'] > 0 && !zeroFlag[ch - 'a']) {
                    charArr[ch - 'a'] -= 1;
                    counter++;
                } else {
                    charArr[ch - 'a'] += 1;
                    zeroFlag[ch - 'a'] = true;
                }
            } else if (ch <= 'Z' && charArr[(ch - 'A') + 26] != -1) {
                if (min == -1) min = i;
                if (charArr[ch - 'A' + 26] > 0 && !zeroFlag[ch - 'A' + 26]) {
                    charArr[ch - 'A' + 26] -= 1;
                    counter++;
                } else {
                    charArr[ch - 'A' + 26] += 1;
                    zeroFlag[ch - 'A' + 26] = true;
                }

            }
            if (counter == t.length()) {
                max = i;
                for (; min <= max; min++) {
                    ch = s.charAt(min);

                    if (ch >= 'a' && charArr[ch - 'a'] > 0) charArr[ch - 'a'] -= 1;
                    else if (ch >= 'a' && charArr[ch - 'a'] == 0) {
                        zeroFlag[ch - 'a'] = false;
                        break;
                    }
                    if (ch <= 'Z' && charArr[(ch - 'A') + 26] > 0) charArr[ch - 'A' + 26] -= 1;
                    else if (ch <= 'Z' && charArr[(ch - 'A') + 26] == 0) {
                        zeroFlag[ch - 'A' + 26] = false;
                        break;
                    }
                }
                if (length > (max - min)) {
                    length = max - min;
                    str = s.substring(min, max + 1);
                    if (str.length() == t.length()) return str;
                }

            }
        }

        return str;
    }


    public static void main(String[] args) {

        System.out.println(minWindow("baaaabab", "abb"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("AAACAB", "ABC"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

}

import java.util.LinkedList;
import java.util.List;

public class RestoreIpAddresses {
    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        LinkedList<String> list = new LinkedList<>();
        return generate(s, 0, "", list, 0);
    }
    public static List<String> generate(String s, int start, String sb,LinkedList<String> list, int dotCount) {

        if (dotCount == 3 && s.length() - start <= 3 && Integer.parseInt(s.substring(start)) <= 255) {
            String substring = s.substring(start);
            sb = sb.concat(substring);
            boolean fullLength = sb.length() == s.length() + 3;
            if(substring.length() >= 2 && substring.charAt(0) != '0' && fullLength) list.add(sb);
            else if (substring.length() == 1 && fullLength) list.add(sb);
        } else if (dotCount == 3) {
            return list;
        } else {
            for (int j = 1; j <= 3; j++) {
                String substring = "";
                if(start < s.length() - j) {
                    substring = s.substring(start, start + j);
                    if(substring.length() >= 2 && substring.charAt(0) != '0'){
                        if (dotCount < 3 && Integer.parseInt(substring) <= 255)
                            generate(s, start + j, sb.concat(substring).concat("."), list, dotCount + 1);
                    }else if (substring.length() == 1) {
                        if (dotCount < 3 && Integer.parseInt(substring) <= 255)
                            generate(s, start + j, sb.concat(substring).concat("."), list, dotCount + 1);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101223101123"));
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("101023"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("101"));
    }
}

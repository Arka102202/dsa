import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RepeatedDNASequences {
    public static List<String> findRepeatedDnaSequences(String s) {
        long hashCode = 0;
        HashMap<Long, String> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        for(int i=0; i<10 && i<s.length(); i++){
            hashCode <<= 5;
            hashCode += s.charAt(i) - 64;
        }
        if(s.length() >= 10) map.put(hashCode, s.substring(0,10));
        int j=1;
        long a = (long)Math.pow(2,50)-1;

        for(int i=10; i<s.length(); i++){
            hashCode <<= 5;
            hashCode += s.charAt(i) - 64;
            hashCode &= a;
            String str = s.substring(j++,i+1);
            if(map.containsKey(hashCode) && !list.contains(str)) list.add(str);
            else map.put(hashCode, str);
        }
        return list;
    }
    public static void main(String[] args) {

        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));

    }


}

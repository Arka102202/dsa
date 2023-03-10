
import java.util.TreeSet;

public class LongestPalindrome {


    public static void main(String[] args) {
        System.out.println(longestPalindrome("bb"));
    }

    static TreeSet<NewString> stringSet = new TreeSet<>(NewString::compareTo);
    static boolean equal = true;
    public static  String longestPalindrome(String s) {

            int start = 0;
            int end = s.length()-1;

            while(start <= end){
                if(s.charAt(start) != s.charAt(end)){
                    equal = false;
                    break;
                }
                start++;
                end--;
            }
            if(equal){
                return s;
            }


            stringSet.clear();
            for(int j=0; j<s.length(); j++){
                for (int i=s.length()-1; i>=2; i--){
                    if(i >= (j+2)){
                        equal = true;
                        NewString a = new NewString();
                        start = j;
                        end = i-1;

                        while(start <= end){
                            if(s.charAt(start) != s.charAt(end)){
                                equal = false;
                                break;
                            }
                            start++;
                            end--;
                        }
                        if(equal){
                            a.ss = s.substring(j,i);
                            stringSet.add(a);
                        }
                    }
                }
            }
            if(stringSet.size() >=1){
                return stringSet.last().ss;
            }

            return s.substring(0,1);


    }
}


class NewString implements Comparable<NewString> {
    public String ss;
    @Override
    public int compareTo(NewString o) {
        return Integer.compare(ss.length(), o.ss.length());
    }
}




























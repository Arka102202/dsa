import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {


    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();
        HashMap<Integer, List<String>> map = new HashMap<>();
        for(String s:strs){

            int hash = hashCode(s);
            if (!map.containsKey(hash)) {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(hash,list);
                lists.add(list);
            }
            else {
                List<String> l = map.get(hash);
                l.add(s);
            }
        }
        return lists;
    }
    static int hashCode(String s){
        int[] arr = new int[27];
        for(char ch:s.toCharArray()){
            int val = ch-'a'+1;
            if(arr[val] == 0) arr[val] = 1;
            else arr[val] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<27; i++)
            if (arr[i] != 0) sb.append(i).append(arr[i]);
        return new String(sb).hashCode();
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat","eat","tea","tan","ate","nat","bat"}));
    }
}

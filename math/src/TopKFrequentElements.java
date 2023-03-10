import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i:nums){
            if(!map.containsKey(i)) map.put(i,1);
            else map.put(i,map.get(i)+1);
        }

        LinkedList<Integer>[] lists = new LinkedList[300];

        for(int i:map.keySet()){
            int val = map.get(i);
            if(lists[val] == null) lists[val] = new LinkedList<>();
            lists[val].add(i);
        }

        int[] top = new int[k];
        int l = 0;
        for(int i=299; i>=0; i--){
            if(lists[i] != null) {
                LinkedList<Integer> list = lists[i];
                for(int j:list) {
                    if(l < k) top[l++] = j;
                }
            }
        }
        return top;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));


    }


}

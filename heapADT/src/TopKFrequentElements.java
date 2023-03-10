import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class TopKFrequentElements {



    public static int[] topKFrequent(int[] nums, int k) {

        if(nums.length == 1) return nums;

        MinPriorityQueue queue = new MinPriorityQueue();

        queue.addAll(nums);

        TreeMap<Integer, Integer> frequencyMap = new TreeMap<>(Comparator.reverseOrder());

        int prev = queue.removeMin(), c = 1;

        for(int i=0; i< nums.length-1; i++){

            int present = queue.removeMin();

            if(present != prev) {
                frequencyMap.put(c, prev);
                prev = present;
                c = 1;
            }
            else c++;
        }

        frequencyMap.put(c, prev);

        int[] r = new int[k];

        for(int i=0; i<k; i++) r[i] = frequencyMap.remove(frequencyMap.firstKey());

        return r;


    }


    public static void main(String[] args) {

        System.out.println(Arrays.toString(topKFrequent(new int[]{1,2}, 1)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{-1,-1}, 1)));


    }









}

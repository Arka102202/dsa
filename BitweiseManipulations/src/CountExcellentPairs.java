import java.util.Arrays;
import java.util.HashSet;

public class CountExcellentPairs {

    public static long countExcellentPairs(int[] nums) {

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        long count = 0;
        for(int i:nums){
            if(set1.size() == 0 || !set1.contains(i)){
                for(int j:nums){
                    if(set2.size() == 0 || !set2.contains(j)){
                        int setBit = Integer.bitCount(i|j) + Integer.bitCount(i&j);
                        if(setBit >= 3) count++;
                    }
                    set2.add(j);
                }
                set2.clear();
            }
            set1.add(i);
        }

        return count;

    }

    public static long countExcellentPairs(int[] nums, int k) {
        long res = 0;
        long[] cnt = new long[30];
        for (int n : Arrays.stream(nums).distinct().toArray()) {
            ++cnt[Integer.bitCount(n)];
        }
        for (int i = 1; i < 30; ++i)
            for (int j = Math.max(i, k - i); j < 30; ++j)
                res += cnt[i] * cnt[j] * (i == j ? 1 : 2);
        return res;
    }


    public static void main(String[] args) {
        System.out.println(countExcellentPairs(new int[]{1,2,3,1},
                3));
    }

//&& (set3.size() == 0 || !set3.contains(Arrays.toString(arr).hashCode()))


}

//

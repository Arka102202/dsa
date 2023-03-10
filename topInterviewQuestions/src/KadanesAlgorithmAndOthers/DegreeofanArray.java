package KadanesAlgorithmAndOthers;

import java.util.HashMap;
public class DegreeofanArray {
    public static int findShortestSubArray(int[] nums) {
        HashMap<Integer, PosDegree> freqMap = new HashMap<>();
        int low = -1, max = -1, lastDegree = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!freqMap.containsKey(nums[i])) {
                freqMap.put(nums[i], new PosDegree(i, 1));
                if (lastDegree <= 1) {
                    low = max = i;
                    lastDegree = 1;
                }
            } else {
                PosDegree pd = freqMap.get(nums[i]);
                pd.d += 1;
                freqMap.put(nums[i], pd);
                if (pd.d > lastDegree) {
                    max = i;
                    if (nums[low] != nums[i]) low = pd.pos;
                    lastDegree = pd.d;
                } else if (pd.d == lastDegree && i - pd.pos < max - low) {
                    max = i;
                    low = pd.pos;
                }
            }
        }
        return max - low == 0 ? 1 : (max - low + 1);
    }

    static class PosDegree {

        int pos, d;

        public PosDegree(int pos, int d) {
            this.pos = pos;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        System.out.println(findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        System.out.println(findShortestSubArray(new int[]{2, 1, 1, 2, 1, 3, 3, 3, 1, 3, 1, 3, 2}));
        System.out.println(findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
    }

}

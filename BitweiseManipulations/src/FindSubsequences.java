import java.util.*;

public class FindSubsequences {
    static List<List<Integer>> subSet = new ArrayList<>();
    static Set<Integer> set = new HashSet<>();
    public static List<List<Integer>> findSubsequences(int[] nums) {
        subSet.clear();
        set.clear();
        int count = 0;
        int runningLowest;
        for (int i = 0; i < nums.length; i++) {
            int lowestValue = nums[i];
            if (nums[i] >= lowestValue) {
                runningLowest = nums[i];
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                createSubSets(nums, i + 1, list, count + 1, runningLowest);
                list.remove(Integer.valueOf(nums[i]));
            }
        }
        if (subSet.size() > 0) return subSet;
        else return new ArrayList<>();
    }
    public static void createSubSets(int[] nums, int previousIndex, List<Integer> list, int count, int runningLowest) {
        int oldRunningLowest = runningLowest;
        for (int i = previousIndex; i < nums.length; i++) {
            if (nums[i] >= runningLowest) {
                list.add(nums[i]);
                list.sort(Integer::compareTo);
                int code = list.toString().hashCode();
                if (set.add(code)) subSet.add(new ArrayList<>(list));
                runningLowest = nums[i];
            }
            createSubSets(nums, i + 1, list, count + 1, runningLowest);
            if (nums[i] >= runningLowest) list.remove(Integer.valueOf(nums[i]));
            runningLowest = oldRunningLowest;
        }
    }
    public static void main(String[] args) {
        System.out.println(findSubsequences(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1}));
    }

}
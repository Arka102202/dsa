import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CountArrangement {
    static List<List<Integer>> subSet = new ArrayList<>();
    static HashSet<Integer> set = new HashSet<>();
    public static int countArrangement(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        subSet.clear();
        int target = nums.length - 1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            list.remove(Integer.valueOf(nums[i]));
            createSubSets(nums, i, list, count + 1, target);
            list.add(nums[i]);
        }
        return (subSet.size() == 0) ? 1:subSet.size();
    }
    public static void createSubSets(int[] nums, int previousIndex, List<Integer> list, int count, int target) {

        if (count <= target) {

            for (int i = 0; i < nums.length; i++) {
                if(i != previousIndex){
                    list.add(nums[i]);
                    list.remove(Integer.valueOf(nums[i]));
                    if(list.size() == nums.length) subSet.add(new ArrayList<>(list));
                    createSubSets(nums, i, list, count + 1, target);
                    list.add(nums[i]);
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(countArrangement(3));
        System.out.println(subSet);
    }

}

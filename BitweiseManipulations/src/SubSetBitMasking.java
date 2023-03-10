import java.util.ArrayList;
import java.util.List;

public class SubSetBitMasking {
    static List<List<Integer>> subSet = new ArrayList<>();


    public static List<List<Integer>> subsets(int[] nums) {
        subSet.clear();
        subSet.add(List.of());
        int target = nums.length - 1;
        int count = 0;
        List<Integer> separateList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            separateList.add(nums[i]);
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            subSet.add(new ArrayList<>(list));
            createSubSets(nums, i + 1, list, count + 1, target);
            list.remove(Integer.valueOf(nums[i]));

        }
        if(nums.length > 1) subSet.add(separateList);
        return subSet;
    }


    public static void createSubSets(int[] nums, int previousIndex, List<Integer> list, int count, int target) {

        if (count < target) {

            for (int i = previousIndex; i < nums.length; i++) {
                list.add(nums[i]);
                subSet.add(new ArrayList<>(list));
                createSubSets(nums, i + 1, list, count + 1, target);
                list.remove(Integer.valueOf(nums[i]));
            }
        }

    }

    public static void main(String[] args) {

        int[] arr = new int[20];
        for(int i=1; i<=20; i++){
            arr[i-1] = i;
        }
        System.out.println(subsets(new int[]{9,1,2,5,8,3,3,4,6,5}));
    }

}

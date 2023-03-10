import java.util.ArrayList;
import java.util.List;

public class MakeSquare {
    static int sideLength;
    static boolean[] isUsed;
    public static boolean makeSquare(int[] matchsticks) {
        int[] nums = new int[matchsticks.length];
        isUsed = new boolean[matchsticks.length];
        for (int i = 0; i < matchsticks.length; i++) {
            nums[i] = i;
            sideLength += matchsticks[i];
            isUsed[i] = false;
        }
        if((sideLength & 0b11) == 0){
            sideLength >>= 2;
            int sum = 0;
            int count = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                sum += matchsticks[nums[i]];
                int len = list.size();
                if (sum == sideLength) {
                    list.add(count + 1);
                    isUsed[i] = true;
                }
                else if (sum < sideLength) createSubSets(nums, matchsticks, i + 1, list, count + 1, sum);
                if(list.size() > len) isUsed[i] = true;
                sum -= matchsticks[nums[i]];
            }
            sum = 0;
            boolean used = true;
            for(int i:list) sum += i;
            for(boolean b:isUsed) used &= b;
            return ((sum >= matchsticks.length) & used);
        }
        else return false;
    }
    public static void createSubSets(int[] nums, int[] matchsticks, int previousIndex, List<Integer> list, int count, int sum) {
        for (int i = previousIndex; i < nums.length; i++) {
            sum += matchsticks[nums[i]];
            if (sum == sideLength) {
                list.add(count + 1);
                isUsed[i] = true;
            }
            else if (sum < sideLength) createSubSets(nums, matchsticks, i + 1, list, count + 1, sum);
            sum -= matchsticks[nums[i]];
        }
    }


    public static void main(String[] args) {
        System.out.println(makeSquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));
    }

}

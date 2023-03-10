import java.util.*;

public class CanPartitionKSubsets {
    static long targetSum;
    static HashSet<Integer> previous = new HashSet<>();
    static LinkedList<Integer> usedList = new LinkedList<>();
    static int x, y, z, len ;
    static LinkedList<LinkedList<Integer>> subSets = new LinkedList<>();
    public static boolean canPartitionKSubsets(int[] nums,int k) {
        subSets.clear();
        previous.clear();
        x =0;
        y = 0;
        len = nums.length;
        long sum = 0;
        for (int num : nums) sum += num;
        targetSum = sum / k;
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        LinkedList<Integer> list1 = new LinkedList<>();
        for (int i : nums) list1.add(i);
        return isPerfectPartition(list1, k-1)+1 == k;
    }
    static int isPerfectPartition(LinkedList<Integer> nums, int k) {
        int i;
        for(i=0; i<k; i++){
            previous.clear();
            boolean r = createPartition(0,nums);
            if(r) {
                subSets.add(new LinkedList<>(usedList));
                usedList.clear();
                z = 0;
            }
            else if(i>0 || z == 0) {
                if(subSets.size() > 0) {
                    nums.addAll(subSets.removeFirst());
                    y++;
                }
                else {
                    int temp = nums.removeFirst();
                    nums.addLast(temp);
                    x++;
                }
                i = subSets.size()-1;
                z++;
            }
            else if(i == 0) return 0;
            if(!(len < 10) && x == len/4) return 0;
            if(y == 20) return 0;
        }
        return i;
    }
    static boolean createPartition(int sum, LinkedList<Integer> nums) {
        if (previous.contains(sum) || sum >= targetSum) return sum == targetSum;
        previous.add(sum);
        for (int i = 0; i < nums.size(); i++) {
            int current = nums.removeFirst();
            usedList.add(current);
            if (createPartition(sum + current, nums)) {
                return true;
            }
            nums.add(i,current);
            usedList.remove(Integer.valueOf(current));
        }
        return false;
    }
    public static boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
        if(k==1)return true;
        if(cur_sum == target && cur_num>0)return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
                visited[i] = 0;
            }
        }
        return false;
    }
    static boolean isPerfectPartition(int sum, int[] nums) {
        if (previous.contains(sum) || sum >= targetSum) return sum == targetSum;
        previous.add(sum);
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0) continue;
            int current = nums[i];
            nums[i]=0;
            if (isPerfectPartition(sum + current, nums)) {
                return true;
            }
            nums[i]=current;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{1,5,11,5},2));
        System.out.println(canPartitionKSubsets(new int[]{3,5,2,1,1,1,2,3,6},2));
        System.out.println(canPartitionKSubsets(new int[]{10,9,9,9,9,8,7,3,1,1},2));
        System.out.println(canPartitionKSubsets(new int[]{7,7,2,2,2,2,9,4,7},2));
//        System.out.println(canPartitionKSubsets(new int[]{2,2,2,2,3,4,5},4));
//        System.out.println(canPartitionKSubsets(new int[]{1,2,1,1,1,2,2,2},4));
        System.out.println(canPartitionKSubsets(new int[]{10, 9, 9, 9, 9, 8, 7, 3, 1, 1},2));
        System.out.println(canPartitionKSubsets(new int[]{1,1,1,1,2,2,2,2},2));
        System.out.println(canPartitionKSubsets(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97}
                ,2));
        System.out.println(canPartitionKSubsets(new int[]{20, 1, 16, 2, 17, 16, 8, 15, 7},2));

        System.out.println(canPartitionKSubsets(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 12, 12, 12, 12, 12, 16, 16, 16, 16, 16, 16, 16, 16, 20, 20, 20, 20, 20, 20, 20, 20, 24, 24, 24, 24, 24, 24, 24, 24, 28, 28, 28, 28, 28, 28, 28, 28, 32, 32, 32, 32, 32, 32, 32, 32, 36, 36, 36, 36, 36, 36, 36, 36, 40, 40, 40, 40, 40, 40, 40, 40, 44, 44, 44, 44, 44, 44, 44, 44, 48, 48, 48, 48, 48, 48, 48, 48, 52, 52, 52, 52, 52, 52, 52, 52, 56, 56, 56, 56, 56, 56, 56, 56, 60, 60, 60, 60, 60, 60, 60, 60, 64, 64, 64, 64, 64, 64, 64, 64, 68, 68, 68, 68, 68, 68, 68, 68, 72, 72, 72, 72, 72, 72, 72, 72, 76, 76, 76, 76, 76, 76, 76, 76, 80, 80, 80, 80, 80, 80, 80, 80, 84, 84, 84, 84, 84, 84, 84, 84, 88, 88, 88, 88, 88, 88, 88, 88, 92, 92, 92, 92, 92, 92, 92, 92, 96, 96, 96, 96, 96, 96, 96, 96, 97, 99}
                ,2));
        System.out.println(canPartitionKSubsets(new int[]{1,1,2,2},2));
//        System.out.println(canPartitionKSubsets(new int[]{4, 10, 9, 10, 10, 4, 9, 7, 5, 8, 8, 4, 4, 5}, 1));
//        System.out.println(canPartitionKSubsets(new int[]{19, 11, 1, 3, 3, 1, 9, 9, 3, 1}, 3));
//        System.out.println(canPartitionKSubsets(new int[]{815, 625, 3889, 4471, 60, 494, 944, 1118, 4623, 497, 771, 679, 1240, 202, 601, 883}, 3));
    }
}
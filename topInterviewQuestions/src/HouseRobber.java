public class HouseRobber {

    public static int rob(int[] nums) {
        int[][] memo = new int[nums.length][2];
        memo[nums.length-1][0] = nums[nums.length-1];
        memo[nums.length-1][1] = 0;

        for (int i=nums.length-2; i>=0; i--){
            memo[i][0] = memo[i+1][1]+nums[i];
            memo[i][1] = Math.max(memo[i+1][0], memo[i+1][1]);
        }
        return Math.max(memo[0][0], memo[0][1]);
    }

    public static void main(String[] args) {

        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(rob(new int[]{1,2,3,1}));



    }




}

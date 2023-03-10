public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        int minSize = Integer.MAX_VALUE, sum = 0, min = 0, max;
        for (int i=0; i<nums.length; i++){
            sum += nums[i];
            if(sum >= target) {
                max = i;
                minSize = Math.min(minSize,(max - min +1));
                while (min < nums.length-1 && sum - nums[min] >= target) {
                    min++;
                    minSize = Math.min(minSize, (max - min + 1));
                    sum -= nums[min-1];
                }
            }
        }
        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(1, new int[]{3,1,4,2,4}));
    }
}

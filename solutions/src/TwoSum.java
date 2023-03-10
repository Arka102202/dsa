import java.util.Arrays;

public class TwoSum {


    public static void main(String[] args) {

        int[] nums = new int[]{2,5,5,15};
        int target =10;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] num1 = new int[nums.length];
        System.arraycopy(nums, 0, num1, 0, nums.length);        // O(n)
        Arrays.sort(nums);                                                    // O(nlog(n))
        int[] positions = new int[2];
        int p1=0, p2=0;
        for(int i=0; i<nums.length; i++){                                     // O(n)
            int rest = target - nums[i];
            if(rest >= 0){
                int position = Arrays.binarySearch(nums,i+1,nums.length,rest);
                if(position >= 0){
                    positions = findIndex(num1,nums[i],rest);
                }
            }else if(target <0 && rest < 0){
                int position = Arrays.binarySearch(nums,i+1,nums.length,rest);
                if(position >= 0){
                    positions = findIndex(num1,nums[i],rest);
                }
            }
        }
        Arrays.sort(positions);
        return positions;
    }

    public static int[] findIndex(int[] nums,int key1,int key2){
        int start=0, end=0, middle1=0, middle2 =0;
        int p1=0, p2=0;
        start = 0; end = nums.length-1;
        if(nums.length%2 ==0){
            middle1 = (nums.length/2-1);
            middle2 = middle1+1;
        }else{
            middle2 = middle1 = ((nums.length+1)/2)-1;
        }
        while(start <= middle1 && middle2<=end){
            if(nums[start] == key1){
                p1 = start;
            }else if(nums[end] == key1){
                p1 = end;
            }else if(nums[middle1] == key1){
                p1 = middle1;
            }else if(nums[middle2] == key1){
                p1 = middle2;
            }
            if(nums[end] == key2 && p1 != end){
                p2 = end;
            }else if(nums[middle1] == key2 && p1 != middle1){
                p2 = middle1;
            }else if(nums[middle2] == key2 && p1 != middle2){
                p2 = middle2;
            }else if(nums[start] == key2 && p1 != start){
                p2 = start;
            }
            start++;
            middle1--;
            middle2++;
            end--;
        }

        return new int[]{p1,p2};
    }





}

import java.util.Arrays;

public class SearchInRotatedSortedArray {
    static int search(int[] nums, int target) {
        int pivotPoint = nums.length-1;
        if(nums.length >= 3){
            for(int i=0; i<nums.length; i+=3){
                int pos2 = i+1, pos3 = i+2;
                if(pos2 < nums.length && nums[i] > nums[pos2]) pivotPoint = i;
                else if (pos2 < nums.length && pos3 < nums.length && nums[pos2] > nums[pos3]) pivotPoint = pos2;
                else if (pos3 < nums.length-1 && nums[pos3] > nums[pos3+1]) pivotPoint = pos3;
            }
            int pos1 = Arrays.binarySearch(nums,0,pivotPoint+1,target);
            int pos2 = Arrays.binarySearch(nums,pivotPoint+1,nums.length, target);
            if(pos1 < 0 && pos2 < 0) return -1;
            else return pos1 >= 0? pos1:pos2;
        }
        else {
            if (nums.length == 1) return nums[0] == target ? 0:-1;
            else {
                if(nums[0] == target) return 0;
                else return nums[1] == target ? 1:-1;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(search(new int[]{1,3,5},1));
        System.out.println(search(new int[]{0,2,3,4,5,6,7},0));
        System.out.println(search(new int[]{4,5,6,7,0,2,3},0));
        System.out.println(search(new int[]{6,7,0,2,3,4,5},0));
    }






}

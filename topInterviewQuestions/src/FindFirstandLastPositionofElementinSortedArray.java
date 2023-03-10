import java.util.Arrays;

public class FindFirstandLastPositionofElementinSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        int[] pArr = {-1, -1};
        if (nums.length == 0) return pArr;
        int a = binarySearch0(nums, target, true);
        if (a >= 0){
            if (nums[a] == target) pArr[0] = a;
            else pArr[0] = a-1;
        }
         a = binarySearch0(nums, target, false);
        if (a >= 0){
            if (nums[a] == target) pArr[1] = a;
            else pArr[1] = a-1;
        }
        return pArr;
    }

    private static int binarySearch0(int[] a, int key, boolean left) {
        int low = 0;
        int high = a.length-1;

        if (high == low) return low;

        while (low < high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (left){
                if (midVal < key)
                    low = mid;
                else high = mid;
            }else {
                if (midVal <= key)
                    low = mid;
                else high = mid;
            }

            if (high - low == 1 && left){
                if (low == 0 && a[low] == key) return 0;
                else if (a[high] == key) return high;
                else break;
            }else if (high - low == 1){
                if (a[high] == key) return high;
                else if (low == 0 && a[low] == key) return 0;
                else break;
            }

        }
        return a[low] == key?low:-1;  // key not found.
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(searchRange(new int[]{5,6}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,6,6}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{5}, 5)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 6)));
        System.out.println(Arrays.toString(searchRange(new int[]{1,1,1,1,1,1,2,3,4,4,5,5,5,6,7,8,8,8,8}, 8)));
    }
}

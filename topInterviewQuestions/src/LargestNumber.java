import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public static String largestNumber(int[] nums) {
        String r;
        String[] arr = new String[nums.length];
        for(int i=0; i<nums.length; i++) arr[i] = Integer.toString(nums[i]);
        Arrays.sort(arr,new LargerNumberComparator());
        r = Arrays.stream(arr).reduce("", String::concat);
        return Double.parseDouble(r) == 0.0 ? "0" : r;

    }

    private static class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public static void main(String[] args) {

        System.out.println(largestNumber(new int[]{3,30,34,5,9}));



    }










}

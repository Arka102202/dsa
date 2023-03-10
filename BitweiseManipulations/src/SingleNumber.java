import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SingleNumber {


    static HashMap<Integer, Integer> map = new HashMap<>();

    public static int singleNumber1(int[] nums) {

        Integer num = null;

        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        for (int i : map.keySet()) {

            if (map.get(i) == 1) {
                num = i;
                break;
            }
        }

        return num;

    }

    public static int singleNumber2(int[] A) {
        int ones = 0, twos = 0, common_bit_mask = 0;
        for (int j : A) {
            twos |= (j&ones);
            ones ^= j;
            common_bit_mask = ~(ones&twos);
            ones &= common_bit_mask;
            twos &= common_bit_mask;
        }
        return ones;
    }

    public static int singleNumber3(int[] nums) { // did
        int x1 = 0, x2 = 0, mask;

        for (int i : nums) {

            int j = x1 & i;

            x2 ^= j;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }

        return x1;  // Since p = 1, in binary form p = '01', then p1 = 1, so we should return x1.
        // If p = 2, in binary form p = '10', then p2 = 1, and we should return x2.
        // Or alternatively we can simply return (x1 | x2).
    }


    public static int findDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int j;
            if (nums[i] < 0) j = -nums[i];
            else j = nums[i];

            if (nums[j] < 0) {
                if (nums[i] >= 0) return nums[i];
                else return -nums[i];
            }

            nums[j] = -nums[j];

        }
        return 0;

    }

    public static int floydsTortoiseHare(int[] nums) {

        int hare = nums[0];
        int tortoise = nums[0];

        do {
            hare = nums[nums[hare]];
            tortoise = nums[tortoise];
        } while (hare != tortoise);

        tortoise = nums[0];
        while (hare != tortoise) {
            hare = nums[hare];
            tortoise = nums[tortoise];
        }

        return hare;
    }

    public static int[] singleNumber(int[] nums) {
        int XOR = 0, num1 = 0, num2 = 0;
        int rightMostSetBitPosition;
        for (int j : nums) {
            XOR ^= j;
        }
        rightMostSetBitPosition = (int) (Math.log((XOR & -XOR)) / Math.log(2.0));
        for (int j : nums) {
            if ((j & (1 << (rightMostSetBitPosition))) == (1 << (rightMostSetBitPosition))) {
                num1 ^= j;
            } else num2 ^= j;
        }
        return new int[]{num1, num2};
    }

    public static void main(String[] args) {

        System.out.println(floydsTortoiseHare(new int[]{1,3,4,2,2}));
        int a = 23;
        System.out.println(Integer.toBinaryString(a));
        int s = (a&(-a));
        int r = (a&(-a))+a;
        int y = ((a&(-a))+a)|(((a^((a&(-a))+a))>>>2)/(a&(-a)));
        a = y;
        y = ((a&(-a))+a)|(((a^((a&(-a))+a))>>>2)/(a&(-a)));

        for(int i=0; i<10; i++){
            a = y;
            y = ((a&(-a))+a)|(((a^((a&(-a))+a))>>>2)/(a&(-a)));
            System.out.println(Integer.toBinaryString(y));
        }

    }
}

import java.util.Arrays;

public class GCDSort {

    public static boolean gcdSort(int[] nums) {


        for (int i=0; i<nums.length; i++){

            for(int j=0; j<nums.length; j++){


                if(j< nums.length-1 && nums[j] > nums[j+1]){

                    int gcd = findGCD(nums[j],nums[j+1]);
                    if(gcd >1){
                        nums[j] ^= nums[j+1];
                        nums[j+1] ^= nums[j];
                        nums[j] ^= nums[j+1];
                    }

                }
            }



        }

        System.out.println(Arrays.toString(nums));

        return true;
    }




    static int findGCD(int num1, int num2) {
        int r = -1;
        while (true) {
            r = num1 % num2;
            if (r == 0) break;
            else {
                num1 = num2;
                num2 = r;
            }
        }
        return num2;
    }


    public static void main(String[] args) {
        System.out.println(gcdSort(new int[]{10,5,9,3,15}));

        int[] arr = {1,1,1,1,1,1,1,1,1,1,1,2};
        System.out.println(Arrays.binarySearch(arr,1));

    }

}

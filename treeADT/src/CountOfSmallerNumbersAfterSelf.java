import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {

    static LinkedList<Integer> counts;
    static int[] arr;
    static int size = 0;
    static List<Integer> countSmaller(int[] nums) {
        counts = new LinkedList<>();
        int len = nums.length;
        size = 0;
        arr = new int[100000];
        for(int i=len-1; i>=0; i--)
            add(nums[i]);
        return counts;
    }

    static boolean add(int num){
        size++;
        maxHeapifyUp(size-1,num);
        return true;
    }
    static void maxHeapifyUp(int p, int value){
        while(p>0){
            int parent = (p - 1) >>> 1;
            int parentValue = arr[parent];

            if(parentValue >= value)
                break;

            arr[p] = parentValue;
            p = parent;
        }
        arr[p] = value;

        if(p > 0 && ((p-1)&1) != 0 && arr[p-1] < arr[p]){
            int temp = arr[p-1];
            arr[p-1] = arr[p];
            arr[p] = temp;
            p--;
        }


        counts.push(size-p-1);
    }

    public static void main(String[] args) {
//        countSmaller(new int[]{21,10,11,3,5,6,10,20,15,1,2,1,3,4,5});
//        display(new int[]{21,10,11,3,5,6,10,20,15,1,2,1,3,4,5});
//        countSmaller(new int[]{21,20,11,6,10,5,4,3,2,1});
//        display(new int[]{21,20,11,6,10,5,4,3,2,1});
        countSmaller(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 2, 2, 2});
        display(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 2, 2, 2});
    }


    static void display(int[] arr) {

        for (int j : arr) {
            System.out.printf("%2d, ", j);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%2d, ", counts.get(i));
        }
        System.out.println();


    }


}

import java.util.Arrays;
import java.util.LinkedList;
public class RadixSort {
    public static int[] radixSort(int[] arr){
        LinkedList<Integer>[] holder = new LinkedList[10];
        int maxNumber = Integer.MIN_VALUE;
        for(int i:arr)
            if(i > maxNumber) maxNumber = i;
        for(int i = 1; maxNumber/i > 0; i *= 10){
            for(int num:arr){
                int digit = (num/i)%10;
                if(holder[digit] == null) holder[digit] = new LinkedList<>();
                holder[digit].add(num);
            }
            int j = 0;
            for(LinkedList<Integer> list:holder){
                if(list != null){
                    for(int num:list) arr[j++] = num;
                    list.clear();
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(radixSort(new int[]{121,1,432,25,26,28,564,100000000,1})));
    }

}

package math;

import java.util.Arrays;

public class QuickSort {
    static int[] arr;
    static int[] stage(int[] nums){
        arr = nums;
        quickSort0(0, arr.length-1);
        return arr;
    }
    static void quickSort0(int low, int high){
        if(low < high){
            int pivotPont = partition0(low, high);
            quickSort0(low, pivotPont-1);
            quickSort0(pivotPont+1, high);
        }
    }
    static int partition0(int low, int high){
        int pivot = arr[low], i = low, j = high;
        while (i < j){
            while (i < arr.length && arr[i] <= pivot) i++;
            while (j > 0 && arr[j] >= pivot) j--;
            if(i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        return j;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(stage(new int[]{121,1,432,25,26,28,564,100000000,1})));
    }
}

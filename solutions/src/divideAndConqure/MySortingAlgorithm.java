package divideAndConqure;

import java.util.Arrays;

public class MySortingAlgorithm {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{1}, 1, 1)));
    }

    public static int[] sort(int[] arr, int startValue, int endValue) {
        int[] values = new int[endValue + 1];
        int[] multiplicity = new int[endValue + 1];
        for (int i = 0; i < endValue + 1; i++) {
            values[i] = startValue - 2;
        }

        for (int i = 0; i < arr.length; i++) {

            if (values[arr[i]] == -1) {
                values[arr[i]] = arr[i];
                multiplicity[arr[i]] = 1;
            } else {
                multiplicity[arr[i]] += 1;
            }
        }

        int[] sortedArr = new int[arr.length];
        int k = 0;

        for (int i = 1; i < endValue + 1; i++) {
            if(values[i] != -1){
                for (int j = 0; j < multiplicity[i]; j++) {
                    sortedArr[k++] = values[i];

                }
            }
        }


        return sortedArr;
    }

}

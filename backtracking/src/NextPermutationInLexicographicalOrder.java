import java.util.Arrays;

public class NextPermutationInLexicographicalOrder {

    public static void nextPermutation(int[] candidates) {
        int len = candidates.length;
        if (len > 1) {
            int i = len - 2;
            while (i >= 0 && candidates[i] >= candidates[i + 1]) {
                i--;
            }
            if (i >= 0) {
                int j = i + 1;
                while (j < len && candidates[i] <= candidates[j]) {
                    j++;
                }
                swap(i, j - 1, candidates);
            }
            int start = i + 1, end = len - 1;
            while (start < end) {
                swap(start, end, candidates);
                start++;
                end--;
            }
        }

        System.out.println(Arrays.toString(candidates));
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        nextPermutation(new int[]{5, 4, 7, 5, 3, 2});
        nextPermutation(new int[]{9, 5, 4, 3, 1});
    }

}

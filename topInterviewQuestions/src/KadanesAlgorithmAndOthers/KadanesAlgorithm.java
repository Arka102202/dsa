package KadanesAlgorithmAndOthers;

public class KadanesAlgorithm {
    public static int maxSubArray(int[] A) {
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            A[i] += Math.max(A[i - 1], 0);
            max = Math.max(max, A[i]);
        }
        return max;
    }

    public static int maxProduct(int[] A) {
        int n = A.length, res = A[0], l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            l =  (l == 0 ? 1 : l) * A[i];
            r =  (r == 0 ? 1 : r) * A[n - 1 - i];
            res = Math.max(res, Math.max(l, r));
        }
        return res;
    }

    public static int maxAbsoluteSum(int[] A) {
        int s = 0, min = 0, max = 0;
        for (int a: A) {
            s += a;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        return max - min;
    }


    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{-3,-5,-3,-2,-6,3,10,-10,-8,-3,0,10,3,-5,8,7,-9,-9,5,-8}));
        System.out.println(maxProduct(new int[]{-1,2,5,3}));
        System.out.println(maxSubArray(new int[]{-3, -1, -1}));
    }


}

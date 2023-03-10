package KadanesAlgorithmAndOthers;

public class LongestTurbulentSubarray {

    public static int maxTurbulenceSize(int[] arr) {
        int p1 = 0, p2 = 0, g = 0, l = 0, len = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                if (g == 0) {
                    g++;
                    l = 0;
                } else {
                    g = 1;
                    if (p2 - p1 > len) len = p2 - p1;
                    p1 = i - 1;
                }
                p2 = i;
            } else if (arr[i - 1] < arr[i]) {
                if (l == 0) {
                    l++;
                    g = 0;
                } else {
                    l = 1;
                    if (p2 - p1 > len) len = p2 - p1;
                    p1 = i - 1;
                }
                p2 = i;
            } else {
                g = l = 0;
                if (p2 - p1 > len) len = p2 - p1;
                p1 = i;
            }
        }
        if (p2 - p1 > len) len = p2 - p1;
        return len + 1;
    }

    public static void main(String[] args) {

        System.out.println(maxTurbulenceSize(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));

        System.out.println(maxTurbulenceSize(new int[]{2, 0, 2, 4, 2, 5, 0, 1, 2, 3}));

        System.out.println(maxTurbulenceSize(new int[]{4}));
        System.out.println(maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        System.out.println(maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
    }
}

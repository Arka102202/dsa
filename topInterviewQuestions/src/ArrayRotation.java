import java.util.Arrays;

public class ArrayRotation {

    static int[] rotate(int[] arr, int distance, boolean left) {
        System.out.println("rotate " + Arrays.toString(arr) + " by " + distance + " to " + (left ? "left" : "right"));
        distance = (distance % arr.length);
        if (distance == 0) return arr;
        if (!left) distance = arr.length - distance;
        rotateLeft(arr, distance);
        return arr;
    }

    static void rotateLeft(int[] arr, int distance) {
        int c = 0, r, temp;
        for (int i = 0; i < arr.length; i++) {
            if (distance > (arr.length / 2)) {
                if (i <= distance && c == (arr.length - distance)) c = 0;
                r = c + distance;
                if (i > distance && r >= arr.length) r = arr.length - 1;
            } else if (distance < (arr.length / 2)){
                r = i + distance;
                if (r >= arr.length) r = arr.length - 1;
            }else {
                r = i+distance;
                if(r == arr.length) break;
            }
            temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
            c++;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotate(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21}, 29, false)));
        System.out.println(Arrays.toString(rotate(new int[]{1, 2, 3, 4, 5, 6}, 5, false)));
        System.out.println(Arrays.toString(rotate(new int[]{-1, -100, 3, 99}, 2, false)));
        System.out.println(Arrays.toString(rotate(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, 38, false)));
        System.out.println(Arrays.toString(rotate(new int[]{1, 2}, 2, false)));
        System.out.println(Arrays.toString(rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3, false)));


    }
}

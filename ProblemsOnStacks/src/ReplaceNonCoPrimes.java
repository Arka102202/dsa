import java.util.LinkedList;
import java.util.List;

public class ReplaceNonCoPrimes {
    public static List<Integer> replaceNonCoPrimes(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int j : nums) {
            int num = j;
            if (list.size() > 0) {
                while (!list.isEmpty()) {
                    int gcd = findGCD(num, list.getLast());
                    if (gcd == 1) break;
                    num *= list.removeLast() / gcd;
                }
                list.add(num);
            } else list.add(num);
        }
        return list;

    }
    static int findGCD(int num1, int num2) {
        int r;
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
        System.out.println(replaceNonCoPrimes(new int[]{11,9,3,9,3,9,3,9,3,3,3,3,3,33,33,3,3,3,9,3,3,9,3,33,3,33,9,33,33,33,9,3,3,9,3,3,9,3,3,33,33,9,3,33,9,3,33,3,3,33,9,3,9,33,3,3,9,9,33,3,3,3485,85,3485,17,85,5,205,5,1025,85,85,205,205,25,5,425,85,5,425,5,1025,5,205,5,425,17,289}));
        System.out.println(replaceNonCoPrimes(new int[]{8303,361,8303,361,437,361,8303,8303,8303,6859,19,19,361,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,70121,1271,31,961,31,7,2009,7,2009,2009,49,7,7,8897,1519,31,1519,217}));
        System.out.println(replaceNonCoPrimes(new int[]{287,41,49,287,899,23,23,20677,5,825}));
        System.out.println(replaceNonCoPrimes(new int[]{517,11,121,517,3,51,3,1887,5}));
        System.out.println(replaceNonCoPrimes(new int[]{31,97561,97561,97561,97561,97561,97561,97561,97561}));
        System.out.println(replaceNonCoPrimes(new int[]{2,2,1,1,3,3,3}));
    }

}


public class PowerOfX {
    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        long count = 2, n1 = n;
        if (n < 0) {
            x = 1 / x;
            n1 = Math.abs(n1);
        }
        double temp = x, t = 1;
        while (n1 > 1){
            while (count <= n1){
                x *= x;
                count *= 2;
            }
            t *= x; x = temp;
            n1 -= (count/2);
            count = 2;
        }
        if (n1 == 1) t *= temp;
        return t;
    }


    public static void main(String[] args) {

        System.out.println(myPow(2, Integer.MIN_VALUE));
        System.out.println(myPow(8.95371,-1));
        System.out.println(myPow(2.0,-58));
    }


}

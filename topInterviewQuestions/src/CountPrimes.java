import java.util.LinkedList;
import java.util.List;

public class CountPrimes {
    public static int countPrimes0(int n) {
        int count = 2;
        boolean prime = true;
        if (n < 2) return 0;
        LinkedList<Integer> list = new LinkedList<>(List.of(2,3));
        for(int i=3; i<n; i++){
            if ((i-1)%6 == 0 || (i+1)%6 == 0){
                for (int p : list) {
                    if (i % p == 0 && p <= Math.sqrt(i)) {
                        prime = false;
                        break;
                    }
                }
            }else prime = false;
            if (prime){
                if (i <= 1000) list.add(i);
                count++;
            }
            prime = true;
        }
        return count;

    }

    public static int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println(countPrimes(
                500000000));



    }




}

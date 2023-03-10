import java.util.HashMap;

public class IntegerReplacementDP {
    static HashMap<Long, Long> memo = new HashMap<>();
    public static int integerReplacement(int n) {
        return (int)calculateMinNumber(n);
    }
    public static long calculateMinNumber(long remaining){
        long count= 0;
        if(remaining == 1) return 0;
        if(remaining < 1) return Integer.MAX_VALUE;
        if(memo.containsKey(remaining)) return memo.get(remaining);
        if((remaining&1) == 0) {
            count += calculateMinNumber(remaining >>1);
            count+=1;
        }
        else {
            count+=1;
            long tempCount1 = calculateMinNumber(remaining-1);
            long tempCount2 = calculateMinNumber(remaining+1);

            if(tempCount1 != Integer.MAX_VALUE && tempCount2 <= tempCount1) count += tempCount2;
            else if(tempCount2 != Integer.MAX_VALUE && tempCount1 < tempCount2) count += tempCount1;
        }
        memo.put(remaining, count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(integerReplacement(2147483647));
    }



}

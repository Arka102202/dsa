import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CanIWin {

    static HashMap<Integer, Boolean> middleScore = new HashMap<>();
    public static boolean canIWin(int maxInteger, int desiredTotal) {
        if(desiredTotal == 0) return true;
        if(!(((maxInteger * (maxInteger+1))/2) >= desiredTotal)) return false;
        LinkedList<Integer> options = new LinkedList<>();
        for(int i=1; i<=maxInteger; i++){
            options.add(i);
        }
        int count = 1;

        return tryCombinations(options, count, desiredTotal);

    }
    public static boolean tryCombinations(LinkedList<Integer> options, int count, int remaining){

       if(remaining <= 0 && count%2 == 0) return true;
       if(remaining <= 0) return false;
       int position = binarySearch(options, remaining);
       if(position >= 0 && count%2 != 0) return true;
       if(position >= 0) return false;

       if(middleScore.containsKey(remaining)){
           return middleScore.get(remaining);
       }
       boolean result = true;
       for(int i:options){
           LinkedList<Integer> remainingOptions =new LinkedList<>(options);
           remainingOptions.remove(Integer.valueOf(i));
           result &= tryCombinations(remainingOptions, count+1, remaining- i);
           middleScore.put(remaining, result);
       }
       return result;
    }
    private static int binarySearch(List<Integer> a, long key) {
        int low = 0;
        int high = a.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a.get(mid);

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String[] args) {
        System.out.println(canIWin(4,6));
    }

}

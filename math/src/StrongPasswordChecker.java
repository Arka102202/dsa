import java.util.Comparator;
import java.util.PriorityQueue;

public class StrongPasswordChecker {
    public static int strongPasswordChecker(String password) {
        int[] ok = new int[]{1, 1, 1};
        char[] A = password.toCharArray();
        for (char ch : A){ // check the 3 conditions first.
            ok[0] &= Character.isLowerCase(ch)? 0 : 1;
            ok[1] &= Character.isUpperCase(ch)? 0 : 1;
            ok[2] &= Character.isDigit(ch)? 0 : 1;
        }
        int ans = 0, len = password.length(), cond = ok[0]+ok[1]+ok[2]; // cond -> how many cond not met yet.
        var minheap = new PriorityQueue<Integer>(Comparator.comparingInt(o -> o%3)); // sort by o%3
        for (int i = 0, cnt = 1; i < len; i++, cnt++){ // put consecutive chars with count >= 3 into the minheap
            if (i == len-1 || A[i] != A[i+1]){
                if (cnt >= 3){
                    minheap.offer(cnt);
                }
                cnt = 0;
            }
        }
        while(len > 20 && !minheap.isEmpty()){ // if len > 20, we try to delete from the chars groups.
            int cur = minheap.poll();
            if (--cur >= 3){
                minheap.offer(cur);
            }
            --len;
            ++ans;
        }
        while(!minheap.isEmpty()){ // if there are still 3 or more chars groups, we need to replace or insert
            int cur = minheap.poll();
            cond -= cur/3;
            ans += cur/3;
            if (len < 6){ // insert only for len < 6; otherwise, replace.
                len++;    // I do ++ here as cur can only be 3,4,5.
            }
        }
        while(len > 20){ // if len is still > 20, we need to delete.
            ans++;
            len--;
        }
        while(len < 6){ // if len < 6, we need to insert and per insertion will satisify 1 cond
            cond--;
            ans++;
            len++;
        }
        return ans + (Math.max(cond, 0)); // remember to take the max of (cond, 0).
    }

    public static void main(String[] args) {
//        System.out.println(strongPasswordChecker("1337C0d3"));
//
//        System.out.println(strongPasswordChecker("aaaBBB7"));

        System.out.println(strongPasswordChecker("FFFFFFFFFFFFFFF11111111111111111111AAA"));

        System.out.println(strongPasswordChecker("aaaabbbbccccddeeddeeddeedd"));

        System.out.println(strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));

//        System.out.println(strongPasswordChecker("ABABABABABABABABABAB1"));
//
//        System.out.println(strongPasswordChecker("1111111111"));
//
//        System.out.println(strongPasswordChecker("aaAA11"));
//
//        System.out.println(strongPasswordChecker("aaa111"));
//
//        System.out.println(strongPasswordChecker("....."));


    }

}

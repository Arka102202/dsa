package divideAndConqure;
import java.util.*;
public class MinCoin {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2,3}, 7));
    }
    public static int coinChange(int[] coins, int amount) {
        int[] amountCoinCount = new int[amount+1];
        Arrays.fill(amountCoinCount,-1);
        Arrays.sort(coins);
        coinChange3(coins, amount, amountCoinCount);
        if (amount == 0) {
            return 0;
        } else if(amountCoinCount[amount] == Integer.MAX_VALUE) {
            return -1;
        }else{
            return amountCoinCount[amount];
        }
    }
    public static int coinChange3(int[] coins, int amount, int[] amountCoinCount)   {
        int rest;
        int cc = Integer.MAX_VALUE;
        if (amount == 0) {
            return 0;
        }
        if (amountCoinCount[amount] != -1) {
            return amountCoinCount[amount];
        }
        if (amount < coins[0]) {
            return cc;
        }
        for (int i = 0; i < coins.length; i++) {
            if (amount >= coins[i]) {
                rest = coinChange3(coins, amount - coins[i],amountCoinCount);
                if(rest != Integer.MAX_VALUE && rest +1 < cc){
                    cc = rest+1;
                }
            }
        }
        return amountCoinCount[amount] = cc;
    }


    public static int coinChange1(int[] coins, int amount) {
        AmountCoinCount[] amountCoinCount = new AmountCoinCount[amount+1];
        for(int i=1; i<=amount; i++) amountCoinCount[i] = new AmountCoinCount(i);
        Arrays.sort(coins);
        coinChange4(coins, amount, amountCoinCount);
        if (amount == 0) {
            return 0;
        } else if(amountCoinCount[amount].count == Integer.MAX_VALUE) {
            return -1;
        }else{
            return amountCoinCount[amount].count;
        }
    }

    public static int coinChange4(int[] coins, int amount, AmountCoinCount[] amountCoinCount)   {
        int rest;
        int cc = Integer.MAX_VALUE;

        for (int i = 0; i < coins.length; i++) {
            if (amount >= coins[i]) {
                if (amountCoinCount[amount].count != -1) {
                    rest = amountCoinCount[amount].count;
                }
                else if (amount - coins[i] == 0) {
                    rest = 0;
                }
                else rest = coinChange4(coins, amount - coins[i],amountCoinCount);

                if(rest != Integer.MAX_VALUE && rest +1 < cc){
                    cc = rest+1;
//                    if(rest == 0)
                }
            }
        }
        return amountCoinCount[amount].count = cc;
    }
}

class AmountCoinCount {
    int amount, count;
    HashMap<Integer, Integer> denominationToCountMap;

    public AmountCoinCount(int amount) {
        this.amount = amount;
        denominationToCountMap = new HashMap<>();
        count = -1;
    }

    public AmountCoinCount(int amount, int denomination) {
        this(amount);
        denominationToCountMap.put(denomination, 1);
        count = 1;
    }

    public AmountCoinCount(int amount,int denomination, AmountCoinCount amountCoinCount){
        this(amount);
        int temp = 0;
        for(int i:amountCoinCount.denominationToCountMap.keySet()){
            temp = amountCoinCount.denominationToCountMap.get(i);
            if(i == denomination) denominationToCountMap.put(i,temp+1);
        }
        count = temp+1;
    }


}




































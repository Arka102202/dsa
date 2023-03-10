package KadanesAlgorithmAndOthers;

public class BestTimetoBuyandSellStock {

    public static int maxProfit(int[] prices) {
        int buy = 0, profit = 0;
        for(int i=1; i<prices.length; i++){
            if (prices[i] < prices[buy]) buy = i;
            else if (prices[i]-prices[buy] > profit) profit = prices[i]-prices[buy];
        }
        return profit;
    }

    public static void main(String[] args) {

        System.out.println(maxProfit(new int[]{2,1,2,1,0,1,2}));

        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));

        System.out.println(maxProfit(new int[]{7,6,4,3,1}));

    }


}

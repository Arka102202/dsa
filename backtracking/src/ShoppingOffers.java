import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShoppingOffers {
    static HashMap<Integer, Integer> memo = new HashMap<>();
    static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        memo.clear();
        int minPrice = 0;
        boolean[] used = new boolean[special.size()];
        Arrays.fill(used, false);
        for (int i = 0; i < needs.size(); i++) minPrice += (price.get(i) * needs.get(i));
        int p = getLowestPrice(price, special, needs, "", used, " 0".repeat(needs.size()));
        return Math.min(p, minPrice);
    }
    static int getLowestPrice(List<Integer> prices, List<List<Integer>> special,
                              List<Integer> needs, String rest, boolean[] used, String target) {
        if (rest.equals(target)) return 0;
        if (memo.containsKey(rest.hashCode())) return memo.get(rest.hashCode());
        int minPrice = Integer.MAX_VALUE;
        String newRest = "";
        boolean usable = true;
        for (int i = 0; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> newNeeds = new ArrayList<>();
            int j;
            for (j = 0; j < needs.size(); j++) {
                int required = needs.get(j);
                int offered = offer.get(j);
                usable = (required >= offered);
                if (usable) {
                    newNeeds.add(required - offered);
                    newRest = newRest.concat(" " + (required - offered));
                } else break;
            }
            if (usable) {
                used[i] = true;
                int price = getLowestPrice(prices, special, newNeeds, newRest, used, target);
                if (price != Integer.MAX_VALUE && price + offer.get(j) < minPrice) minPrice = price + offer.get(j);
                used[i] = false;
                newRest = "";
            }
        }
        int price = 0;
        for (int i = 0; i < needs.size(); i++) price += (prices.get(i) * needs.get(i));
        if (price < minPrice) minPrice = price;
        memo.put(rest.hashCode(), minPrice);
        return minPrice;
    }

    public static void main(String[] args) {


        List<Integer> list3;
        List<Integer> list4;
        List<Integer> list1;
        List<Integer> list2;
        List<List<Integer>> special;

        list1 = new ArrayList<>(List.of(1, 10));
        list3 = new ArrayList<>(List.of(2, 2));
        special = new ArrayList<>();
        special.add(list1);
        special.add(list3);
        System.out.println(shoppingOffers(new ArrayList<>(List.of(9)), special, new ArrayList<>(List.of(3))));

        list1 = new ArrayList<>(List.of(2, 1));
        list3 = new ArrayList<>(List.of(5, 3));
        list2 = new ArrayList<>(List.of(3, 2));
        list4 = new ArrayList<>(List.of(4, 2));
        special = new ArrayList<>();
        special.add(list1);
        special.add(list3);
        special.add(list2);
        special.add(list4);
        System.out.println(shoppingOffers(new ArrayList<>(List.of(10)), special, new ArrayList<>(List.of(4))));


        list1 = new ArrayList<>(List.of(1, 1, 0, 4));
        list2 = new ArrayList<>(List.of(2, 2, 1, 9));
        special = new ArrayList<>();
        special.add(list1);
        special.add(list2);
        System.out.println(shoppingOffers(new ArrayList<>(List.of(2, 3, 4)), special, new ArrayList<>(List.of(1, 2, 1))));

        list1 = new ArrayList<>(List.of(3, 0, 5));
        list2 = new ArrayList<>(List.of(1, 2, 10));
        special = new ArrayList<>();
        special.add(list1);
        special.add(list2);
        System.out.println(shoppingOffers(new ArrayList<>(List.of(2, 5)), special, new ArrayList<>(List.of(3, 2))));
    }
}

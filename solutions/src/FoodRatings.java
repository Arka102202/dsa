import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class FoodRatings {

    HashMap<Integer, TreeSet<Food>> foodMap = new HashMap<>();
    HashMap<Integer, Food> foodList = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

        for(int i=0; i< foods.length; i++){
            Food food = new Food(ratings[i],foods[i], cuisines[i]);
            foodList.put(foods[i].hashCode(),food);
            if(foodMap.containsKey(cuisines[i].hashCode())){
                foodMap.get(cuisines[i].hashCode()).add(food);
            }else{
                foodMap.put(cuisines[i].hashCode(), new TreeSet<>(List.of(food)));
            }
        }
    }

    public void changeRating(String food, int newRating) {
        Food f = foodList.get(food.hashCode());
        foodMap.get(f.cuisine.hashCode()).remove(f);
        f.rating = newRating;
        foodMap.get(f.cuisine.hashCode()).add(f);
    }

    public String highestRated(String cuisine) {
        return foodMap.get(cuisine.hashCode()).last().name;
    }

    public static void main(String[] args) {

        FoodRatings foodRatings = new FoodRatings(new String[]{"czopaaeyl","lxoozsbh","kbaxapl"},
                new String[]{"dmnuqeatj","dmnuqeatj","dmnuqeatj"}, new int[]{11,2,15});

        System.out.println(foodRatings.highestRated("dmnuqeatj"));

        foodRatings.changeRating("czopaaeyl", 12);
        System.out.println(foodRatings.highestRated("dmnuqeatj"));
        foodRatings.changeRating("lxoozsbh", 5);

        System.out.println(foodRatings.highestRated("dmnuqeatj"));
        foodRatings.changeRating("kbaxapl", 8);

        System.out.println(foodRatings.highestRated("dmnuqeatj"));




    }



}
class Food implements Comparable<Food>{
    int rating;
    String name, cuisine;

    public Food(int rating, String name, String cuisine) {
        this.rating = rating;
        this.name = name;
        this.cuisine = cuisine;
    }

    @Override
    public int compareTo(Food o) {
        return this.rating-o.rating == 0? o.name.compareTo(this.name):this.rating - o.rating;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

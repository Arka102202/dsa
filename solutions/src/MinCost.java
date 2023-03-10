import java.util.*;
public class MinCost {
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{0,0,0,3}, new int[][]{{2,2,5}, {1,5,5}, {5,1,2}, {5,2,5}}, 3, 3));
    }
    static int k = 0;
    static int start = 0;
    static int totalEmptySpace = 0;
    static int totalEmptyChunks = 0;
    static int existingNeighbour = 0;
    static int colorPosition = 0;
    static List<List<List<Integer>>> neighbourHoodEmptyHouses = new ArrayList<>();
    static List<TreeMap<Integer, List<Integer>>> costIndexMap = new ArrayList<>();
    static List<List<Integer>> combinations = new ArrayList<>();
    static int combinationsLength = 0;
    static int t1 = 0, filled1 = 0;
    public static int minCost(int[] houses, int[][] cost, int n, int target) {

        // sorting the cost matrix and collection the indices of all the similarly priced paints:
        for (int[] ints : cost) {
            TreeMap<Integer, List<Integer>> map = new TreeMap<>();
            for (int j = 0; j < ints.length; j++) {
                if (map.containsKey(ints[j])) {
                    map.get(ints[j]).add(j);
                } else {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(j);
                    map.put(ints[j], tempList);
                }
            }
            costIndexMap.add(map);
        }

        // mapping neighbourhoods with empty houses:
        // neighbourHoodEmptyHouses.size == no of neighbourhood
        // 1st level --> info about each neighbour
        //      1st level --> house color.
        //      2nd level --> empty house number(EHN)
        //      3rd level --> previous neighbour
        //      4th level --> color price
        //      5th level --> indices of empty houses(optional as EHN could be zero)
        // ==> beforeZero is for the cases 0,0,2,(3),0,0,4 ==> for 3 and all this type of cases
        int emptyHouseCount = 0;
        int neighbourhood = 0;
        boolean beforeZero = true;
        for (int house : houses) {
            if (house != 0 && house != neighbourhood && beforeZero) {
                neighbourhood = house;
                if (neighbourHoodEmptyHouses.size() > 0) {
                    if (neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0) == neighbourhood) {
                        neighbourHoodEmptyHouses.set(neighbourHoodEmptyHouses.size() - 1, createEntry(neighbourhood,
                                emptyHouseCount + neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(1).get(0),
                                neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(2).get(0), cost, true));
                    } else {
                        neighbourHoodEmptyHouses.add(createEntry(neighbourhood, emptyHouseCount,
                                neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0), cost, false));
                    }
                } else {
                    neighbourHoodEmptyHouses.add(createEntry(neighbourhood, emptyHouseCount, 0, cost, false));
                }
                emptyHouseCount = 0;
                beforeZero = false;
            }
            else if (house == 0) {
                if(!beforeZero){
                    start = k;
                }
                emptyHouseCount++;
                totalEmptySpace += 1;
                neighbourhood = house;
                beforeZero = true;
            }
            else if (house != neighbourhood) {
                neighbourhood = house;
                if (neighbourHoodEmptyHouses.size() > 0) {
                    if (neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0) == neighbourhood) {
                        neighbourHoodEmptyHouses.set(neighbourHoodEmptyHouses.size() - 1, createEntry(neighbourhood,
                                emptyHouseCount + neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(1).get(0),
                                neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(2).get(0), cost, true));
                    } else {
                        neighbourHoodEmptyHouses.add(createEntry(neighbourhood, emptyHouseCount,
                                neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0), cost, false));
                    }
                } else {
                    neighbourHoodEmptyHouses.add(createEntry(neighbourhood, emptyHouseCount, 0, cost, false));
                }
            }
            k++;
        }
        if (neighbourHoodEmptyHouses.size() > 1 && neighbourhood == 0) {
            neighbourHoodEmptyHouses.add(createEntry(0, emptyHouseCount,
                    neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0), cost, false));
        }
        else if (neighbourhood == 0) {
            neighbourHoodEmptyHouses.add(createEntry(0, emptyHouseCount, 0, cost, false));
        }

        // sorting the neighbourhoods according to the empty places
        neighbourHoodEmptyHouses.sort(Comparator.comparingInt(info -> info.get(1).get(0)));

        //calculating the min cost:
        int sum = 0;
        if (target == existingNeighbour) {
            for (List<List<Integer>> neighbourHoodEmptyHouse : neighbourHoodEmptyHouses) {
                if (neighbourHoodEmptyHouse.get(1).get(0) != 0) {
                    if (neighbourHoodEmptyHouse.get(0).get(0) != 0) {
                        for (int j : neighbourHoodEmptyHouse.get(4)) {
                            sum += cost[j][neighbourHoodEmptyHouse.get(0).get(0) - 1];
                        }
                    } else {
                        for (int j : neighbourHoodEmptyHouse.get(4)) {
                            sum += cost[j][neighbourHoodEmptyHouse.get(3).get(0) - 1];
                        }
                    }
                }
            }

            return sum;
        }
        else if (totalEmptyChunks == (target - existingNeighbour)) {
            for (int i = 0; i < neighbourHoodEmptyHouses.size(); i++) {
                if (neighbourHoodEmptyHouses.get(i).get(1).get(0) == 1) {
                    sum += lowestCost(i, cost, 0,0);
                } else if (neighbourHoodEmptyHouses.get(i).get(1).get(0) > 1) {

                    TreeSet<Integer> sumSet = new TreeSet<>();
                    int count = neighbourHoodEmptyHouses.get(i).get(4).size();
                    int sum1;
                    for(int r=1; r<=count; r++){
                        for(int advance=0; advance<count; advance++){
                            sum1 = 0;
                            boolean entered = false;
                            for(int j=0; j<count; j++){

                                if((i == 0 && j == 0) || (j == advance && j < advance+r)){
                                    if(!entered){
                                        sum1 += lowestCost(i,cost,0,j);
                                        entered = true;
                                    }else{
                                        sum1 += cost[neighbourHoodEmptyHouses.get(i).get(4).get(j)][colorPosition];
                                    }
                                }
                                else if(j < advance){
                                    sum1 += cost[neighbourHoodEmptyHouses.get(i).get(4).get(j)][neighbourHoodEmptyHouses.get(i).get(3).get(0) - 1];
                                }else {
                                    sum1 += cost[neighbourHoodEmptyHouses.get(i).get(4).get(j)][neighbourHoodEmptyHouses.get(i).get(0).get(0) - 1];
                                }


                            }
                            sumSet.add(sum1);
                        }
                    }

                    sum += sumSet.first();
                }
            }
            return sum;
        }
        else if(totalEmptyChunks == 1 && neighbourHoodEmptyHouses.get(0).get(0).get(0) == 0){

            List<Integer> combination = new ArrayList<>();
            target = 3;
            int t = 0;
            int filled = 0;
            int neighbour = 5;
            int i = 0;

            while (t < target - 1) {
                combination.add(i);
                t++;
                filled++;
            }
            combination.add(0);

            t = t1 = filled = filled1 = 0;
            createCombination(i, target, t, filled, neighbour, combination);

            int houseNumber = 0;
            int colorNumber;
            int c;
            TreeSet<Integer> sumSetLocal = new TreeSet<>();
            TreeSet<Integer> sumSetGlobal = new TreeSet<>();
            for(List<Integer> list:combinations){
                for(colorNumber=0; colorNumber<n; colorNumber++){
                    c = colorNumber;
                    for(int k=0; k<target; k++){
                        for(int l=0; l<list.get(k); l++){
                            sum += cost[houseNumber][colorNumber];
                            houseNumber++;
                        }
                        if(colorNumber == n-1){
                            colorNumber =0;
                        }else {
                            colorNumber++;
                        }
                    }
                    sumSetLocal.add(sum);
                    sum= 0;
                    houseNumber = 0;
                    colorNumber = c;
                }
                sumSetGlobal.add(sumSetLocal.first());
            }
            return sumSetGlobal.first();
        }
        else {
            return -1;
        }
    }
    public static List<List<Integer>> createEntry(int neighbourhood, int emptyHouseCount, int previousNeighbourhood, int[][] cost, boolean isSet) {
        List<Integer> info1 = new ArrayList<>();
        List<List<Integer>> info = new ArrayList<>();

        info1.add(neighbourhood);
        if (neighbourhood != 0) {
            existingNeighbour += 1;
        }
        info.add(info1);

        info1 = new ArrayList<>();
        info1.add(emptyHouseCount);
        info.add(info1);

        info1 = new ArrayList<>();
        int colorCost = 0;
        if (neighbourhood != 0) {
            if(k < cost.length){
                colorCost = cost[k][neighbourhood - 1];
            }else{
                colorCost = cost[k-1][neighbourhood - 1];
            }
        } else {
            if (neighbourHoodEmptyHouses.size() > 1) {
                colorCost = cost[k - 1][neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(0).get(0) - 1];
            }
        }
        info1.add(colorCost);
        info.add(info1);

        info1 = new ArrayList<>();
        info1.add(previousNeighbourhood);
        info.add(info1);

        if (!isSet) {
            if (emptyHouseCount != 0) {
                info1 = new ArrayList<>();
                for (int i = start; i < k; i++) {
                    info1.add(i);
                }
                info.add(info1);
                totalEmptyChunks += 1;
            }
        } else {
            if (neighbourHoodEmptyHouses.size() > 1 && neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).size() > 3) {
                info1 = neighbourHoodEmptyHouses.get(neighbourHoodEmptyHouses.size() - 1).get(3);
                for (int i = start; i < k; i++) {
                    info1.add(i);
                }
                info.add(info1);
            } else {
                info1 = new ArrayList<>();
                for (int i = start; i < k; i++) {
                    info1.add(i);
                }
                info.add(info1);
            }
        }
        return info;
    }
    public static int lowestCost(int neighbourhood, int[][] cost, int costIndex, int colorIndex) {
        int sum = 0;
        int presentHouseNumber = neighbourHoodEmptyHouses.get(neighbourhood).get(4).get(colorIndex);
        Arrays.sort(cost[presentHouseNumber]);
        int lowestCost = cost[presentHouseNumber][costIndex];
        List<Integer> lowestCostPositionList = costIndexMap.get(presentHouseNumber).get(lowestCost);
        if (!lowestCostPositionList.contains(neighbourHoodEmptyHouses.get(neighbourhood).get(2).get(0)-1) && lowestCostPositionList.size() > 1) {
            sum += lowestCost;
        } else if (!lowestCostPositionList.contains(neighbourHoodEmptyHouses.get(neighbourhood).get(0).get(0)-1) && lowestCostPositionList.size() > 1) {
            sum += lowestCost;
        } else if (lowestCostPositionList.size() > 3) {
            sum += lowestCost;
        }
        else if(!lowestCostPositionList.contains(neighbourHoodEmptyHouses.get(neighbourhood).get(2).get(0)-1) &
                    !lowestCostPositionList.contains(neighbourHoodEmptyHouses.get(neighbourhood).get(0).get(0)-1) & lowestCostPositionList.size() == 1){
            sum += lowestCost;
        }
        else {
            sum += lowestCost(neighbourhood, cost, costIndex + 1, colorIndex);
        }
        colorPosition = colorIndex;
        return sum;
    }
    public static void createCombination(int i, int target, int t, int filled, int houses, List<Integer> combination) {

        for (int j = 1; j <= (houses - target + 1); j++) {
            combination = new ArrayList<>(combination);
            if (i >= target - 1) {
                break;
            } else if (filled != houses) {
                combination.set(i, j);
                t++;
                filled += j;
            }
            combinationsLength = combinations.size();
            int t1 = TestClass.t1;
            int filled1 = TestClass.filled1;
            TestClass.t1 = t;
            TestClass.filled1 = filled;
            createCombination(i + 1, target, t, filled, houses, combination);
            TestClass.t1 = t1;
            TestClass.filled1 = filled1;
            if (combinationsLength == combinations.size()) {
                TestClass.flag = true;
            }
            if (t >= target - 1 && houses - filled > 0) {
                combination.set(target - 1, houses - filled);
                combinations.add(combination);
                t = t1;
                filled = filled1;

            } else if (TestClass.flag) {
                TestClass.flag = false;
                t = t1;
                filled = filled1;
            } else {
                combinationsLength = combinations.size();
                break;
            }
        }
    }
}
package divideAndConqure;
public class MintCostDC {
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{0, 1, 0, 0, 1, 2, 0, 0, 2, 1},
                new int[][]{{4, 5, 2, 6}, {8, 3, 2, 9}, {6, 7, 3, 1}, {10, 10, 2, 7}, {6, 5, 2, 4}, {4, 4, 3, 9}, {9, 8, 3, 5}, {7, 9, 10, 3}, {8, 5, 9, 10}, {10, 7, 4, 6}},
                10,
                4,
                6
        ));
    }

    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int houseNumber = 0;
        int[][][] memo = new int[m][target + 1][n + 1];
        int minCost = findTotalCost2(houses, cost, n, memo, houseNumber, target, 0, 0);

        if (minCost == Integer.MAX_VALUE) {
            return -1;
        }
        return minCost;

    }

    static int f;


    public static int findTotalCost2(int[] houses, int[][] cost, int colourRange, int[][][] memo, int houseNumber, int t, int preColor, int n) {

        int minCost = Integer.MAX_VALUE;
        boolean changed;
        int color = 0;

        if (n > t) return Integer.MAX_VALUE;

        if (houseNumber >= houses.length) return n < t ? Integer.MAX_VALUE : 0;

        if (memo[houseNumber][n][color] != 0) return memo[houseNumber][n][preColor];

        if (houses[houseNumber] == 0) {
            for (int i = 0; i < colourRange; i++) {
                int c;
                if (i + 1 != preColor) {
                    n += 1;
                    changed = true;
                } else {
                    changed = false;
                }
                color = i + 1;
                c = findTotalCost2(houses, cost, colourRange, memo, houseNumber + 1, t, i + 1, n);

                if (c != Integer.MAX_VALUE && c + cost[houseNumber][i] < minCost) {
                    minCost = c + cost[houseNumber][i];
                    memo[houseNumber][n][color] = minCost;
                }
                if (changed) {
                    n -= 1;
                }

            }
        } else {

            if (houses[houseNumber] != preColor) {
                n += 1;
            }
            minCost = findTotalCost2(houses, cost, colourRange, memo, houseNumber + 1, t, houses[houseNumber], n);
        }


        return minCost;

    }


    public static int findTotalCost1(int[] houses, int[][] cost, int colourRange, int[][][] memo, int houseNumber, int t, int preColor, int n) {

        int minCost = Integer.MAX_VALUE;
        boolean changed;

        if (houseNumber < houses.length && houses[houseNumber] == 0) {

            for (int i = 0; i < colourRange; i++) {
                f += 1;

                if (i + 1 != preColor) {
                    n += 1;
                    changed = true;
                } else {
                    changed = false;
                }
                if (n <= t && houses.length - f >= t - n && f <= houses.length) {
                    int c = 0;
                    if (f < houses.length) {
                        if (memo[houseNumber][n][i + 1] != 0) {
                            c = memo[houseNumber][n][i + 1];
                            c -= cost[houseNumber][i];
                        } else {
                            c = findTotalCost1(houses, cost, colourRange, memo, houseNumber + 1, t, i + 1, n);
                        }
                    } else {
                        memo[houseNumber][n][i + 1] = cost[houseNumber][i];
                    }
                    if (c != Integer.MAX_VALUE && c + cost[houseNumber][i] < minCost) {
                        minCost = c + cost[houseNumber][i];
                    }
                }
                f -= 1;
                if (changed) {
                    n -= 1;
                }
            }
        } else if (houseNumber < houses.length) {
            if (houses[houseNumber] != preColor) {
                n += 1;
                changed = true;
            } else {
                changed = false;
            }
            f += 1;
            int c;

            if (n <= t && houses.length - f >= t - n && f < houses.length) {
                c = findTotalCost1(houses, cost, colourRange, memo, houseNumber + 1, t, houses[houseNumber] - 1, n);
                if (c != Integer.MAX_VALUE) {
                    minCost = c;
                }
            } else if (n == t && f == houses.length) {
                minCost = 0;
            }
            if (changed) {
                n -= 1;
            }
            f -= 1;
        }

        return memo[houseNumber][n][preColor] = minCost;

    }


}

//    static List<Integer> p = new ArrayList<>();
//    public static void findTotalCost(int[] houses, int[][] cost, int[] colourNumbers, int colour, int filled, int neighbour, int totalCost, int houseNumber, int target) {
//
//        boolean entered = false;
//
//        for(int colourN=0; colourN<colourNumbers.length; colourN++){
//
//           if(houseNumber <houses.length && houses[houseNumber] == 0){
//               boolean neighbourChanged = false;
//               if(colour > colourNumbers.length-1){colour =0;}
//               if (colourN != colour) {
//                   neighbour+=1;
//                   neighbourChanged = true;
//               }
//               filled+=1;
//
//               if(houses.length - filled >= target - neighbour && filled < houses.length){
//                   totalCost += cost[houseNumber][colourN];
//                   p.add(colourN);
//                   findTotalCost(houses, cost, colourNumbers, colourN, filled, neighbour, totalCost, houseNumber+1, target);
//                   filled-=1;
//                   if(neighbourChanged){
//                       neighbour-=1;
//                   }
//                   totalCost -= cost[houseNumber][colourN];
//               }else if(houses.length - filled < target - neighbour && filled < houses.length){
//                   filled-=1;
//                   if(neighbourChanged){
//                       neighbour-=1;
//                   }
//               }else if(neighbour < target && filled == houses.length){
//                   filled-=1;
//                   if(neighbourChanged){
//                       neighbour-=1;
//                   }
//               }else if(neighbour > target && filled == houses.length){
//                   filled-=1;
//                   if(neighbourChanged){
//                       neighbour-=1;
//                   }
//               }
//               if(neighbour == target && filled == houses.length){
//                   totalCost += cost[houseNumber][colourN];
//                   allCosts.add(totalCost);
//                   p.clear();
//                   filled-=1;
//                   if(neighbourChanged){
//                       neighbour-=1;
//                   }
//                   totalCost -= cost[houseNumber][colourN];
//
//               }
//           }
//
//           else if( houseNumber <houses.length &&!entered ) {
//               if (houses[houseNumber]-1 != colour) {
//                   neighbour+=1;
//               }
//               filled+=1;
//               if(neighbour < target | filled < houses.length){
//                   findTotalCost(houses, cost, colourNumbers, houses[houseNumber]-1, filled, neighbour, totalCost, houseNumber+1, target);
//               } else if(neighbour == target && filled == houses.length){
//                   allCosts.add(totalCost);
//               }
//               entered = true;
//           }
//        }
//    }


//new int[]{4,0,0,0,4,11,0,0,0,0,3,0,0,0,0,5,0,0,0,0,0,8,2,2,0},
//                new int[][]{{33,13,38,3,25,10,49,9,10,36,39,3},
//                        {47,19,6,37,2,23,50,18,46,14,24,33},
//                        {32,31,32,17,36,41,43,29,36,29,47,3},
//                        {25,27,5,31,1,17,27,46,10,8,31,49},
//                        {50,16,33,24,42,2,33,39,43,31,2,38},
//                        {38,6,23,18,9,13,31,36,28,7,7,1},
//                        {40,23,21,5,48,2,18,24,6,27,39,44},
//                        {25,43,4,9,5,5,30,42,23,41,7,15},
//                        {45,32,44,15,5,1,2,43,49,30,29,4},
//                        {39,26,42,45,27,28,41,6,42,27,4,43},
//                        {32,2,43,13,15,30,32,12,36,5,19,22},
//                        {12,23,13,8,8,9,32,43,46,41,43,8},
//                        {10,18,27,2,7,40,44,50,32,29,42,10},
//                        {50,7,15,9,32,9,15,10,15,41,10,36},
//                        {48,6,26,6,14,37,44,47,4,44,1,30},
//                        {34,46,12,32,19,1,18,31,1,16,44,48},
//                        {15,35,17,14,16,29,23,18,28,26,45,17},
//                        {43,45,7,39,37,18,18,33,24,47,27,46},
//                        {17,12,15,20,44,34,14,8,28,40,12,21},
//                        {18,10,15,47,21,7,47,34,37,49,16,24},
//                        {19,3,38,14,32,21,4,25,34,3,33,23},
//                        {21,45,3,49,45,40,38,10,30,5,37,21},
//                        {29,38,43,22,44,26,3,18,45,40,40,17},
//                        {21,12,30,23,4,25,32,43,37,15,35,30},
//                        {38,14,6,21,3,43,43,30,9,19,39,17}},
//                25,
//                12,
//                15
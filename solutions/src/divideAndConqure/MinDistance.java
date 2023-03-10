package divideAndConqure;

public class MinDistance {

    static int[][] arr;
    static int MATCH = 0;
    static final int INSERT = 1;
    static final int DELETE = 2;
    static int MAXLEN;
    static int x = 0;
//arr = new int[word1.length() + 1][word2.length() + 1];
//        for (int[] ints : arr) Arrays.fill(ints, -1);
//        MAXLEN = Math.max(word1.length(), word2.length());
////        return processDynamically(word1, word2);
//        return dp(word1, word2);

    public static int minDistance(String word1, String word2) {
        word1 = " ".concat(word1);
        word2 = " ".concat(word2);
        MAXLEN = Math.max(word1.length(), word2.length());
        return dp(word1, word2);
    }
    static int dp(String word, String pattern) {

        int[] opt = new int[3];
        int[][] cost = new int[MAXLEN][MAXLEN];
        int[][] parent = new int[MAXLEN][MAXLEN];

        for(int i=0; i<MAXLEN; i++){
            rowInit(cost, parent, i);
            columnInit(cost, parent, i);
        }

        for(int i=1; i<word.length(); i++){
            for(int j=1; j<pattern.length(); j++){

                opt[MATCH] = cost[i-1][j-1] + match(word.charAt(i), pattern.charAt(j));
                opt[INSERT] = cost[i][j-1] + indel(pattern.charAt(j));
                opt[DELETE] = cost[i-1][j] + indel(pattern.charAt(j));

                cost[i][j] = opt[MATCH];
                parent[i][j] = MATCH;

                for(int k=INSERT; k<=DELETE; k++){

                    if(opt[k]<cost[i][j]) {
                        cost[i][j] = opt[k];
                        parent[i][j] = k;
                    }
                    x++;
                }
                x++;
            }
        }

//        for(int[] sArr:cost) {
//            for (int s : sArr) System.out.printf("%3d ",s);
//            System.out.println();
//        }
//
//        System.out.println();
//        for(int[] sArr:parent) {
//            for (int s : sArr) System.out.printf("%3d ",s);
//            System.out.println();
//        }

        int[] goalIndices = goalCell(word,pattern);

        reconstructPath(word,pattern,goalIndices[0],goalIndices[1],parent);
        System.out.println();
        return cost[goalIndices[0]][goalIndices[1]];
    }
    static void rowInit(int[][] cost, int[][] parent, int i){
        cost[0][i] = i;
        parent[0][i] = i>0?INSERT:-1;

    }
    static void columnInit(int[][] cost, int[][] parent, int i){
        cost[i][0] = i;
        parent[i][0] = i>0?DELETE:-1;
    }
    static int match(char c, char d) {
        return c == d ?0:1;
    }
    static int indel(char c){
        return 1;
    }
    static int[] goalCell(String word, String pattern){

        int i = word.length()-1;
        int j = pattern.length()-1;
        return new int[]{i,j};
    }


    static void reconstructPath(String word, String pattern, int i, int j, int[][] parent){

        if(parent[i][j] == -1) return;

        if ( parent[i][j] == MATCH){
            reconstructPath(word, pattern, i-1,j-1,parent);
            if(word.charAt(i) == pattern.charAt(j)) System.out.print("M ");
            else System.out.print("R ");
        }

        if ( parent[i][j] == INSERT){
            reconstructPath(word, pattern, i,j-1,parent);
            System.out.print("I ");
        }

        if ( parent[i][j] == DELETE){
            reconstructPath(word, pattern, i-1,j,parent);
            System.out.print("D ");
        }

    }












    static int processDynamically(String word, String pattern) {
        if (pattern.length() == 0 || word.length() == 0) {
            MATCH++;
            return word.length() == 0 ? pattern.length() : word.length();
        }
        if (arr[word.length()][pattern.length()] != -1) {
            MATCH++;
            return arr[word.length()][pattern.length()];
        }
        int minCount = Integer.MAX_VALUE;
        if (word.charAt(0) == pattern.charAt(0)) {
            MATCH++;
            minCount = processDynamically(word.substring(1), pattern.substring(1));
        } else {
            for (int j = 0; j < 3; j++) {
                int count;
                // for replace:
                if (j == 0) count = processDynamically(word.substring(1), pattern.substring(1));
                    // for delete
                else if (j == 1) count = processDynamically(word.substring(1), pattern);
                    // for insert
                else count = processDynamically(word, pattern.substring(1));
                if (count != Integer.MAX_VALUE && count + 1 < minCount) minCount = count + 1;
                MATCH++;
            }
        }

        return arr[word.length()][pattern.length()] = minCount;
    }

    public static void main(String[] args) {
        System.out.println(minDistance("thou shalt not", "you should not"));
        System.out.println(minDistance("i want thing more", "i want nothing more"));
        System.out.println(minDistance("retropctve", "retrospective"));
        System.out.println((minDistance("sea", "eat") == 2));
        System.out.println(minDistance("ab", "bc") == 2);
        System.out.println(minDistance("arkadyuti", "joyeeta") == 8);
        System.out.println(minDistance("shot", "spot") == 1);
        System.out.println(minDistance("intention", "execution") == 5);
    }


}



//for(int[] sArr:cost) {
//            for (int s : sArr) System.out.printf("%3d ",s);
//            System.out.println();
//        }












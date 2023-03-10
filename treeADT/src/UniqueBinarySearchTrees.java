import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTrees {
    static ArrayList<List<Integer>> nCrs = new ArrayList<>();
    static{
        nCrs.add(List.of(1,1));
        nCrs.add(List.of(1,2,1));
        nCrs.add(List.of(1,3,3,1));
        nCrs.add(List.of(1,4,6,4,1));
        nCrs.add(List.of(1,5,10,10,5,1));
        nCrs.add(List.of(1,6,15,20,15,6,1));
        nCrs.add(List.of(1,7,21,35,35,21,7,1));
        nCrs.add(List.of(1,8,28,56,70,56,28,8,1));
        nCrs.add(List.of(1,9,36,84,126,126,84,36,9,1));
        nCrs.add(List.of(1,10,45,120,210,252,210,120,45,10,1));
        nCrs.add(List.of(1,11,55,165,330,462,462,330,165,55,11,1));
        nCrs.add(List.of(1,12,66,220,495,792,924,792,495,220,66,12,1));
        nCrs.add(List.of(1,13,78,286,715,1287,1716,1716,1287,715,286,78,13,1));
        nCrs.add(List.of(1,14,91,364,1001,2002,3003,3432,3003,2002,1001,364,91,14,1));
        nCrs.add(List.of(1,15,105,455,1365,3003,5005,6135,6135,5005,3003,1365,455,105,15,1));
        nCrs.add(List.of(1,16,120,560,1820,4368,8008,11440,12870,11440,8008,4368,1820,560,120,16,1));
    }
    static int numTrees(int n) {
        int[] prev = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        return n == 1 ? 1: generate(n,prev,n-1,1,1,0);
    }
    static int generate(int n, int[] prev, int rem, int totalNodeCount, int layer, int totalCount) {
        if (rem == 1) return prev[layer-1] * 2;
        if (rem == 0) return 1;
        int count = 0;
        int pow = Math.min((int) Math.pow(2, layer), rem);
        if(layer > 0) pow = Math.min(pow, 2*prev[layer-1]);
        for (int j = 0; j < pow; j++) {
            int nodeCount = pow - j;
            totalNodeCount += nodeCount;
            prev[layer]= nodeCount;
            count += (generate(n, prev, n - totalNodeCount, totalNodeCount, layer + 1, totalCount) * nCr(2*prev[layer-1],nodeCount));
            totalNodeCount -= nodeCount;
        }
        totalCount += count;
        return totalCount;
    }
    static int nCr(int n, int r){
        return nCrs.get(n-1).get(r);
    }
    public static void main(String[] args) {
        for(int i=1; i<=19; i++) System.out.println(i +" --> " + numTrees(i));
    }
}

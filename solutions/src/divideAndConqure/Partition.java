package divideAndConqure;

import java.util.Arrays;

public class Partition {

    static int MAXN, MAXK;

    static void partitionWithOutRearrangement(int[] s, int n, int k){
        MAXK = k;
        MAXN = n;
        partition(s,n,k);
    }

    static void partition(int[] s, int n, int k){

        int[][] momo = new int[MAXN+1][MAXK+1];
        int[][] dividers = new int[MAXN+1][MAXK+1];
        int[] prefixSumArray = new int[MAXN+1];
        int cost;

        prefixSumArray[0] = 0;
        for(int i=1; i<=n; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + s[i-1];
        }

        for (int i=0; i<=n; i++) momo[i][1] = prefixSumArray[i];
        for (int i=0; i<=k; i++) dividers[1][i] = s[1];

        for(int i=2; i<=n; i++){
            for(int j=2; j<=k; j++){
                momo[i][j] = Integer.MAX_VALUE;
                for(int x=1; x<=(i-1); x++){

                    cost = Math.max(momo[x][j-1], prefixSumArray[i] - prefixSumArray[x]);

                    if(momo[i][j] > cost){
                        momo[i][j] = cost;
                        dividers[i][j] = x;
                    }
                }
            }
        }

        reconstructPartition(s,dividers,n,k);
    }
    static void reconstructPartition(int[] s, int[][] dividers, int n, int k){

        if(k == 1) System.out.println(Arrays.toString(Arrays.copyOfRange(s, 0, n)));
        else {
            reconstructPartition(s, dividers, dividers[n][k], k-1);
            System.out.println(Arrays.toString(Arrays.copyOfRange(s, dividers[n][k], n)));
        }

    }

    public static void main(String[] args) {
        partitionWithOutRearrangement(new int[]{19, 11, 1, 3, 3, 1, 9, 9, 3, 1},9 ,3);
    }

























}

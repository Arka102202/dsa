import java.util.Arrays;

public class DisjointSet {
    int[] parent, rank;
    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }
    public int find(int num){
        if(parent[num] == num) return num;
        else return find(parent[num]);
    }
    public int pathCompressedFind(int num){
        if(parent[num] == num) return num;
        else {
            rank[num] = 1;
            return parent[num] = pathCompressedFind(parent[num]);
        }
    }

    public void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py) parent[py] = px;
    }
    public void unionByRank(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py) {
            if(rank[px] > rank[py]) parent[py] = px;
            else if (rank[px] < rank[py]) parent[px] = py;
            else {
                parent[py] = px;
                rank[px] +=1;
            }
        }
    }

    public static void main(String[] args) {
        DisjointSet d = new DisjointSet(8);
        d.unionByRank(0,2);
        d.unionByRank(2,3);
        d.unionByRank(3,4);
        d.unionByRank(5,6);
        d.unionByRank(4,6);
        System.out.println(Arrays.toString(d.parent));
        System.out.println(Arrays.toString(d.rank));
    }

}

import java.util.LinkedList;
import java.util.List;

public class Combinations {

    static List<List<Integer>> lists = new LinkedList<>();

    public static List<List<Integer>> combine(int n, int k) {
        lists.clear();
        LinkedList<Integer> list = new LinkedList<>();
        generate(n,k,k-1,1,list);
        return lists;
    }

    static void generate(int n, int k, int c, int start, LinkedList<Integer> list) {
        for(int i=start; i<= n-c; i++){
            list.add(i);
            if(list.size() == k) lists.add(new LinkedList<>(list));
            else if (list.size() < k) generate(n,k,c-1, i+1,list);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(1,1));
        combine(15,4).forEach(System.out::println);
    }



}

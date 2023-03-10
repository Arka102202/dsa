import java.util.*;

public class CombinationSum {
    static List<List<Integer>> lists = new LinkedList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        lists.clear();
        Arrays.sort(candidates);
        generate4(candidates, new LinkedList<>(), 0, new boolean[candidates.length], candidates.length);
        return lists;
    }
    public static void generate(int[] candidates, int target, int sum, LinkedList<Integer> list, int start) {
        if (sum == target) lists.add(new LinkedList<>(list));
        else if (sum < target) {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                generate(candidates, target, sum + candidates[i], list, i);
                list.remove(Integer.valueOf(candidates[i]));
            }
        }
    }
    public static void generate2(int[] candidates, int target, int sum, LinkedList<Integer> list, int start) {
        if (sum == target) lists.add(new LinkedList<>(list));
        else if (sum < target) {
            int prev = -1;
            for (int i = start; i < candidates.length; i++) {
                if (prev != candidates[i]) {
                    list.add(candidates[i]);
                    generate2(candidates, target, sum + candidates[i], list, i + 1);
                    list.remove(Integer.valueOf(candidates[i]));
                }
                prev = candidates[i];
            }
        }
    }
    public static void generate3(int[] candidates, LinkedList<Integer> list, int start, boolean[] visited, int len) {
        for (int i = start; i < candidates.length; i++) {
            if (!visited[i]) {
                list.add(candidates[i]);
                visited[i] = true;
                if (list.size() == len) lists.add(new ArrayList<>(list));
                else generate3(candidates, list, 0, visited, len);
                list.remove(Integer.valueOf(candidates[i]));
                visited[i] = false;
            }
        }
    }
    public static void generate4(int[] candidates, LinkedList<Integer> list, int start, boolean[] visited, int len) {
        int prev = -12;
        for (int i = start; i < candidates.length; i++) {
            if (!visited[i] && prev != candidates[i]) {
                list.add(candidates[i]);
                prev = candidates[i];
                visited[i] = true;
                if (list.size() == len) {
                    lists.add(new ArrayList<>(list));
                } else generate4(candidates, list, 0, visited, len);
                list.removeLast();
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                30));
        System.out.println(combinationSum(new int[]{1, 2}, 4));
        System.out.println(combinationSum(new int[]{100, 200, 4, 12}, 400));
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{3,3,0,3}, 8));

    }
}
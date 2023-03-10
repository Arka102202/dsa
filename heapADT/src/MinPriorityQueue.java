import java.util.ArrayList;

public class MinPriorityQueue {
    ArrayList<Integer> arr0 = new ArrayList<>();
    public void add(int val){
        arr0.add(val);
        minHeapifyUp(arr0.size()-1, val);
    }
    public void addAll(int[] arr){
        for(int i:arr) add(i);
    }
    public void minHeapifyUp(int pos, int val){
        while (pos > 0){
            int parent = (pos-1) >>> 1;
            int pVal = arr0.get(parent);
            if(pVal < val) break;
            arr0.set(pos, pVal);
            pos = parent;
        }
        arr0.set(pos, val);
    }
    public int removeMin(){

        int min = arr0.get(0);
        arr0.set(0,arr0.get(arr0.size()-1));
        arr0.remove(arr0.size()-1);
        minHeapifyDown();

        return min;
    }
    public void minHeapifyDown(){

        int half = arr0.size() >>> 1, pos = 0;
        int val;

        if(arr0.size() > 0) {
            val = arr0.get(0);
            while (pos < half) {
                int child = (pos << 1) + 1, childVal = arr0.get(child);
                int right = child + 1;

                if (right < arr0.size() && arr0.get(right) < childVal)
                    childVal = arr0.get(child = right);

                if (childVal > val) break;

                arr0.set(pos, childVal);
                pos = child;
            }
            arr0.set(pos, val);
        }

    }
    public ArrayList<Integer> sort(int[] arr){
        addAll(arr);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i< arr.length; i++) list.add(removeMin());
        return list;
    }

    public static void main(String[] args) {

        MinPriorityQueue queue = new MinPriorityQueue();

        int[] arr = new int[]{-1,-1};
        System.out.println(queue.sort(arr));

    }
}

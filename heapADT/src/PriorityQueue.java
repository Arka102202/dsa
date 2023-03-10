import java.util.Arrays;

public class PriorityQueue {
    int[] arr;
    int size = 0, initCap = 10;
    PriorityQueue(){
        arr = new int[initCap];
    }

    PriorityQueue(int[] arr){
        this.arr = Arrays.copyOf(arr,(size = initCap = arr.length));
        int i = (size >>> 1) -1;
        for(;i>=0;i--){
            maxHeapifyDown(i,arr[i],size);

        }
    }
    public boolean add(int num){
        size++;
        if(size > initCap) {
            initCap <<=1;
            arr = Arrays.copyOf(arr,initCap);
        }
        maxHeapifyUp(size-1,num);
        return true;
    }
    public boolean addAll(int[] arr){
        for(int i:arr) add(i);
        return true;
    }
    public int deleteMax(){
        if(size > 0){
            int max = arr[0];
            arr[0] = arr[size-1];
            arr[size-1] = 0;
            maxHeapifyDown(0,arr[0],--size);
            return max;
        }else return -1;
    }
    public void maxHeapifyUp(int p, int value){
        while(p>0){
            int parent = (p - 1) >>> 1;
            int parentValue = arr[parent];

            if(parentValue > value)
                break;

            arr[p] = parentValue;
            p = parent;
        }
        arr[p] = value;
    }
    public void maxHeapifyDown(int p, int value,int n){

        int half = n >>> 1;

        while (p<half){
            int child = (p << 1)+1;
            int childValue = arr[child];
            int right = child+1;

            if(right < n && arr[right] > arr[child])
                childValue = arr[child = right];
            if (arr[child] < arr[p])
                break;

            arr[p] = childValue;
            p = child;
            arr[p] = value;
        }

        arr[p] = value;
    }



    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();

        int[] arr = new int[]{3, 68, 43, 5, 10, 13, 11, 63, 28, 23};
        pq.addAll(arr);
        for(int ignored=0; ignored<arr.length; ignored++) System.out.print(pq.deleteMax() +", ");
        System.out.print("\b\b");

    }

















}

import java.util.ArrayList;
import java.util.List;

public class UnrolledLinkedList {


    List<UnrolledListNode> listOfArray = new ArrayList<>();

    static int arrayLength = (int)Math.sqrt(100000.0);


    void add(int num){
        

        for(int i=0; i< listOfArray.size(); i++){

            if(listOfArray.get(i) != null && listOfArray.get(i).getArr().length != arrayLength){
                listOfArray.get(i).add(i);
            }



        }
    }




















}


class UnrolledListNode{

    Integer[] arr;
    int capacity;
    UnrolledListNode next;
    int count;

    public UnrolledListNode(UnrolledListNode next) {
        this.next = next;
    }

    public UnrolledListNode(int capacity){
        this(new Integer[capacity], capacity, null);

    }

    public UnrolledListNode(Integer[] arr, int capacity){
        this(arr,capacity, null);
    }

    public UnrolledListNode(Integer[] arr, int capacity, UnrolledListNode next) {
        this.arr = arr;
        this.capacity = capacity;
        this.next = next;
    }

    void add(int num){
        if(arr.length < capacity){
            arr[count] = num;
            count++;
        }
    }

    public Integer[] getArr() {
        return arr;
    }

    public int getCapacity() {
        return capacity;
    }

    public UnrolledListNode getNext() {
        return next;
    }
}
import java.util.Arrays;

public class SkipList {
    int[] list;

    boolean added;

    static int temp1,temp2;

    int len;
    public SkipList() {
        list = new int[50000+10];
    }

    public boolean search(int target) {
        int position = Arrays.binarySearch(list, 0,len, target);
        return position >= 0;
    }

    public void add(int num) {

        if(added){
            int position = Arrays.binarySearch(list,0,len,num);
            if(position >= 0 && position < list.length){
                arrayUpdateForAddition(position+1, len);
                list[position+1] = num;
            }else{
                arrayUpdateForAddition((-position)-1, len);
                list[(-position)-1] = num;
            }
            len++;
        }else{
            list[0] = num;
            len++;
            added = true;
        }


    }

    public boolean erase(int num) {

        int position = Arrays.binarySearch(list,0, len, num);

        if(position >= 0) {
            arrayUpdateForRemoval(position);
            return true;
        }else return false;

    }

    void arrayUpdateForAddition(int position, int len){
        for(int i=position; i<len+1; i++){
            temp1 = list[i];
            list[i] = temp2;
            temp2 = temp1;
        }

    }

    void arrayUpdateForRemoval(int position){
//        if (len - position >= 0) System.arraycopy(list, position + 1, list, position, len - position);

        if (len - position >= 0) System.arraycopy(list, position + 1, list, position, len - position);
        len--;

    }


    public static void main(String[] args) {

        int[] arr = {1,2,3,5,7};

        SkipList skipList = new SkipList();

        for(int i:arr){
            skipList.add(i);
        }
//        for(int i=0; i<arr.length; i++){
//            skipList.erase(arr[i]);
//        }
//
//        skipList.erase(5);

        System.out.println(Arrays.toString(skipList.list).replaceAll("[ 0,]", ""));

        System.out.println(skipList.search(0));

    }


}
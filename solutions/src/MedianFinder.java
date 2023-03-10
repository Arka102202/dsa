import java.util.Arrays;

public class MedianFinder {
    int[] list;
    boolean added;

    static int temp1,temp2;

    static int len;
    public MedianFinder() {

        list = new int[50000+10];
    }
    public void addNum(int num) {

        if(added){
            int position = Arrays.binarySearch(list,0,len,num);
            if(position >= 0 && position < list.length){
                arrayUpdate(position+1, len);
                list[position+1] = num;
            }else{
                arrayUpdate((-position)-1, len);
                list[(-position)-1] = num;
            }
            len++;
        }else{
            list[0] = num;
            len++;
            added = true;
        }


    }

    public double findMedian() {

        if((len & 1) == 0){
            return (double)(list[(len>>1)-1] + list[len>>1])/2;
        }else{
            return list[((len+1)/2)-1];
        }
    }

    void arrayUpdate(int position, int len){


        for(int i=position; i<len+1; i++){

            temp1 = list[i];
            list[i] = temp2;
            temp2 = temp1;
        }

    }


    public static void main(String[] args) {


        MedianFinder medianFinder = new MedianFinder();

        int[] arr = {1,2,3,3,3,3,3,3,5,5,5,5,5,7};

        for(int i:arr){
            medianFinder.addNum(i);
        }

        System.out.println(Arrays.toString(medianFinder.list));


    }

}


//    private int binarySearch(int[] a, long key) {
//        int low = 0;
//        int high = list.size() - 1;
//
//        while (low <= high) {
//            int mid = (low + high) >>> 1;
//            long midVal = a.get(mid);
//
//            if (midVal < key)
//                low = mid + 1;
//            else if (midVal > key)
//                high = mid - 1;
//            else
//                return mid; // key found
//        }
//        return -(low + 1);  // key not found.
//    }
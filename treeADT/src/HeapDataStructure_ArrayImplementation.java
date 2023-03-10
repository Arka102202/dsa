import java.util.Arrays;

public class HeapDataStructure_ArrayImplementation {
    static int[] createMaxheap(int[] arr){
         for(int i=arr.length >>> 1; i>0; i--){
             maxHeapifyDown(i-1,arr);
         }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    static void maxHeapifyDown(int parent, int[] arr){
        if(parent < arr.length){
            int leftChild = ((parent+1) << 1)-1;
            int rightChild = leftChild+1;
            int temp = arr[parent];
            if(leftChild < arr.length && rightChild < arr.length){
                if(arr[leftChild] >= arr[rightChild] && arr[leftChild] > arr[parent]){
                    arr[parent] = arr[leftChild];
                    arr[leftChild] = temp;
                    maxHeapifyDown(leftChild,arr);
                }else if (arr[leftChild] < arr[rightChild] && arr[rightChild] > arr[parent]){
                    arr[parent] = arr[rightChild];
                    arr[rightChild] = temp;
                    maxHeapifyDown(rightChild,arr);
                }
            }else if (leftChild < arr.length && arr[leftChild] > arr[parent]){
                arr[parent] = arr[leftChild];
                arr[leftChild] = temp;
                maxHeapifyDown(leftChild,arr);
            }
        }
    }
    static void minHeapify(int parent, int[] arr){
        if(parent-1 < arr.length){
            int leftChild = (parent << 1)-1;
            int rightChild = leftChild+1;
            parent -= 1;
            int temp = arr[parent];
            if(leftChild < arr.length && rightChild < arr.length){
                if(arr[leftChild] <= arr[rightChild] && arr[leftChild] <= arr[parent]){
                    arr[parent] = arr[leftChild];
                    arr[leftChild] = temp;
                    minHeapify(leftChild+1,arr);
                }else if (arr[leftChild] > arr[rightChild] && arr[rightChild] <= arr[parent]){
                    arr[parent] = arr[rightChild];
                    arr[rightChild] = temp;
                    minHeapify(rightChild+1,arr);
                }
            }
            else if (leftChild < arr.length && leftChild >= 0 && arr[leftChild] < arr[parent]){
                arr[parent] = arr[leftChild];
                arr[leftChild] = temp;
                minHeapify(leftChild+1,arr);
            }
        }
    }
    static int removeMax(int[] arr){
        int max = arr[0];
        int leftChild = (1<<1)-1;
        int rightChild = leftChild+1;
        if(leftChild < arr.length && rightChild < arr.length){
            if(arr[leftChild] >= arr[rightChild]) {
                arr[0] = arr[leftChild];
                arr[leftChild] = 0;
                maxHeapifyDown(leftChild, arr);
            }else {
                arr[0] = arr[rightChild];
                arr[rightChild] = 0;
                maxHeapifyDown(rightChild, arr);
            }
        }else if (leftChild < arr.length){
            arr[0] = arr[leftChild];
            arr[leftChild] = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = createMaxheap(new int[]{20,1,12,5,6,9,9});
        for(int i=0; i<arr.length; i++){
            System.out.print(removeMax(arr) + ", ");
        }
        System.out.print("\b\b\n");
        arr = createMaxheap(new int[]{20,6,12,10,15,9,7,1,5,3});
        for(int i=0; i<arr.length; i++){
            System.out.print(removeMax(arr) + ", ");
        }
        System.out.print("\b\b\n");
        arr = createMaxheap(new int[]{1,2,3,4,5,6,7,8,9});
        for(int i=0; i<arr.length; i++){
            System.out.print(removeMax(arr) + ", ");
        }
        System.out.print("\b\b\n");
        arr = createMaxheap(new int[]{1,1,1,1,1,2,2,2,2,2,2});
        for(int i=0; i<arr.length; i++){
            System.out.print(removeMax(arr) + ", ");
        }
        System.out.print("\b\b");
    }










}

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {


        quickSort(new int[]{9,7,5,4,3,2,1,0}, 0, 7);


    }


    static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            int p = partition(arr, low, high);

            System.out.println(Arrays.toString(arr));

            quickSort(arr, low, p-1);

            quickSort(arr, p+1, high);

        }

    }



    static int partition (int[] arr, int low, int high)

    {

        int pivot = arr[high];          //we are taking last element in array as pivot

        int i = (low - 1);                  // Index of smaller element

        for (int j = low; j <= high- 1; j++)

        {

            if (arr[j] < pivot)

            {

                i++;// increment index of smaller element
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }

        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;


        return (i + 1);

    }
}

package math;

import java.util.Scanner;
import java.util.Arrays;

public class PayMoney2 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the size of transaction array:");
        int transSize = sc.nextInt();
        int[] transArr = new int[transSize];

        System.out.println("enter the values of array");
        int sum = 0;
        for(int i=0; i<transSize; i++) {
        	sum += sc.nextInt();
        	transArr[i] = sum;
        }

        System.out.println("enter the total no of targets that needs to be achieved:");
        int targetSize = sc.nextInt();

        sum = 0;

        for(int i=0; i<targetSize; i++){
            System.out.println("enter the value of target");
            int targetVal = sc.nextInt();
            
            int pos = Arrays.binarySearch(transArr, targetVal);

           if(pos < 0 &&  -pos >= transArr.length) System.out.println("Given target is not achieved ");
           else	{
        	   if(pos < 0) System.out.println("Target achieved after "+ Math.abs(pos)+" transactions");
        	   else System.out.println("Target achieved after "+ (Math.abs(pos)+1)+" transactions");
           }

        }
    }
}

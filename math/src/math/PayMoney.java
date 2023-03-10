package math;

import java.util.Scanner;

public class PayMoney {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the size of transaction array:");
        int transSize = sc.nextInt();
        int[] transArr = new int[transSize];

        System.out.println("enter the values of array");
        for(int i=0; i<transSize; i++) transArr[i] = sc.nextInt();

        System.out.println("enter the total no of targets that needs to be achieved:");
        int targetSize = sc.nextInt();

        int sum = 0;

        for(int i=0; i<targetSize; i++){
            System.out.println("enter the value of target");
            int targetVal = sc.nextInt();
            boolean flag = false;
            for(int j=0; j<transSize; j++){
                sum += transArr[j];
                if(sum >= targetVal) {
                    System.out.println("Target achieved after "+ (j+1) +" transactions");
                    flag = true;
                    break;
                }
            }
            if(!flag) System.out.println("Given target is not achieved ");
            sum = 0;
        }
    }
}

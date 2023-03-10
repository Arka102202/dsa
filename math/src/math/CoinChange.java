package math;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CoinChange {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter the size of currency denominations :");
        int size = sc.nextInt();

        int[] denominations = new int[size];
        System.out.println("enter the currency denominations value:");
        for(int i=0; i<size; i++) denominations[i] = sc.nextInt();

//        denominations =  mergeSort(denominations);
        
        denominations = QuickSort.stage(denominations);

        System.out.println("enter the amount you want to pay:");
        int amount = sc.nextInt();

        if(amount == 0) {
        	System.out.println("you don't have use any denomination.");
        	return;
        }
        
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (int i=denominations.length-1; i>=0; i--){
            if(denominations[i] > 0) {
            	int j = amount /denominations[i];
                amount %= denominations[i];
                if(j > 0) map.put(denominations[i],j);
            }
        }

        if(amount == 0) {
            System.out.println("Your payment approach in order to give min no of notes will be");
            for(int i:map.keySet()){
                int c = map.get(i);
                if(c > 0 ) System.out.println(i+" : "+c);
            }
        }else System.out.println("you can't make payment with this denomination");

        sc.close();
    }

    public static int[] mergeSort(int[] arr){

        int middle = (arr.length%2 == 0)? (arr.length/2) : (arr.length+1)/2;

        int[] left  = new int[middle];
        int[] right = new int[(arr.length%2 == 0)? middle : (middle-1)];

        if(arr.length > 1){
            left  = mergeSort(Arrays.copyOfRange(arr,0,middle));
            right = mergeSort(Arrays.copyOfRange(arr,middle,arr.length));
        }

        if(arr.length == 1) return arr;
        else if (left.length == 1 && right.length == 1) {
            if(left[0] > right[0]) return new int[]{right[0],left[0]};
            else return new int[]{left[0],right[0]};
        }
        else if (left.length > 1 || right.length > 1) {
            int l = 0; int r = 0;
            int len = left.length+right.length;
            int[] newInt = new int[len];
            if(right[0] >= left[left.length-1] && right[right.length-1] > left[0]){

                for(int i=0; i<(len); i++){
                    if(l < left.length){
                        newInt[i] = left[l++];
                    }else{
                        newInt[i] = right[r++];
                    }
                }

            }
            else if (left[0] >= right[right.length - 1] && left[left.length - 1] > right[0]) {

                for(int i=0; i<(len); i++){
                    if(r < right.length){
                        newInt[i] = right[r++];
                    }else{
                        newInt[i] = left[l++];
                    }
                }
            }
            else{
                int valueL = 0; int valueR = 0;

                for(int i=0; i<(len); i++){
                    if( l < left.length){
                        valueL = left[l];
                    }
                    if(r < right.length){
                        valueR = right[r];
                    }
                    if(valueR > valueL && l <= left.length-1 ){
                        newInt[i] = valueL;
                        l++;
                    }else if(valueR <= valueL && r <= right.length-1){
                        newInt[i] = valueR;
                        r++;
                    }else if(r == right.length ){
                        newInt[i] = valueL;
                        l++;
                    }else if(l == left.length){
                        newInt[i] = valueR;
                        r++;
                    }
                }
            }
            return newInt;
        }
        else return new int[0];
    }

}

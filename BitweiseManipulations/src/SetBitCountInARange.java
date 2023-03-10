import java.util.HashMap;

public class SetBitCountInARange {

    static int step = 0;
    public static int setBitCount(int range){

//        base case 1:
        if(range == 1) return 1;
//        base case 2:
        if(range == 2) return 2;

        int count = 0;
        int i = 1, rangeBeforeChange = 0;
        boolean firstR = true, firstC = true;
        while(range > 0){
            rangeBeforeChange = range;
            if(firstR){
                range -= 3;
                firstR = false;
            }else{
                range -= 1<<(i);
            }
            if(range < 0){
                count += (setBitCount(rangeBeforeChange-1)+rangeBeforeChange);
            }else{
                if(firstC){
                    count = 4;
                    firstC = false;
                }else{
                    count += ((1<<i)+count);
                }
            }
            i++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(setBitCount(20000));
        int count = 0;
        for(int i=0; i<=20000; i++){
            count += Integer.bitCount(i);
        }
        System.out.println(count);
        System.out.println(step);
    }








}

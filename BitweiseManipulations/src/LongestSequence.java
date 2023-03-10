import java.util.ArrayList;
import java.util.List;

public class LongestSequence {


    static int longestSequence(int num){

        int count = 1, previousValue = -1, i = 0;
        List<Integer> oneZeroCount1stPass = new ArrayList<>();
        List<Integer> oneZeroCount2ndPass = new ArrayList<>();
        boolean first = true;

        while(num >= 1){
            int value = num&1;
            num >>= 1;
            if(previousValue != value){
                previousValue = value;
                if(!first) oneZeroCount1stPass.add(count);
                else first = false;
                count &= 0;
            }
            count++;
            i++;
        }
//        to add the last count:
        oneZeroCount1stPass.add(count);

        for(i=0; i<oneZeroCount1stPass.size(); i++){
            if((i&1) != 0){
                int tempCount = (oneZeroCount1stPass.get(i-1)+1);
                if(oneZeroCount1stPass.get(i) == 1){
                    if(i < oneZeroCount1stPass.size()-1) tempCount += oneZeroCount1stPass.get(i+1);
                    oneZeroCount2ndPass.add(tempCount);
                }else{
                    oneZeroCount2ndPass.add(tempCount);
                    tempCount = 0;
                    if(i < oneZeroCount1stPass.size()-1) tempCount += oneZeroCount1stPass.get(i+1)+1;
                    oneZeroCount2ndPass.add(tempCount);
                }
            }
        }


        int maxCount = 0;

        for(int j:oneZeroCount2ndPass){
            maxCount = j - ((j- maxCount) & ((j-maxCount) >> 31));
        }


        if(maxCount == 0) return oneZeroCount1stPass.get(0)+1;
        else return maxCount;
    }


    public static void main(String[] args) {
        System.out.println(longestSequence(1775));
    }





}

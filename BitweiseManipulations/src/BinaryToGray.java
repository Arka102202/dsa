import java.util.ArrayList;
import java.util.List;

public class BinaryToGray {

    public static List<Integer> grayCode(int n) {

        List<Integer> grayCode = new ArrayList<>();

        for(int i=0; i<(1<<n); i++){

            int j = i << 1;
            j =  i ^ j;
            j = j >> 1;
            grayCode.add(j);
        }

        return grayCode;
    }

    public static void main(String[] args) {
        System.out.println(grayCode(2));
    }
}

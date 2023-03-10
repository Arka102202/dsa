import java.util.*;

public class TestClass {

    static List<List<Integer>> combinations = new ArrayList<>();
    static int combinationsLength = 0;
    static int t1 = 0, filled1 = 0;
    static boolean flag = false;

    public static void main(String[] args) {


//        List<Integer> combination = new ArrayList<>();
//        int target = 3;
//        int t = 0;
//        int filled = 0;
//        int houses = 5;
//        int i = 0;
//        int[][] cost = new int[][]{{1,10},{10,1},{10,1},{1,10},{5,1}};
//        int n = 2;
//
//        while (t < target - 1) {
//            combination.add(i);
//            t++;
//            filled++;
//        }
//        combination.add(0);
//
//        t = t1 = filled = filled1 = 0;
//        createCombination(i, target, t, filled, houses, combination);
//        System.out.println(combinations);
//
//        int houseNumber = 0;
//        int colorNumber;
//        int c = 0;
//        int sum = 0;
//        TreeSet<Integer> sumSetLocal = new TreeSet<>();
//        TreeSet<Integer> sumSetGlobal = new TreeSet<>();
//        for(List<Integer> list:combinations){
//            for(colorNumber=0; colorNumber<n; colorNumber++){
//                c = colorNumber;
//                for(int k=0; k<target; k++){
//                    for(int l=0; l<list.get(k); l++){
//                        sum += cost[houseNumber][colorNumber];
//                        houseNumber++;
//                    }
//                    if(colorNumber == n-1){
//                        colorNumber =0;
//                    }else {
//                        colorNumber++;
//                    }
//                }
//                sumSetLocal.add(sum);
//                sum= 0;
//                houseNumber = 0;
//                colorNumber = c;
//            }
//            sumSetGlobal.add(sumSetLocal.first());
//        }
//
//
//        System.out.println(sumSetGlobal.first());


        String s = "123 -123";

        StringBuilder sb = new StringBuilder("0 ");

        Set<String> set = new HashSet<>();
        List<Integer> list = new ArrayList<>(List.of(1, 1, 1, 1));
        set.add(list.toString());
//        System.out.println(list.toString());

//        System.out.println(set.add(list.toString()));

        int[] arr = {1, 2, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 7};

        System.out.println("position = " + Arrays.binarySearch(arr, 4));


        int[] arr1 = new int[arr.length];

        int k = 1200;
        k -= (k / arr.length) * arr.length;
        int distance = arr.length - k;

        for (int i = 0; i < arr.length; i++) {
            if (distance >= arr.length) distance -= (distance / arr.length) * arr.length;
            arr1[i] = arr[distance];
            distance++;
        }
        arr = arr1;

        System.out.println(Arrays.toString(arr));


        String s1 = "nwae";
        int hashCode = s1.hashCode();
        System.out.println("anew".hashCode() == hashCode);


//        System.out.println(Arrays.binarySearch(arr,0));

//        System.out.println(412%14);


        int a = 1201, reverseA = 0;
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(Integer.rotateLeft(a,4)));


        for (int i = 0; i < 11; i++) {

            int c = a >> i;
            c = c & 1;
            reverseA += c << (10 - i);
        }

//        System.out.println(Integer.toBinaryString(reverseA));


        System.out.println(1 / 2);


        Integer[] i1 = {0, 1};
        Integer[] i2 = {2, 2};

//        System.out.println(i1^i2);

//
//        System.out.println("arka".hashCode());
//        System.out.println("mallika".hashCode());
//        System.out.println("arkaa".hashCode());
//
//
//        System.out.println(divide(Integer.MIN_VALUE, -1));

        System.out.println(Long.toUnsignedString(Long.MAX_VALUE,2));


        System.out.println(addBinary("1011000110101001000100111010001100011000001001100100111110111011110001001101101"
                ,"11110000011110100100010101011000111011000110000001000000100000100001001011011111010"));

//        BigInteger i = new BigInteger(Long.toBinaryString(Long.MAX_VALUE),2).add(new BigInteger(Long.toBinaryString(Long.MAX_VALUE),2));

//        System.out.println(i.toString(2));


    }

    public static String addBinary(String a, String b) {

        String old = "";
        int i = a.length()-1, j = b.length()-1;
        long sum = 0;
        for (i = a.length(), j = b.length(); i >= 31 && j >= 31; i-= 31, j-=31) {
            long q =  Long.parseUnsignedLong(a.substring((i-31),i),2);
            long p = Long.parseUnsignedLong(b.substring((j-31),j),2);
            sum += q + p;
            int k = Long.toBinaryString(sum).length();

            if(k-31 >= 0 ){
                old = Long.toBinaryString(sum).substring(k-31,k).concat(old);
                sum = Long.toBinaryString(sum).substring(0,k-31).equals("") ? 0:Long.parseLong(Long.toBinaryString(sum).substring(0,k-31)) ;
            }else {
                old = "0".repeat(31-k)+Long.toBinaryString(sum).substring(0,k).concat(old);
                sum = 0;
            }
        }
        i--; j--;

        int i1 = 3;
        for (; i >= i1 && j >= i1; i-= i1, j-= i1) {
            long q =  Long.parseUnsignedLong(a.substring((i- i1),i),2);
            long p = Long.parseUnsignedLong(b.substring((j- i1),j),2);
            sum += q + p;
            int k = Long.toBinaryString(sum).length();

            if(k- i1 >= 0 ){
                old = Long.toBinaryString(sum).substring(k- i1,k).concat(old);
                sum = Long.toBinaryString(sum).substring(0,k- i1).equals("") ? 0:Long.parseLong(Long.toBinaryString(sum).substring(0,k- i1)) ;
            }else {
                old = "0".repeat(i1 -k)+Long.toBinaryString(sum).substring(0,k).concat(old);
                sum = 0;
            }
        }
        i--; j--;
        for (; i >= 0 && j >= 0; i--, j--) {
            sum += (a.charAt(i)-48) + (b.charAt(j)-48);
            old = (sum % 2) + "".concat(old);
            sum = sum / 2;
        }

        while (i >= 0) {
            sum += (a.charAt(i)-48);
            old = (sum % 2) + "".concat(old);
            sum = sum / 2;
            i--;
        }
        while (j >= 0) {
            sum += (b.charAt(j)-48);
            old = (sum % 2) + "".concat(old);
            sum = sum / 2;
            j--;
        }

        if(sum > 0) {
            old = (sum%2) + "".concat(old);
            if(sum/2 > 0){
                old = (sum/2) + "".concat(old);
            }
        }


        return old;

    }


    public static int divide(int dividend, int divisor) {

        long q = ((long) dividend / (long) divisor);

        if (q > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if (q < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else return (dividend / divisor);


    }


    public static List<List<Integer>> createCombination(int i, int target, int t, int filled, int houses, List<Integer> combination) {

        for (int j = 1; j <= (houses - target + 1); j++) {
            combination = new ArrayList<>(combination);
            if (i >= target - 1) {
                break;
            } else if (filled != houses) {
                combination.set(i, j);
                t++;
                filled += j;
            }
            combinationsLength = combinations.size();
            int t1 = TestClass.t1;
            int filled1 = TestClass.filled1;
            TestClass.t1 = t;
            TestClass.filled1 = filled;
            createCombination(i + 1, target, t, filled, houses, combination);
            TestClass.t1 = t1;
            TestClass.filled1 = filled1;
            if (combinationsLength == combinations.size()) {
                TestClass.flag = true;
            }
            if (t >= target - 1 && houses - filled > 0) {
                combination.set(target - 1, houses - filled);
                combinations.add(combination);
                t = t1;
                filled = filled1;

            } else if (TestClass.flag) {
                TestClass.flag = false;
                t = t1;
                filled = filled1;
            } else {
                combinationsLength = combinations.size();
                break;
            }
        }
        List<List<Integer>> info = new ArrayList<>();
        info.add(combination);
        return info;
    }


}

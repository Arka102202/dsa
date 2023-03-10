import java.util.HashMap;

public class IntegerToRoman {
    static HashMap<Integer, String> symbolMap = new HashMap<>();
    static {
        symbolMap.put(1,"I");
        symbolMap.put(5,"V");
        symbolMap.put(10,"X");
        symbolMap.put(50,"L");
        symbolMap.put(100,"C");
        symbolMap.put(500,"D");
        symbolMap.put(1000,"M");
    }
    public static String intToRoman(int num) {
        String n = Integer.toString(num);
        int j = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=n.length()-1; i>=0; i--)sb = generate(n.charAt(i) - '0', j++).append(" ").append(sb);
        return new String(sb);
    }

    static StringBuilder generate(int digit, int position){

        int lowestValue = (int) Math.pow(10,position), midVal = lowestValue*5, highVal = midVal*2;
        StringBuilder sb = new StringBuilder();
        digit *= lowestValue;

        if(digit == highVal-lowestValue) sb.append(symbolMap.get(lowestValue)).append(symbolMap.get(highVal));
        else if (digit == midVal -lowestValue) sb.append(symbolMap.get(lowestValue)).append(symbolMap.get(midVal));
        else if (digit >= midVal){
            sb.append(symbolMap.get(midVal));
            int c = digit-midVal;
            for(int i=0; i<(c/lowestValue); i++) sb.append(symbolMap.get(lowestValue));
        }
        else {
            for(int i=0; i<(digit/lowestValue); i++) sb.append(symbolMap.get(lowestValue));
        }

        return sb;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3999; i++) {
            System.out.println(intToRoman(i));
        }
    }



}

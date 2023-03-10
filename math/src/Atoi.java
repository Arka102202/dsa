public class Atoi {
    public static int myAtoi(String s) {
        char sign = ' ';
        char[] chInString = s.toCharArray();
        int i;
        for(i=0; i<s.length(); i++){
            if(chInString[i] >= '0' && chInString[i] <= '9') {
                if ( i > 0 ) sign = chInString[i-1];
                break;
            }
            else if (chInString[i] != ' ' && chInString[i] != '+' && chInString[i] != '-' ) return 0;
            else if (chInString[i] == '+' || chInString[i] == '-' ) {
                if(i < s.length()-1 && !(chInString[i+1] >= '0' && chInString[i+1] <= '9'))
                    return 0;
            }
        }
        chInString = s.substring(i).toCharArray();
        sign = (sign == '-'?'-':'+');
        int num = 0, start = chInString.length-1, p = 0;
        for(i=0; i<chInString.length; i++){
            if(chInString[start-i] >= '0' && chInString[start-i] <= '9'){
                long newNum = (long) (chInString[start - i] - '0') * (int) Math.pow(10,p);
                if(sign == '+' && Integer.MAX_VALUE - num <= newNum) return Integer.MAX_VALUE;
                else if (-(long) Integer.MIN_VALUE - num <= newNum) return Integer.MIN_VALUE;
                num += newNum;
                p++;
            }
            else num = p = 0;
        }
        return sign == '-'? -num:num;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("122"));
    }
}

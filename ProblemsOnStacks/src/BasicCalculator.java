public class BasicCalculator {
    static int index;
    public static int calculate(String s) {
        index = 0;
        return temp(s.replaceAll(" ",""));
    }
    public static int temp(String s){
        Integer operand = null;
        int total = 0;
        boolean negate = false;
        for(int i = index; i<s.length(); i++){
            if(s.charAt(i) == '(') {
                index = i+1;
                operand = temp(s);
            }
            if(s.charAt(i) == ')') {
                index = i;
                break;
            }
            else {
                if (s.charAt(i) == '-') negate = true;
                else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    int start = i;
                    while (i<s.length() && s.charAt(i) != '-' && s.charAt(i) != '+' && s.charAt(i) != ')') i++;
                    int end = i;
                    operand = Integer.parseInt(s.substring(start,end));
                    i--;
                }
                if(operand != null && s.charAt(i) != '-' && s.charAt(i) != '+'){
                    if (!negate) total += operand;
                    else total -= operand;
                    negate = false;
                }
                if(s.charAt(i) == '(') i = index;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("s o".replaceAll(" ",""));

        System.out.println(calculate("2134234543"));
        System.out.println(calculate("1+1"));
    }

}

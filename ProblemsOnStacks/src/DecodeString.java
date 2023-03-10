public class DecodeString {
    static int index = 0;
    public static String decodeString(String s) {
        index = 0;
        return processRecursively(s);
    }
    static String processRecursively(String s){
        String decodedString = "";
        String num = "";
        for(int i=index; i<s.length(); i++){
            if(s.charAt(i) > '0' && s.charAt(i) <= '9') {
                while(s.charAt(i) != '[') {
                    num = num.concat(Character.toString(s.charAt(i)));
                    i++;
                }
                int n = Integer.parseInt(num);
                index = i+1;
                String s1 = processRecursively(s);
                decodedString = decodedString.concat(s1.repeat(n));
                num = "";
                i = index;
            }
            else if(s.charAt(i) == ']') {
                index = i;
                return decodedString;
            }
            else decodedString = decodedString.concat(Character.toString(s.charAt(i)));
        }
        return decodedString;
    }

    public static void main(String[] args) {
        System.out.println(decodeString("4[a2[bcf3[d]2[a]]ef]").equals("abcfdddaabcfdddaaefabcfdddaabcfdddaaefabcfdddaabcfdddaaefabcfdddaabcfdddaaef"));
        System.out.println(decodeString("3321[a2[c]]").equals("acc".repeat(3321)));
        System.out.println(decodeString("2[abc]3[cd]ef").equals("abcabccdcdcdef"));
    }



}

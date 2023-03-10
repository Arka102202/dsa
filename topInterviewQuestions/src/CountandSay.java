public class CountandSay {

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        StringBuilder sb = new StringBuilder();
        if (n > 1){
            String s = countAndSay(n-1);
            char temp = s.charAt(0);
            int count = 1;
            for(int i=1; i<s.length(); i++){
                if (temp != s.charAt(i)){
                    sb.append(count).append(temp);
                    temp = s.charAt(i);
                    count = 1;
                }else count++;
            }
            sb.append(count).append(temp);
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        for(int i = 1; i<50; i++){
            System.out.println(i +" --> "+countAndSay(i));
        }
    }

}

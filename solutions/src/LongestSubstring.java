public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcabcdd"));
    }
    public static int lengthOfLongestSubstring(String s) {

        if(s.isBlank() && !s.isEmpty()){
            return 1;
        }else if(s.isEmpty()){
            return 0;
        }
        else{
            int len = 0;
            int[] info = new int[256];
            int[] lenArr = new int[256];
            for(int i=0; i<256; i++){
                info[i]=lenArr[i] = -1;
            }
            int leftPointer = 0;
            int highestLength = -1;
            for(int i=0; i<s.length(); i++){
                int f = info[s.charAt(i)];
                if (f != -1 && (f > leftPointer || leftPointer == 0)) {
                    if(highestLength < len){
                        highestLength = len;
                    }
                    len = i - info[s.charAt(i)]-1;
                    leftPointer = info[s.charAt(i)];
                }
                info[s.charAt(i)] = i;
                len++;
            }
            if(highestLength < len){
                highestLength = len;
            }
            return highestLength;

        }
    }
}
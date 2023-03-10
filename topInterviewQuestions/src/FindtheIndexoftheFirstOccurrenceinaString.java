public class FindtheIndexoftheFirstOccurrenceinaString {
    public static int strStr(String haystack, String needle) {
        int[] preFixArr = createPrefixArr(needle);
        int i=0, j=0, count = 0;
        while (i<haystack.length()){
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length()-1) return i-j;
                else {
                    i++; j++;
                }
            }
            else if (j>0) j = preFixArr[j-1];
            else i++;
        }
        return -1;
    }
    static int[] createPrefixArr(String needle){
        int[] preFixArr = new int[needle.length()];
        preFixArr[0] = 0;
        int i = 1, j = 0;
        while (i < needle.length()){
            if (needle.charAt(i) == needle.charAt(j)) preFixArr[i++] = j++ +1;
            else if (j>0) j = preFixArr[j-1];
            else preFixArr[i++] = 0;
        }
        return preFixArr;
    }

    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad","tSad"));
    }

}

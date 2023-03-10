import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class RemoveKDigits {
    static HashMap<Integer, kString> memo = new HashMap<>();
    static int len, k1 = 0;
    public static String removeKdigits(String num, int k) {
        len = num.length() - k;
        StringBuilder list1 = findCombinationOfMinValue(num);
        System.out.println(list1);
        StringBuilder list = dp(num,len,0);
        boolean nonZero = false;
        String s = "";
        for (int i = 0; i < list.length(); i++) {
            if (!nonZero && list.charAt(i) != '0') {
                s = s.concat(Character.toString(list.charAt(i)));
                nonZero = true;
            } else if (nonZero) {
                s = s.concat(new String(list).substring(i));
                break;
            }
        }
        return s.length() == 0 ? "0" : s;
    }
    static StringBuilder dp(String nums, int k, int index) {
        TreeMap<StringBuilder, Integer> set = new TreeMap<>(StringBuilder::compareTo);
        if(k == 0) return new StringBuilder();
        if(memo.containsKey(nums.substring(index).hashCode())){
            if(memo.get(nums.substring(index).hashCode()).k == k)  return memo.get(nums.substring(index).hashCode()).value;
        }
        for(int i=index; i<nums.length() && (nums.length() - i) >= k; i++){
            StringBuilder sb = new StringBuilder();
            sb.append(nums.charAt(i));
            if(k > 1) sb.append(dp(nums, k-1, i+1));
            set.put(sb,i);
        }
        StringBuilder r = set.firstKey();
        memo.put(nums.substring(index).hashCode(), new kString(k,r));
        return r;
    }

    public static void main(String[] args) {

        System.out.println(removeKdigits("1123337220604317046062306050609124630638654395168712355717850880691219901011187111233372206043170460623060506091246306386543951687123557178508806912199010111871", 50));
    }
    static StringBuilder findCombinationOfMinValue(String nums) {
        int minNum = Integer.MAX_VALUE;
        int index = 0, nextIndex = 0, j;
        StringBuilder list = new StringBuilder();
        int lowestValue = Integer.MAX_VALUE;
        for(char ch:nums.toCharArray()) if((ch-'0' < lowestValue)) lowestValue = ch-'0';
        while (list.length() < len) {
            for (j = index; j < nums.length(); j++) {
                if ((nums.length() - j + list.length()) >= len && (nums.charAt(j) - '0') < minNum) {
                    minNum = (nums.charAt(j) - '0');
                    nextIndex = j;
                    if(minNum == lowestValue) break;
                } else if (((nums.length() - j + list.length())) < len) {
                    if (minNum == Integer.MAX_VALUE) {
                        minNum = (nums.charAt(j) - '0');
                        nextIndex = j;
                    }
                    break;
                }
            }
            list.append(minNum);
            index = nextIndex + 1;
            minNum = Integer.MAX_VALUE;
        }
        return list;
    }

    static LinkedList<Integer> findCombinationOfMinValue(int[] nums) {
        int maxNum = Integer.MAX_VALUE;
        int index = 0, nextIndex = 0;
        LinkedList<Integer> list = new LinkedList<>();
        while (list.size() < len && len <= nums.length) {
            for (int j = index; j < nums.length; j++) {
                if ((nums.length - j + list.size()) >= len && nums[j] < maxNum) {
                    maxNum = nums[j];
                    nextIndex = j;
                } else if (((nums.length - j + list.size())) < len) {
                    if (maxNum == Integer.MAX_VALUE) {
                        maxNum = nums[j];
                        nextIndex = j;
                    }
                    break;
                }
            }
            list.add(maxNum);
            index = nextIndex + 1;
            maxNum = Integer.MAX_VALUE;
        }
        return list;
    }
}


class  kString {


    int k;
    StringBuilder value;

    public kString(int k, StringBuilder value) {
        this.k = k;
        this.value = value;
    }
}
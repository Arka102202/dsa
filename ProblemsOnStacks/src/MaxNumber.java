import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class MaxNumber {
    static LinkedList<LinkedList<Integer>> stack1 = new LinkedList<>();
    static LinkedList<LinkedList<Integer>> stack2 = new LinkedList<>();
    static  int arrValue = 0, rIndex = -1, lIndex = -1;
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        stack1.clear(); stack2.clear();
        int index = 0;
        StringBuilder s1 = new StringBuilder();
        String s2 = null;
        if(nums1.length + nums2.length == k){
            LinkedList<Integer> list1 = new LinkedList<>();
            LinkedList<Integer> list2 = new LinkedList<>();
            for(int i=0; i<Math.max(nums1.length, nums2.length); i++){
                if(i < nums1.length) list1.add(nums1[i]);
                if(i < nums2.length) list2.add(nums2[i]);
            }
            stack1.add(list1);
            stack2.add(list2);
        }
        else {
            findCombinationOfMaxValue(nums1, k, stack1);
            findCombinationOfMaxValue(nums2, k, stack2);
        }
        LinkedList<int[]> arrList = new LinkedList<>();
        int arrIndex = -1;

        for (int i = 0; i < stack1.size(); i++) {

            LinkedList<Integer> left = stack1.get(i);
            LinkedList<Integer> right = stack2.get((stack2.size() - 1) - i);
            int[] arr = new int[k];
            if (left.size() + right.size() == k) {
                int r = 0, l = 0;
                for (int j = 0; j < k; j++) {
                    if (r < right.size() && l < left.size()) {
                        if (left.get(l) > right.get(r)) {
                            arr[j] = left.get(l);
                            l++;
                        }
                        else if (Objects.equals(left.get(l), right.get(r))){
                            breakTie(l,r,right,left);
                            if(rIndex != -1) {
                                arr[j] = right.get(r);
                                r++;
                            }else {
                                arr[j] = left.get(l);
                                l++;
                            }
                            rIndex = -1; lIndex = -1;
                        }
                        else {
                            arr[j] = right.get(r);
                            r++;
                        }
                    }
                    else if (r == right.size() && l < left.size()) {
                        arr[j] = left.get(l);
                        l++;

                    } else if (l == left.size() && r < right.size()) {
                        arr[j] = right.get(r);
                        r++;
                    }
                    s1.append(arr[j]);
                }
                arrList.add(arr);
                if(s2 == null) {
                    s2 = new String(s1);
                    arrIndex = index;
                }
                else {
                    if(s2.compareTo(new String(s1)) < 0){
                        s2 = new String(s1);
                        arrIndex = index;
                    }
                }
                index++;
                s1 = new StringBuilder();
            }
        }


        return arrList.get(arrIndex);

    }


    static void findCombinationOfMaxValue(int[] nums, int k, LinkedList<LinkedList<Integer>> stack) {
        int maxNum = -1;
        int index = 0, nextIndex = 0;
        for (int len = 0; len <= k; len++) {
            LinkedList<Integer> list = new LinkedList<>();
            while (list.size() < len && len <= nums.length) {
                for (int j = index; j < nums.length; j++) {
                    if ((nums.length - j + list.size()) >= len && nums[j] > maxNum) {
                        maxNum = nums[j];
                        nextIndex = j;
                    } else if (((nums.length - j + list.size())) < len) {
                        if (maxNum == -1) {
                            maxNum = nums[j];
                            nextIndex = j;
                        }
                        break;
                    }
                }
                list.add(maxNum);
                index = nextIndex + 1;
                maxNum = -1;
            }
            stack.add(new LinkedList<>(list));
            index = 0;
        }
    }


    static void breakTie(int tempL, int tempR, LinkedList<Integer> right, LinkedList<Integer> left){

        if(tempR < right.size()-1 && tempL < left.size()-1){
            while(tempL < left.size() && tempR < right.size()){
                if(right.get(tempR) > left.get(tempL)){
                    arrValue = right.get(tempR);
                    rIndex = tempR;
                    break;
                }else if (right.get(tempR) < left.get(tempL)){
                    arrValue = left.get(tempL);
                    lIndex = tempL;
                    break;
                }
                tempL++; tempR++;
            }
        }

        else if (tempR == right.size()-1 && tempL < left.size()-1){
            while (tempL < left.size()-1){
                if(right.get(tempR) > left.get(tempL+1)){
                    arrValue = right.get(tempR);
                    rIndex = tempR;
                    break;
                }else if (right.get(tempR) < left.get(tempL+1)) {
                    arrValue = left.get(tempL);
                    lIndex = tempL;
                    break;
                }
                tempL++;
            }

        }

        else if(tempR < right.size()-1 && tempL == left.size()-1){
            while (tempR < right.size()-1){
                if(right.get(tempR+1) > left.get(tempL)){
                    arrValue = right.get(tempR);
                    rIndex = tempR;
                    break;
                }else if(right.get(tempR+1) < left.get(tempL)) {
                    arrValue = left.get(tempL);
                    lIndex = tempL;
                    break;
                }
                tempR++;
            }
        }
    }
    public static void main(String[] args) {

//        System.out.println(Arrays.toString(maxNumber(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                100)));
//
//        System.out.println(Arrays.toString(maxNumber(new int[]{5,0,2,1,0,1,0,3,9,1,2,8,0,9,8,1,4,7,3}, new int[]{7,6,7,1,0,1,0,5,6,0,5,0}, 31)));
        System.out.println(Arrays.toString(maxNumber(new int[]{6,7}, new int[]{6,0,4}, 5)));


    }
}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RainWaterTrap {
    public static int trap(int[] height) {
        int index = -1, leftBoundary = 0, rightBoundary = 0, totalAmount = 0, changed = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        if(height.length > 1){
            for (int i = 0; i < height.length; i++) {
                if (height[i] >= leftBoundary) {
                    if (rightBoundary != 0) rightBoundary = 0;
                    while (stack.size() != 0) {
                        int value = stack.poll();
                        if (value <= leftBoundary) totalAmount += (leftBoundary - value);
                    }
                    leftBoundary = height[i];
                    changed++;
                } else if (height[i] > rightBoundary && height[i] <= leftBoundary) {
                    rightBoundary = height[i];
                    index = i;
                }
                stack.push(height[i]);
            }
            if (stack.size() != 0) {
                if(changed > 1){
                    index -= (height.length - stack.size());
                }
                List<Integer> list = new ArrayList<>();
                while(index >= 0 && stack.size() > (index)) list.add(stack.poll());
                int[] arr = new int[list.size()];
                for(int i=0; i<list.size(); i++) arr[i] = list.get(i);
                totalAmount += trap(arr);
                while (stack.size() != 0) {
                    int value = stack.poll();
                    if (value <= rightBoundary) totalAmount += (rightBoundary - value);
                }
            }
        }
        return totalAmount;
    }
    public static void main(String[] args) {
        System.out.println("test 1 ==> "+trap(new int[]{6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3}));
        System.out.println("test 2 ==> "+trap(new int[]{2,6,3,8,2,7,2,5,0}));
        System.out.println("test 3 ==> "+trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println("test 4 ==> "+trap(new int[]{4,2,0,3,1,2}));
        System.out.println("test 5 ==> "+trap(new int[]{4,2,0,3,2,5}));
        System.out.println("test 6 ==> "+trap(new int[]{4,2,0,4,1,2}));
        System.out.println("test 7 ==> "+trap(new int[]{2,0,2}));
        System.out.println("test 8 ==> "+trap(new int[]{0,2,2}));
    }
}

import java.util.LinkedList;

public class LargestRectangleArea {
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int area;
        int count = 0;
        int previousH = -1;
        LinkedList<Integer> stackH = new LinkedList<>();
        LinkedList<Integer> stackC = new LinkedList<>();
        boolean oneMore = false;

        for (int i = 0; i < heights.length; i++) {

            int tempHeight = heights[i];
            if (tempHeight != 0) {
                count++;
                while (i < heights.length - 1 && tempHeight == heights[i + 1]) {
                    count++;
                    i++;
                }

                if (stackH.size() > 0 && stackH.peek() > tempHeight) {
                    for (int j = 0; j < stackH.size(); j++) {
                        if (tempHeight <= stackH.get(j)) {
                            if (previousH != -1 && previousH <= stackH.get(j)) {
                                if (j < stackH.size() - 1 && previousH <= stackH.get(j + 1)) {
                                    break;
                                } else if (j < stackH.size() - 1 && previousH > stackH.get(j + 1)) oneMore = true;
                            } else count += stackC.get(j);
                        } else break;
                        if (!oneMore) previousH = stackH.get(j);
                        oneMore = false;
                    }

                }
                previousH = -1;
                stackC.push(count);

                for (int j = i + 1; j < heights.length; j++) {
                    if (tempHeight <= heights[j]) count++;
                    else break;
                }
                area = count * tempHeight;
                if (area > maxArea) maxArea = area;
                count = 0;
                stackH.push(tempHeight);
            } else {
                stackH.clear();
                stackC.clear();
            }


        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{3,2,5,6,1,4,4}));
        System.out.println(largestRectangleArea(new int[]{9,6,2,5,4,9,5,3,8}));
        System.out.println(largestRectangleArea(new int[]{3, 5, 5, 2, 5, 5, 6, 6, 4, 4, 1, 1, 2, 5, 5, 6, 6, 4, 1, 3}));
        System.out.println(largestRectangleArea(new int[]{11, 11, 13, 5, 3, 11, 14, 8, 18, 9, 6, 5, 19, 17, 8, 7, 8, 3, 0, 18}));
        System.out.println(largestRectangleArea(new int[]{9, 8, 4, 9, 2, 6, 9, 0, 5, 4, 9, 5, 3, 8, 2, 9}));
        System.out.println(largestRectangleArea(new int[]{1, 1}));
        System.out.println(largestRectangleArea(new int[]{6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3}));
        System.out.println(largestRectangleArea(new int[]{3, 6, 5, 7, 4, 8, 1, 0}));
        System.out.println(largestRectangleArea(new int[]{10, 9, 8, 7, 6, 1, 2, 3, 4, 5}));
        System.out.println(largestRectangleArea(new int[]{4, 2, 0, 3, 2, 4, 3, 4})); // 10
        System.out.println(largestRectangleArea(new int[]{5, 4, 1, 2})); // 8
        System.out.println(largestRectangleArea(new int[]{2, 1, 2}));
        System.out.println(largestRectangleArea(new int[]{2, 4}));
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 6, 7, 8, 10}));
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }


}

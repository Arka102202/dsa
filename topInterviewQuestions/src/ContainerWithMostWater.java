public class ContainerWithMostWater {


    public static int maxArea(int[] height) {

        int leftLen, rightLen, area = 0;

        for (int i = 0, j = height.length - 1; i < j; ) {

            leftLen = height[i];
            rightLen = height[j];
            int area1 = Math.abs(j - i) * Math.min(leftLen, rightLen);
            if (area1 > area)
                area = area1;
            if (leftLen < rightLen) i++;
            else j--;
        }

        return area;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{0,1,0}));
    }


}

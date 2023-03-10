import java.util.Arrays;

public class GetCollisionTimes {
    public static double[] getCollisionTimes(int[][] cars) {

        return new double[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getCollisionTimes(new int[][]{{1, 2}, {11, 4}, {13, 1}, {18, 5}, {19, 2}, {20, 4}})));
        System.out.println(Arrays.toString(getCollisionTimes(new int[][]{{3, 4}, {5, 4}, {6, 3}, {9, 1}})));
        System.out.println(Arrays.toString(getCollisionTimes(new int[][]{{1,2}, {2,1}, {4,3}, {7,2}})));
    }

}


class SpeedIndex{

    double speed;
    int index;

    public SpeedIndex(double speed, int index) {
        this.speed = speed;
        this.index = index;
    }
}























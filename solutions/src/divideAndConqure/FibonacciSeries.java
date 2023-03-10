package divideAndConqure;

public class FibonacciSeries {

    public static void main(String[] args) {
        int n = 4;
        map = new double[n+1];
        System.out.println(findTerm(n));
    }

    static  double[] map;
    public static double findTerm(int n){

        if(n==1 || n==2){
            map[n] = 1.0;
            return 1.0;
        }
        if(map[n] != 0){
            return map[n];
        }else{
            double result = findTerm(n-1)+findTerm(n-2);
            map[n] = result;
            return result;
        }

    }




}

public class RangeBitwiseAnd {

    public static int rangeBitwiseAnd(int left, int right) {

        int num = 0;
        if (left == 0) {
            return 0;
        }
        if (left == 1 && right != 1) {
            return 0;
        }
        if (left == right) {
            return left;
        }

        long i = 1;
        while (i <= 32) {

            if ((left >= (1 << i)) && (right <= (1 << (i + 1)) - 1)) {

                if ((left == (1 << i) || left == (1 << i) + 1) && right == (1 << (i + 1)) - 1) {
                    return (1 << i);
                } else{
                    num = calculateAND(left, right);
                    return num;
                }
            }


            i++;


        }



//        num = calculateAND(left, right);

        return num;
    }

    public static int calculateAND(int left, int right) {

        int num = 2147483647;

        for (long i = left; i <= right; i += 1) {
            num &= i;
        }
        return num;
    }

    public static void main(String[] args) {

        System.out.println(rangeBitwiseAnd(2000, 2147483647));
    }

}

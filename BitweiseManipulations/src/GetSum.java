public class GetSum {

    public static int getSum(int a, int b) {

        int carry = 0;
        int sum, i = 0, addedNumber = 0;
        int[] bits = new int[32];

        while(Math.abs(a) >=1 & Math.abs(b)>= 1){
            int addend1 = a&1;
            int addend2 = b&1;
            a = a >> 1;
            b = b >> 1;
            sum = addend1^addend2^carry;
            carry = (addend1&addend2) | (addend1&carry) | (addend2&carry);
            bits[i] = sum;
            i++;
            if(i > 31) break;
        }

        if(Math.abs(a) >= 1 & i<= 31){
            while(Math.abs(a) >=1){
                int addend1 = a & 1;
                a = a >> 1;
                bits[i] = addend1^carry;
                carry = addend1&carry;
                i++;
                if(i > 31) break;
            }
        }
        else if(Math.abs(b) >= 1 & i<= 31){
            while(Math.abs(b) >=1){
                int addend2 = b & 1;
                b = b >> 1;
                bits[i] = addend2^carry;
                carry = addend2&carry;
                i++;
                if(i > 31) break;
            }
        }
        if(i<=31) bits[i] = carry;

        for(i = 31; i>=0; i--)
            addedNumber |= bits[i] << i;

        return addedNumber;

    }


    public static void main(String[] args) {
        System.out.println(getSum(0,-1));
        System.out.println(~(-23));
    }
}

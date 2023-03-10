import java.util.Arrays;

public class TestClass {


    static final int CHAR_BIT = 8;
    static final int SIZE_INT = 32;

    /* This function will return absoulte value of n*/
    static int getAbs(int n)
    {
        int mask = n >> (SIZE_INT * CHAR_BIT - 1);
        System.out.println(Integer.valueOf(0b1111111));
        System.out.println(Integer.toBinaryString((n + mask)));
        return ((n + mask) ^ mask);
    }

    public static void main(String[] args) {

        System.out.println(getAbs(~(-1)));
        System.out.println((-14+(-14>>31))^(1<<31));
        System.out.println(~22);
        int a = 20, o = 8;
        int i2 = (o - a) & ((o - a) >> 31);
        int small = a + i2;
        int large = o - i2;
        System.out.println(small+""+large);
//        -15 ^ 0xFFFFFFFF


//        turning of the right most bit:
        int i = Integer.MAX_VALUE;
        int compensatingValue = i & -i;
//        System.out.println(i^compensatingValue);

//        given number is power of 4 or not
        int num = 16;
        int remainder = num & 3;
        System.out.println(remainder == 0);
        System.out.println(num>>2);

//        rotate the bites of a number
        int rotationDistance = 4;
        byte b = -100;
        System.out.println(Integer.toBinaryString(b));
//        System.out.println(Integer.toBinaryString(b << 4));
//        System.out.println(Integer.toBinaryString(b >> 8-4));
        int rotatedNum = (b << rotationDistance) | (b >> (8-4));
        System.out.println(Integer.toBinaryString(rotatedNum));

        int[] arr = {1, 2, 3, 2, 3, 1, 3};
        int XOR = 0;
        for(int j:arr){
            XOR ^= j;
        }
        System.out.println(XOR);

        System.out.println(Integer.MAX_VALUE+Integer.MAX_VALUE+Integer.MAX_VALUE);

        System.out.println(Integer.bitCount(10^20));
        i = 10^20;
//        System.out.println(Integer.toBinaryString(i));
//        i = i - ((i >>> 1) & 0x55555555);
//        System.out.println(Integer.toBinaryString((10^20)>>>1));
//        System.out.println(Integer.toBinaryString(0x55555555));
//        System.out.println(Integer.toBinaryString(i));
//        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
//
//        System.out.println(Integer.toBinaryString(i));
//        i = (i + (i >>> 4)) & 0x0f0f0f0f;
//        System.out.println(Integer.toBinaryString(i));
//        i = i + (i >>> 8);
//        System.out.println(Integer.toBinaryString(i));
//        i = i + (i >>> 16);
//        System.out.println(Integer.toBinaryString(i));
//        int count =  i & 0x3f;
//        System.out.println(Integer.toBinaryString(count));


        System.out.println((int)(Math.log((double) (16&(-15)))/Math.log(2.0)));


        num = 250;
        System.out.println("num = "+Integer.toBinaryString(num));

        i = (num>>4) & 0x000F;
        System.out.println(Integer.toBinaryString(i));
        num = (num << 4) & 0x00F0 ;
        System.out.println(Integer.toBinaryString(num));
        num = num^i;
        System.out.println(Integer.toBinaryString(num));

        System.out.println(15&0x000D);
        System.out.println(28^0x1B);
        int CHAR_BIT =32;
        int x = 124,y=122;
        int j = x - ((x - y) & ((x - y) >> (CHAR_BIT - 1)));
        System.out.println("small = " +j);
        arr = new int[]{0,1};
        for(int k=0; k<2; k++) arr[k] &= 0;
        System.out.println(Arrays.toString(arr));

        int v = 3;
        System.out.println(Integer.toBinaryString(v));
        // swap odd and even bits
        v = ((v >>> 1) & 0x55555555) | ((v & 0x55555555) << 1);
// swap consecutive pairs
        v = ((v >> 2) & 0x33333333) | ((v & 0x33333333) << 2);
// swap nibbles ...
        v = ((v >> 4) & 0x0F0F0F0F) | ((v & 0x0F0F0F0F) << 4);
// swap bytes
        v = ((v >> 8) & 0x00FF00FF) | ((v & 0x00FF00FF) << 8);
// swap 2-byte long pairs
        v = ( v >> 16             ) | ( v               << 16);
        System.out.println(Integer.toBinaryString(v));
        System.out.println("reversing the number = " + v );
        System.out.println(Integer.reverse(3));

        i = 3;
        System.out.println(Integer.toBinaryString(i));
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        System.out.println(Integer.toBinaryString(i));
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        System.out.println(Integer.toBinaryString(i));
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        System.out.println(Integer.toBinaryString(i)+"     "+i);
        v= 17;
        int n =1;
        for(i=1; (v>>i)!= 0; ++i){
            n = 1<<i;
        }

        System.out.println(n);


        int value = 5;
        int magicNumber = 0;
        for(i=1; value > 0; i++){

            magicNumber += (Math.pow(5,i) * (value%2));
            value /= 2;
        }
        System.out.println(magicNumber);


        num = 1;
        int p1 = 2, p2 = 3;
        int rest = p2 - (p2 -p1);
        num <<= (p2 - p1);
        num +=1;
        num <<= rest;
        i = 28;
        System.out.println(i^num);

        num = 16;
        System.out.println(square(num));

        i = 11; v = 10;

        small = i - ((v-i) & ((v-i)>>31));
        System.out.println("s"+small);



//        to find parity
        v=0b11_0001_1001_1001_1001_1101;
        v = (v >>> 16) ^ (v & 0x0000FFFF);
        v = (v >>> 8 ) ^ (v & 0x000000FF);
        v = (v >>> 4) ^ (v & 0xF);
        int parity = v;
        System.out.println("parity = "+parity);


        System.out.println("ssaa".regionMatches(0,"ss",0,4));




    }
// if there is an over-flow:
    int addOvf(int result, int a, int b)
    {
     result = a + b;
        if(a > 0 && b > 0 && result < 0) return -1;
        if(a < 0 && b < 0 && result > 0) return -1;
        return 0;
    }

//    reverseBit
    static int reverseBit(int n){
        int[] replacementArr = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};
        int a,b,c,d;
        a = replacementArr[n&0xF];
        b = replacementArr[(n>>4)&0xF0];
        c = replacementArr[(n>>8)&0xF0];



    return 0;
    }

    static int square(int n){
        int s = 0;
        if(n == 0) return 0;
        if((n&1) == 0) // x^2 = square(x>>1)<<2;
            s = square(n>>1)<<2;
        else s = (square(n>>1)<<2) + ((n>>1)<<2)+1;
        return s;
    }
}




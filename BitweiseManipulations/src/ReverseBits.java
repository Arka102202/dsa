public class ReverseBits {


    public static int reverseBits(int n) {
        n = (n >>> 1) & 0x5555_5555 | (n & 0x5555_5555) << 1;
        n = (n >>> 2) & 0x3333_3333 | (n & 0x3333_3333) << 2;
        n = (n >>> 4) & 0x0F0F_0F0F | (n & 0x0F0F_0F0F) << 4;
        n = (n >>> 8) & 0x00FF_00FF | (n & 0x00FF_00FF) << 8;
        n = (n >>> 16) & 0x0000_FFFF | (n & 0x0000_FFFF) << 16;

        return n;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(0b00000010100101000001111010011100));
        System.out.println(Integer.reverse(0b00000010100101000001111010011100));
    }

}

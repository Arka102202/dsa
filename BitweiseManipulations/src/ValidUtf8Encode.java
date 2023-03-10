public class ValidUtf8Encode {


    static int filter1 = 0b0;
    static int filter2 = 0b110;
    static int filter3 = 0b1110;
    static int filter4 = 0b11110;

    static int generalFilter = 0b10;


    public static boolean validUtf8(int[] data) {


        for(int i=0; i<data.length; i++){

            int value = data[i];
            if(isMatch(value,1)) continue;
            if (isMatch(value,2)) {
                if(i < data.length-1 && isMatch(data[i+1],0)) {
                    i++;
                }else return false;
            } else if (isMatch(value,3)) {
                if(i < data.length-1 && isMatch(data[i+1],0)) {
                    if(i < data.length-2 && isMatch(data[i+2],0)){
                        i+=2;
                    }else return false;
                }else return false;
            }else if(isMatch(value,4)) {
                if(i < data.length-1 && isMatch(data[i+1],0)) {
                    if(i < data.length-2 && isMatch(data[i+2],0)){
                        if(i < data.length-3 && isMatch(data[i+3],0)){
                            i+=3;
                        }else return false;
                    }else return false;
                }else return false;
            }else return false;


        }

        return true;
    }


    static boolean isMatch(int value, int filterNumber){
        switch (filterNumber){
            case 1:
                return (value >>> 7) == filter1 && !((value >>> 5) == filter2) && !((value >>> 4) == filter3) && !((value >>> 3) == filter4) && !((value >>> 6) == generalFilter);
            case 2:
                return (value >>> 5) == filter2 && !((value >>> 7) == filter1) && !((value >>> 4) == filter3) && !((value >>> 3) == filter4) && !((value >>> 6) == generalFilter);
            case 3:
                return (value >>> 4) == filter3 && !((value >>> 5) == filter2) && !((value >>> 7) == filter1) && !((value >>> 3) == filter4) && !((value >>> 6) == generalFilter);
            case 4:
                return (value >>> 3) == filter4 && !((value >>> 5) == filter2) && !((value >>> 4) == filter3) && !((value >>> 7) == filter1) && !((value >>> 6) == generalFilter);
            default:
                return (value >>> 6) == generalFilter && !((value >>> 5) == filter2) && !((value >>> 4) == filter3) && !((value >>> 3) == filter4) && !((value >>> 7) == filter1);
        }
    }


    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{248,130,130,130}));
        System.out.println(Integer.toBinaryString(248));
    }


}

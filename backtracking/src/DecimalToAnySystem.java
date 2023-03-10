public class DecimalToAnySystem {

    static void convert(int num){
        int decimal_ip = num, counter2, counter1, counter3, radix = 16;
        System.out.println("converted num :");
        for ( counter1 = 0; Math.pow(radix,counter1) < decimal_ip || Math.pow(radix,counter1) == decimal_ip; counter1++){}
        for ( counter2 = counter1 - 1; counter2 >= 0; counter2--){
            for( counter3 = 0; counter3 <= radix; counter3++){
                if( decimal_ip < Math.pow(radix, counter2) * counter3){
                    System.out.printf("%d ",(counter3 - 1));
                    decimal_ip -= ( Math.pow(radix, counter2)*(counter3 - 1) );
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        convert(898975643);
    }


}

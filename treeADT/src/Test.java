import java.util.*;

public class Test {

    public static void main(String[] args) throws Throwable{

        Scanner sc = new Scanner(System.in);

    }


    private static void m() throws UsageException{
        throw new UsageException("haven't used properly");
    }


}

class UsageException extends Exception{

    public UsageException(String message){
        super(message);
    }
}
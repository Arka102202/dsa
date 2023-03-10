import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Password {
    public static String generatePassword(){

        String s = "";
        Stream<Character> chStream =  Stream.generate(() -> (char)(new Random().nextInt(9)+48))
                .limit(3);

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(10)+33))
                .limit(1));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(25)+97))
                .limit(2));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(25)+65))
                .limit(1));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(6)+58))
                .limit(1));

        chStream =  Stream.concat(chStream,Stream.generate(() -> (char)(new Random().nextInt(9)+48))
                .limit(3));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(10)+33))
                .limit(1));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(25)+97))
                .limit(2));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(25)+65))
                .limit(1));

        chStream = Stream.concat(chStream, Stream.generate(() -> (char)(new Random().nextInt(6)+58))
                .limit(1));

        List<Character> list = chStream.collect(Collectors.toList());

        Collections.shuffle(list);
        for(char ch:list)s = s.concat(Character.toString(ch));

        return s;
    }


    public static void writePassword(String type, String info) throws IOException {
        try(BufferedWriter b = new BufferedWriter(new FileWriter(type+".txt"))){
            b.write(info + " ==> " + generatePassword());
        }
    }

    public static void main(String[] args) throws IOException {
        writePassword("goole2","goole_minecrypto.arka Id");
    }
}

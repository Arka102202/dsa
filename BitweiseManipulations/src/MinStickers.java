import java.util.HashMap;
import java.util.LinkedList;

public class MinStickers {


    static HashMap<Integer, Integer> charCount = new HashMap<>();
    static LinkedList<Integer> stickerMask = new LinkedList<>();

    public static int minStickers(String[] stickers, String target) {
        for(char ch:target.toCharArray()){
            if(charCount.size() > 0 && charCount.containsKey(1<<(ch-97))){
                charCount.compute((1<<(ch-97)),(k,v)-> v+1);
            }else charCount.put(1<<(ch-97), 1);
        }
        for(String s:stickers){
            int mask = 0;
            for(char ch:s.toCharArray())
                mask |= 1<<(ch-97);
            stickerMask.add(mask);
        }





        return 0;
    }


    public static void main(String[] args) {
        minStickers(new String[]{"with","example","science"}, "thehat");
    }

}

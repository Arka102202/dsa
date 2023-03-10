import java.util.ArrayList;
import java.util.List;

public class WordBreak2 {
    static List<String> list = new ArrayList<>();
    public static List<String> wordBreak(String s, List<String> wordDict) {
        list.clear();
        generate(0, s, wordDict,"");
        return list;
    }
    static void generate(int index, String s, List<String> wordDict, String brokenWord){
        for(int i=1; i<=10; i++){
            if(index < s.length() - i){
                String word = s.substring(index, index + i);
                if (wordDict.contains(word)) {
                    generate(index + i, s, wordDict, brokenWord.concat(word).concat(" "));
                }
            }
            else if (wordDict.contains(s.substring(index))) {
                list.add(brokenWord.concat(s.substring(index)));
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("pineapplepenapple",List.of("apple","pen","applepen","pine","pineapple")));
        System.out.println(wordBreak("catsandog",List.of("cat","cats","and","sand","dog")));
        System.out.println(wordBreak("catsanddog",List.of("cat","cats","and","sand","dog")));
    }
}

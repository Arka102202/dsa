import java.util.LinkedList;
import java.util.List;

public class LetterCombinations {
    static String[][] letters = new String[][]{{"a","b","c"},{"d","e","f"},
            {"g","h","i"},{"j","k","l"},{"m","n","o"},
            {"p","q","r","s"},{"t","u","v"},{"w","x","y","z"}};
    static List<String> combinations = new LinkedList<>();
    static StringBuilder combination = new StringBuilder();
    static int index = 0;
    static List<String> letterCombinations(String digits) {
        combinations.clear();
        index = 0;
        combination = new StringBuilder();
        return letterCombinations1(digits);
    }
    public static List<String> letterCombinations1(String digits) {

        if(digits.length() >= 2){
            for(int j=index; j<digits.length(); j++) {
                char ch = digits.charAt(j);
                for (int i = 0; i < letters[ch - '0' -2].length; i++) {
                    combination.append(letters[ch - '0' -2][i]);
                    index = j + 1;
                    if(j < digits.length()-2) letterCombinations1(digits);
                    else createFinalCombinations(digits.charAt(j+1) - '0' -2);
                    combination.deleteCharAt(combination.length()-1);
                }
                j = digits.length();
            }
        }else if( digits.length() == 1) createFinalCombinations(digits.charAt(0) - '0' -2);
        return combinations;
    }
    static void createFinalCombinations(int i){
        for(int j=0; j<letters[i].length; j++){
            combination.append(letters[i][j]);
            combinations.add(new String(combination));
            combination.deleteCharAt(combination.length()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
        System.out.println(letterCombinations("2"));
        System.out.println(letterCombinations(""));

    }

}

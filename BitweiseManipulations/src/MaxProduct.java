import java.util.*;

public class MaxProduct {
    static TreeMap<Integer, List<Integer>> lengthWordMap = new TreeMap<>(Comparator.reverseOrder());
    static TreeSet<Integer> productOfLength = new TreeSet<>();
    static int lowestLen;
    public static int maxProduct(String[] words) {
        lengthWordMap.clear();
        productOfLength.clear();
        lowestLen = 0;
        for(String word:words){
            int mask = 0;
            for(char ch:word.toCharArray()) mask |= 1<<(ch-97);
            if(lengthWordMap.containsKey(word.length())) lengthWordMap.get(word.length()).add(mask);
            else lengthWordMap.put(word.length(), new ArrayList<>(List.of(mask)));
        }
        Set<Integer> lengthSet = lengthWordMap.keySet();
        for(int len1:lengthSet){
            if((productOfLength.size() == 0 || lowestLen < len1) && lengthWordMap.get(len1).size() >=2){
                List<Integer> list = lengthWordMap.get(len1);
                for(int i=0; i<list.size(); i++){
                    int mask01 = list.get(i);
                    for(int j=i+1; j<list.size(); j++){
                        int mask02 = list.get(j);
                        if((mask01 & mask02) == 0) {
                            if(productOfLength.size() == 0){
                                return (len1*len1);
                            }else return (len1*len1) > productOfLength.last() ? (len1*len1):productOfLength.last();
                        }
                    }
                }



            }

            for (int mask1:lengthWordMap.get(len1)){
                for(int len2:lengthSet){
                    if(len1 != len2){
                        for(int mask2:lengthWordMap.get(len2)){
                            if((mask1 & mask2) == 0){
                                productOfLength.add(len1*len2);
                                lowestLen = len2;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(productOfLength.size() == 0) return 0;
        else return productOfLength.last();
    }


    public static void main(String[] args) {
        System.out.println(maxProduct(new String[]{"edadc","ebbfe","aacdde","dfe","cb","fddddff","fabca","adccac","ece","ccaf","feba","bcb","edadc","aea","bacb","acefa","fcebffd","dfeebca","bedcbaa","deaccc","abedc","dadff","eef","ddebbb","abecab","cd","abdeee","eedce","deef","dceaddd","ced","fbbf","ba","eefeda","fb","cddc","adafbf","dded","aadbf","caefbaf","ccebf","dbb","ee","dadcecc","ddbcabb","afeaa","ec","aad","efde","cbcda","cdbdafd","cddc","ecaaa","ae","cfc","bccac","cdcc","abbbf","fcdface","ddbcdc","bfebb","daed","bc","dc","ecdfc","eeb","bb","dad","caecb","fbe","bbbc","cacea","dbc","fbe","bcfffbd","aeda","cff","ddfc","ea","bdfd","ccb","cb","ae","ceabefa","dcea","cbaed","bfedf","fa","ccd","fece","bceef","acabca","dafa","fdeec","dac","cae","adeeadb","ecacc","acfe","de"}));
    }



}
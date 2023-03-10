import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PyramidTransition {
    static HashMap<String, Boolean> memo = new HashMap<>();
    static HashMap<Integer, LinkedList<Integer>> lastIndex = new HashMap<>();
    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        memo.clear();
        lastIndex.clear();
        return processDynamically(bottom, 0, allowed);
    }
    public static boolean processDynamically(String layerText, int count, List<String> allowed){
        String nextLayerText = "";
        int position = 0;
        boolean result = true;
        if(layerText.length() == 1) return true;
        if(layerText.length() == 0) return false;
        if(memo.containsKey(layerText)) return memo.get(layerText);
        for(int i = 0; i<=layerText.length()-2; i++){
            String s = findCenters(layerText.substring(i,i+2),allowed,count, position);
            if(s.equals("")) break;
            nextLayerText = nextLayerText.concat(s);
            if(i == layerText.length()-2){
                result = processDynamically(nextLayerText, count+1, allowed);
                memo.put(nextLayerText, result);
                if(result) return true;
                else {
                    while(position >= 0){
                        if(lastIndex.containsKey(count) && lastIndex.get(count).get(position) < (allowed.size()-1)){
                            i = ((2*position) - position) - 1;
                            nextLayerText = nextLayerText.substring(0,position);
                            for(int k:lastIndex.keySet()){
                                if(k > count) lastIndex.get(k).clear();
                            }
                            break;
                        }
                        position--;
                    }
                    if(position < 0) return false;
                }
            }
            if(result) position++;
        }
        return false;
    }
    public static String findCenters(String layeredText, List<String> allowed, int layerCount, int position){
        int index = 0;
        if(lastIndex.containsKey(layerCount) && lastIndex.get(layerCount).size() > position)
            index = lastIndex.get(layerCount).get(position)+1;
        for(int i=index; i<allowed.size(); i++){
            if(allowed.get(i).substring(0,2).equals(layeredText)){
                if(!lastIndex.containsKey(layerCount)) lastIndex.put(layerCount, new LinkedList<>());
                if(lastIndex.get(layerCount).size() > position) lastIndex.get(layerCount).set(position,i);
                else lastIndex.get(layerCount).add(i);
                return allowed.get(i).substring(2);
            }
        }
        return "";
    }
    public static void main(String[] args) {
        System.out.println(pyramidTransition("BCD", List.of("BCC","CDE","CEA","FFF")));
    }
}

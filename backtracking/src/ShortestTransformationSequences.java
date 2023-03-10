import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestTransformationSequences {
    static List<List<String>> sequence = new LinkedList<>();
    static int minLen;

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        sequence.clear();
        minLen = 0;
        int beginWordMask = generateMask(beginWord);
        int endWordMask = generateMask(endWord);
        List<Integer> maskList = new LinkedList<>();

        for (String s : wordList) maskList.add(generateMask(s));

        if (!maskList.contains(endWordMask)) return new ArrayList<>();

        if (beginWord.length() == 1) {
            sequence.add(List.of(beginWord, endWord));
            return sequence;
        }
        return generate(beginWordMask, endWordMask, 0, wordList, maskList, new LinkedList<>(List.of(beginWord)));
    }

    static List<List<String>> generate(int beginMask, int endMask, int start, List<String> wordList, List<Integer> maskList, LinkedList<String> list) {
        for (int i = start; i < maskList.size(); i++) {
            String word = wordList.get(i);
            int mask = maskList.get(i);
            if (compare(beginMask, mask) == 2 && !list.contains(word)) {
                list.add(wordList.get(i));
                if (compare(endMask, mask) == 0) {
                    if (sequence.size() == 0 || list.size() <= minLen) {
                        if (minLen == 0 || list.size() < minLen) {
                            sequence.clear();
                            minLen = list.size();
                        }
                        sequence.add(new ArrayList<>(list));
                    }
                    list.removeLast();
                    return sequence;
                }
                generate(mask, endMask, 0, wordList, maskList, list);
                list.removeLast();
            }
        }
        return sequence;
    }

    public static void main(String[] args) {
        System.out.println(findLadders("lost", "miss", new LinkedList<>(List.of("most", "mist", "miss", "lost", "fist", "fish"))));
        System.out.println(findLadders("hot", "dog", new LinkedList<>(List.of("hot", "dog", "dot"))));
        System.out.println(findLadders("hot", "dog", new LinkedList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(findLadders("hit", "cog", new LinkedList<>(List.of("hot", "dot", "dog", "cog", "lot", "log"))));
        System.out.println(findLadders("hit", "cog", new LinkedList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    static int generateMask(String word) {
        int mask = 0;
        for (char c : word.toCharArray())
            mask += (1 << (c - 'a'));
        return mask;
    }

    static int compare(int i1, int i2) {
        return Integer.bitCount(i1 ^ i2);
    }
}
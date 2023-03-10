import java.util.LinkedList;

public class HuffmanEncodingDecoding {
    static char[] charArr;
    static int[] frequencyArr;
    static String[] codes;
    static ListNode masterNode;
    static void createArrays(String s){
        int[] fArray = new int[10000];
        char[] charArray = new char[10000];
        int count = 0;
        for(char ch:s.toCharArray()){
            if(fArray[ch] >= 1) fArray[ch] +=1;
            else {
                charArray[ch] = ch;
                fArray[ch] = 1;
                count++;
            }
        }
        charArr = new char[count];
        frequencyArr = new int[count];
        int j=0;
        for(int i=1; i<10000; i++){
            if(fArray[i] != 0){
                charArr[j] = charArray[i];
                frequencyArr[j++] = fArray[i];
            }
        }
//        for(j=0; j<charArr.length; j++) System.out.print(charArr[j]+" --> "+frequencyArr[j]+", ");
//        System.out.println("\n\n\n");

    }
    static void sortArraysDecreasingOrder(){
        int i = (frequencyArr.length >>> 1);
        for(;i>=0;i--){
            maxHeapifyDown(i,frequencyArr[i], charArr[i], frequencyArr.length);
        }
        int size = frequencyArr.length;
        LinkedList<ListNode> charList = new LinkedList<>();
        LinkedList<Integer> frequencyList = new LinkedList<>();
        while (size > 0){
            int max = frequencyArr[0];
            char ch = charArr[0];
            frequencyArr[0] = frequencyArr[size-1];
            charArr[0] = charArr[size-1];
            frequencyArr[size-1] = 0;
            charArr[size-1] = 0;
            maxHeapifyDown(0,frequencyArr[0], charArr[0], --size);
            charList.add(new ListNode(ch));
            frequencyList.add(max);
        }

//        for(int j=0; j<charList.size(); j++) System.out.print((char) charList.get(j).val+" --> "+frequencyList.get(j)+", ");
//        System.out.println();

        //call
        createTree(charList,frequencyList);
    }
    static void createTree(LinkedList<ListNode> charList, LinkedList<Integer> fList) {
        ListNode node;
        while (charList.size() >= 2) {
            char val = (char)(fList.removeLast() + fList.removeLast());
            ListNode rNode = charList.removeLast();
            ListNode lNode = charList.removeLast();
            node = new ListNode(val, lNode, rNode);
            int i = charList.size() - 1;
            while (i > 0 && fList.get(i) < val) i--;
            i++;
            if (i >= 0) {
                fList.add(i, (int)val);
                charList.add(i, node);
            }
        }
        charArr = new char[10000];
        codes = new String[10000];
        masterNode = new ListNode(charList.removeLast());
        // call
        createCodeList(masterNode,"");
        for(int i=0; i<codes.length; i++){
            if(codes[i] != null)
                System.out.print("("+charArr[i] + ") --> " + codes[i] +", ");
        }
        System.out.println();
    }
    static void createCodeList(ListNode node,String code){
        if(node.hasLeft()){
            createCodeList(node.left, code.concat("0"));
        }
        if(node.hasRight()){
            createCodeList(node.right, code.concat("1"));
        }
        if(!node.hasLeft() && !node.hasRight()) {
            charArr[node.charVal] = node.charVal;
            codes[node.charVal] = code;
        }
    }
    static String encode(String s){
        StringBuilder encodedString = new StringBuilder();

        for(char ch:s.toCharArray()) encodedString.append(codes[ch]);

        return new String(encodedString);
    }
    static String decode(String s){
        ListNode node = masterNode;
        StringBuilder sb = new StringBuilder();
        for(char i:s.toCharArray()){
            if(i == '0'){
                node = node.left;
            }else if (i=='1'){
                node = node.right;
            }
            if(!node.hasLeft() && !node.hasRight()) {
                sb.append(node.charVal);
                node = masterNode;
            }
        }
        return new String(sb);
    }
    static void maxHeapifyDown(int p, int valueInt, char valueChar,int n){
        int half = n >>> 1;
        while (p<half){
            int child = (p << 1)+1;
            int childValueInt = frequencyArr[child];
            char childChar = charArr[child];
            int right = child+1;

            if(right < n && frequencyArr[right] > frequencyArr[child]) {
                childValueInt = frequencyArr[child = right];
                childChar = charArr[child];
            }
            if (frequencyArr[child] < frequencyArr[p])
                break;

            frequencyArr[p] = childValueInt;
            charArr[p] = childChar;
            p = child;
            frequencyArr[p] = valueInt;
            charArr[p] = valueChar;
        }

        frequencyArr[p] = valueInt;
        charArr[p] = valueChar;
    }
    public static void main(String[] args) {
        createArrays("joyeetadas arkadyutisikdar");
        sortArraysDecreasingOrder();
        String encoded = encode("joyeetadas arkadyutisikdar");
        System.out.println("\""+decode(encoded)+"\"");

        String s = "The n-ary Huffman algorithm uses the {0, 1,..., n − 1} alphabet to encode message and build " +
                "an n-ary tree. This approach was considered by Huffman in his original paper. " +
                "The same algorithm applies as for binary (n=2) codes, except that the n least probable symbols are " +
                "taken together, instead of just the 2 least probable. Note that for n greater than 2, not all sets of" +
                " source words can properly form an n-ary tree for Huffman coding. In these cases, additional 0-probability " +
                "place holders must be added. This is because the tree must form an n to 1 contractor; for binary coding, " +
                "this is a 2 to 1 contractor, and any sized set can form such a contractor. If the number of source words " +
                "is congruent to 1 modulo n−1, then the set of source words will form a proper Huffman tree.";
        createArrays(s.repeat(1));
        sortArraysDecreasingOrder();
        System.out.println("\""+decode(encode(s.repeat(10)))+"\"");
    }

}

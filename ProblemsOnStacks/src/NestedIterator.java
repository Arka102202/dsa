import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    List<NestedInteger> nestedList;
    LinkedList<Integer> stack = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        createFlatList(nestedList);
    }

    void createFlatList(List<NestedInteger> nestedList) {

        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) stack.add(ni.getInteger());
            else {
                List<NestedInteger> list = ni.getList();
                createFlatList(list);
            }
        }

    }

    @Override
    public Integer next() {
        return stack.removeFirst();
    }

    @Override
    public boolean hasNext() {
        return stack.size() >= 1;
    }


    static int index = 0;
    public static NestedInteger deserialize(String s) {
        index = 0;
        return processRecursively(s);
    }
    private static NestedInteger processRecursively(String s){

        NestedInteger ni = new NestedInteger();

        for(int i=index; i<s.length(); i++){
            if(s.charAt(i) == '['){

                index = i+1;
                ni.add(processRecursively(s));
                i = index;

            }
            else if (s.charAt(i) == ']'){
                index = i;
                return ni;

            }else if(s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '-') {

                String num = "";
                while(i < s.length() && s.charAt(i) != ',' & s.charAt(i) != ']' ){
                    num = num.concat(Character.toString(s.charAt(i)));
                    i++;
                }
                if(!num.isEmpty()) ni.add(new NestedInteger(Integer.parseInt(num)));
                i--;
            }
        }
        return ni;

    }
    public static void main(String[] args) {
//        deserialize("[123,[456,[789],[123,23,1,1222]]");
        deserialize("[-123,[456,[789]]]");
//        deserialize("324");

    }



}







 class NestedInteger {

    // Constructor initializes an empty nested list.
    public NestedInteger(){}

    // Constructor initializes a single integer.
    public NestedInteger(int value){

        setInteger(value);
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger(){
        return true;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger(){

        return 10;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value){}

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public  void add(NestedInteger ni){}

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public  List<NestedInteger> getList(){
        return new ArrayList<>();
    }
}

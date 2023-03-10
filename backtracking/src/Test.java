import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class Test {
    static int solve(LinkedList<Integer> intStack, LinkedList<Character> opStack){
        ListIterator<Integer> iterator = intStack.listIterator();
        ListIterator<Character> iterator1 = opStack.listIterator();

        int j = 0;
        while (iterator1.hasNext()){
            if(iterator1.next() == '*') {
                int k = j;
                while(k>0){
                    iterator.next();
                    k--;
                }
                int a = iterator.next();
                iterator.remove();
                int b = iterator.next();
                iterator.remove();
                iterator.add(a*b);
                iterator1.remove();
                iterator = intStack.listIterator(j);
                j=0;
            }else {
                j++;
            }


        }

        int a = intStack.get(0);
        for(int i=1; i<intStack.size(); i++){
            if(opStack.get(i-1) == '+') a += intStack.get(i);
            else a -= intStack.get(i);
        }

        return a;

    }
    public static void main(String[] args) throws ScriptException {
        System.out.println(solve(new LinkedList<>(List.of(1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6 )), new LinkedList<>(List.of('+','-','*','*','+','+','-','*','*','+'
                ,'+','-','*','*','+','+','-','*','*','+','+','-','*'))));
        System.out.println(Integer.toBinaryString(63));

        char letter = 'a';
        System.out.println((char)(letter & '_'));
        System.out.println((char)(letter | ' '));




    }
}

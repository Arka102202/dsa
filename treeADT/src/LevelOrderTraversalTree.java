import java.util.LinkedList;
import java.util.TreeSet;

public class LevelOrderTraversalTree  {

    public static void main(String[] args) {
        ListNode _1 = new ListNode('1');
        ListNode _2 = new ListNode('2');
        ListNode _3 = new ListNode('3');
        ListNode _4 = new ListNode('4');
        ListNode _5 = new ListNode('5');
        ListNode _6 = new ListNode('6');
        ListNode _7 = new ListNode('7');
        ListNode _8 = new ListNode('8');
        ListNode _9 = new ListNode('9');
        ListNode _10 = new ListNode('A');
        ListNode _11 = new ListNode('B');
        ListNode _12 = new ListNode('C');
        ListNode _13 = new ListNode('D');
        ListNode _14 = new ListNode('E');
        ListNode _15 = new ListNode('F');


        _1.left = _2; _1.right = _3;
        _2.left = _4; _2.right = _5;
        _3.left = _6; _3.right = _7;
        _4.left = _8; _4.right = _9;
        _5.left = _10; _5.right = _11;
        _6.left = _12; _6.right = _13;
        _7.left = _14; _7.right = _15;


        LinkedList<ListNode> stack = new LinkedList<>();

        stack.add(_1);

        while(!stack.isEmpty()){
            int s = stack.size();
            for(int i=0; i<s; i++){
                ListNode node = stack.removeFirst();
                System.out.print(node.charVal + ", ");
                if(node.hasLeft())
                    stack.add(node.left);
                if(node.hasRight())
                    stack.add(node.right);
            }
            System.out.println("\b\b");
        }



    }








}

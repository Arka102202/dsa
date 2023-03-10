import java.util.LinkedList;

public class RemoveNthNodeFromEndofList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        LinkedList<ListNode> stack = new LinkedList<>();
        while (head != null){
            stack.addFirst(new ListNode(head.val));
            head = head.next;
        }
        ListNode node = null, next = null;
        for(int i=0; stack.size()>0; i++){
            if (i != n-1){
                node = new ListNode(stack.removeFirst().val);
                node.next = next;
                next = node;
            }else stack.removeFirst();
        }
        return node;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        node = removeNthFromEnd(node, 2);
        System.out.println();



    }









    static public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

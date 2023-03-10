import java.util.LinkedHashSet;

public class LinkedListCycleII {

    public static ListNode detectCycle(ListNode head) {

        LinkedHashSet<ListNode> set = new LinkedHashSet<>();
        if(head != null) {
            set.add(head);
            head = head.next;
        }

        while (set.add(head) && head != null && head.next != null) head = head.next;

        return  head;
    }


    public static void main(String[] args) {

        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);

//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n2;


        System.out.println(detectCycle(n1));






    }
}

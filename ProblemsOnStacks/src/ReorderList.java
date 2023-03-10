import java.util.ArrayList;

public class ReorderList {
    public static void reorderList(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode node = head;
        while (node.next != null) {
            arrayList.add(node.val);
            node = node.next;
        }
        arrayList.add(node.val);
        ListNode n;
        ListNode pre = null;
        head = null;
        boolean hasHead = false;
        int counter = (arrayList.size()&1) == 0 ? (arrayList.size()>>>1) : ((arrayList.size()+1)>>>1);
        for(int i=0; i<counter; i++){
            n= new ListNode(arrayList.get(i));
            if (pre != null) {
                pre.next = n;
            }
            pre = n;
            if(((arrayList.size()&1) != 0 && i != counter-1) || (arrayList.size()&1) == 0){
                n= new ListNode(arrayList.get(arrayList.size()-1-i));
                pre.next = n;
                if (!hasHead) {
                    head = pre;
                    hasHead = true;
                }
                pre = n;
            }
        }
        node = head;
        while (node.next != null) {
            System.out.println(node.val);
            node = node.next;
        }
        System.out.println(node.val);


    }

    public static void main(String[] args) {
        ListNode node01 = new ListNode();
        ListNode node02 = new ListNode();
        ListNode node03 = new ListNode();
        ListNode node04 = new ListNode();
        ListNode node05 = new ListNode();
        ListNode node06 = new ListNode();
        ListNode node07 = new ListNode();
        ListNode node08 = new ListNode();
        ListNode node09 = new ListNode();
        ListNode node010 = new ListNode();
        ListNode node011 = new ListNode();
        ListNode node012 = new ListNode();

        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;
        node05.next = node06;
        node06.next = node07;
        node07.next = node08;
        node08.next = node09;
        node09.next = node010;
        node010.next = node011;
        node011.next = node012;

        node01.val = 10;
        node02.val = 12;
        node03.val = 14;
        node04.val = 16;
        node05.val = 18;
        node06.val = 20;
        node07.val = 21;
        node08.val = 19;
        node09.val = 17;
        node010.val = 15;
        node011.val = 13;
        node012.val = 11;

        reorderList(node01);
        System.out.println();
        reorderList(node02);
    }

}



class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }

}
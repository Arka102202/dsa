import java.util.ArrayList;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    ListNode(int val) { this.val = val; }

    public static void main(String[] args) {

        ListNode node01 = new ListNode();
        ListNode node02 = new ListNode();
        ListNode node03 = new ListNode();
        ListNode node04 = new ListNode();
        ListNode node05 = new ListNode();
//        ListNode node06 = new ListNode();
//        ListNode node07 = new ListNode();
//        ListNode node08 = new ListNode();
//        ListNode node09 = new ListNode();
//        ListNode node010 = new ListNode();
//        ListNode node011 = new ListNode();
//        ListNode node012 = new ListNode();
//
//
//        ListNode node11 = new ListNode();
//        ListNode node12 = new ListNode();
//        ListNode node13 = new ListNode();
//        ListNode node14 = new ListNode();
//        ListNode node15 = new ListNode();
//        ListNode node16 = new ListNode();
//
//
//        node01.val = 9;
//        node02.val = 9;
//        node03.val = 9;
//        node04.val = 9;
//        node05.val = 9;
//        node06.val = 9;
//        node07.val = 9;
//        node08.val = 9;
//        node09.val = 9;
//        node010.val = 9;
//        node011.val = 9;
//        node012.val = 9;


        node01.next = node02;
        node02.next = node03;
        node03.next = node04;
        node04.next = node05;
//        node05.next = node06;
//        node06.next = node07;
//        node07.next = node08;
//        node08.next = node09;
//        node09.next = node010;
//        node010.next = node011;
//        node011.next = node012;


//        node11.val = 9;
//        node12.val = 9;
//        node13.val = 9;
//        node14.val = 9;
//        node15.val = 9;
//        node16.val = 7;
//
//        node11.next = node12;
//        node12.next = node13;
//        node13.next = node14;
//        node14.next = node15;
//        node15.next = node16;
//
//        ListNode node = node01.addTwoNumbers(node01, node11);
//
//        while (node.next != null) {
//            System.out.print(node.val + " ");
//            node = node.next;
//        }
//
//        System.out.println(node.val + " ");

        node01.val = 1;
        node02.val = 2;
        node03.val = 3;
        node04.val = 4;
        node05.val = 5;
//        node06.val = 6;
//        node07.val = 7;
//        node08.val = 8;
//        node09.val = 9;
//        node010.val = 10;
//        node011.val = 11;
//        node012.val = 12;

//        node = node01.addTwoNumbers(node01, node11);
//
//        while (node.next != null) {
//            System.out.print(node.val + " ");
//            node = node.next;
//        }
//
//        System.out.println(node.val + " ");
//
//        node = node.rotateRight(node01, 11);
//
//        while (node.next != null) {
//            System.out.print(node.val + " ");
//            node = node.next;
//        }
//
//        System.out.println(node.val + " ");
//
//        node = node.rotateRight(new ListNode(), 11);
//
//        while (node.next != null) {
//            System.out.print(node.val + " ");
//            node = node.next;
//        }
//
//        System.out.print(node.val + " ");

        ListNode node = new ListNode();

        ListNode[] arr = node.splitListToParts(node01,3);

        System.out.println(arr.length);





//
//
//        int[] array = new int[]{1,2,3,4,5};
//
//        ListNode n;
//        ListNode pre = null;
//        ListNode head = null;
//        boolean hasHead = false;
//        for(int i:array){
//
//            n= new ListNode(i);
//            if (pre != null) {
//                pre.next = n;
//                if (!hasHead) {
//                    head = pre;
//                    hasHead = true;
//                }
//            }
//            pre = n;
//
//        }
//
//        ListNode head2 = null;
//        node = head;
//        pre = null;
//        hasHead = false;
//        while (node.next != null) {
//
//            n= new ListNode(node.val);
//            if (pre != null) {
//                pre.next = n;
//                if (!hasHead) {
//                    head2 = pre;
//                    hasHead = true;
//                }
//            }
//            pre = n;
//
//
//
//
//            node = node.next;
//
//        }
//
//
//
//
//
//
//
//
//        node = head2;
//        while (node.next != null) {
//            System.out.print(node.val + " ");
//
//            node = node.next;
//        }
//
//        System.out.println(node.val + " ");



    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        headNode = new ListNode();
        hasHead = false;
        return addTwoNumbers(l1, l2, null);

    }

    static int nextNodeVal = 0;

    static ListNode headNode;

    static boolean hasHead;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, ListNode previousNode) {


        int sum = nextNodeVal;

        if (l1 != null) sum += l1.val;
        if (l2 != null) sum += l2.val;

        int presentNodeVal = sum % 10;
        nextNodeVal = sum / 10;

        ListNode node = new ListNode();

        node.val = presentNodeVal;
        if (previousNode != null) previousNode.next = node;

        if (!hasHead) {
            headNode = node;
            hasHead = true;
        }

        if ((l1 != null && l1.next != null) & (l2 == null | (l2 != null && l2.next == null)))
            return addTwoNumbers(l1.next, null, node);

        else if ((l1 == null | (l1 != null && l1.next == null)) & (l2 != null && l2.next != null))
            return addTwoNumbers(null, l2.next, node);

        else if (l1 == null & l2 == null & nextNodeVal > 0) return addTwoNumbers(null, null, node);

        else if ((l1 != null && l1.next != null) & (l2 != null && l2.next != null))
            return addTwoNumbers(l1.next, l2.next, node);

        else if (nextNodeVal > 0) return addTwoNumbers(null, null, node);

        return headNode;

    }


    public ListNode rotateRight(ListNode head, int k) {


        if (head != null) {
            List<Integer> list = new ArrayList<>();
            ListNode node = head;
            ListNode lastNode1;
            ListNode lastNode2 = new ListNode();
            ListNode firstNode;

            while (node.next != null) {
                list.add(node.val);
                node = node.next;
            }
            list.add(node.val);
            lastNode1 = node;
            k -= (k / list.size()) * list.size();
            int distance = list.size() - k - 1;


            int i = 0;
            int key = list.get(distance);
            node = head;

            while (node.next != null) {
                if ((i == distance && key == node.val)) {
                    lastNode2 = node;
                    break;
                }

                i++;
                node = node.next;
            }
            if (node.next != null) {
                firstNode = node.next;
                lastNode1.next = head;
                lastNode2.next = null;
            } else firstNode = head;

            return firstNode;
        } else return new ListNode();

    }


    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        ListNode node = head;

        if(node != null){
            while (node.next != null) {
                length++;
                node = node.next;
            }
            length++;
        }

        int partSize = length / k;
        int excess = length % k;
        int per = excess;
        for(int i=length; i>0; i--){
            if(excess%i == 0) {
                per = excess/i;
                break;
            }
        }

        ListNode[] listNodeArray = new ListNode[k];
        node = head;

        if (partSize == 0) {

            int i = 0;
            if(node != null){
                while (node.next != null) {
                    ListNode n = new ListNode(node.val, node.next);
                    n.next = null;
                    listNodeArray[i] = n;
                    i++;
                    node = node.next;
                }
                ListNode n = new ListNode(node.val, null);
                listNodeArray[i] = n;
                i++;
            }
            for (; i < k; i++) listNodeArray[i] = null;

        }
        else {
            boolean hasHead = false, entered = false;
            ListNode newHead = new ListNode();
            ListNode newTail;
            ListNode previousNode = null;
            int c = 0;
            int lengthCount = 0;
            ListNode middle;

            while (node.next != null) {

                if(lengthCount == (partSize + per)-1) {

                    newTail = new ListNode(node.val, null);
                    if(previousNode == null) {
                        listNodeArray[c] = newTail;
                    }else{
                        previousNode.next = newTail;
                        if (!hasHead) {
                            newHead = previousNode;
                        }
                        listNodeArray[c] = newHead;
                    }

                    c++;
                    hasHead = false;
                    entered = true;
                    previousNode = null;
                    lengthCount = 0;
                }

                if(!entered){
                    middle = new ListNode(node.val);

                    if (previousNode != null) {
                        previousNode.next = middle;
                        if (!hasHead) {
                            newHead = previousNode;
                            hasHead = true;
                        }
                    }
                    previousNode = middle;
                    lengthCount++;
                }else{
                    entered = false;
                    excess -= per;
                }

                if(excess == 0){
                    per = 0;
                }

                node = node.next;


            }

            newTail = new ListNode(node.val, null);
            if(previousNode == null) {
                listNodeArray[c] = newTail;
            }else{
                previousNode.next = newTail;
                if (!hasHead) {
                    newHead = previousNode;
                }
                listNodeArray[c] = newHead;
            }
            c++;

            for (; c < k; c++) listNodeArray[c] = null;


        }
        return listNodeArray;


    }
}

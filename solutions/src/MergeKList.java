public class MergeKList {
    ListNode finalHead;
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists != null ){
            ListNode nodeRight;
            ListNode nodeLeft;
            ListNode previousNode;
            ListNode node;
            boolean hasHead, left;
            int j;
            for(j = 0; j< lists.length; j++){
                if(lists[j] != null){
                    finalHead = lists[j];
                    j++;
                    break;
                }
            }
            if(finalHead == null){
                return null;
            }
            for (int i = j; i < lists.length; i++) {
                nodeLeft = new ListNode(finalHead.val, finalHead.next);
                hasHead = false;
                if(lists[i] != null)nodeRight = new ListNode(lists[i].val, lists[i].next);
                else continue;
                previousNode = null;
                while (nodeLeft.next != null & nodeRight.next != null) {
                    left = (nodeLeft.val <= nodeRight.val);
                    if (left) node = new ListNode(nodeLeft.val, nodeLeft.next);
                    else node = new ListNode(nodeRight.val, nodeRight.next);

                    if (previousNode != null) {
                        previousNode.next = node;
                        if (!hasHead) {
                            finalHead = previousNode;
                            hasHead = true;
                        }
                    }
                    previousNode = node;
                    if (left) nodeLeft = nodeLeft.next;
                    else nodeRight = nodeRight.next;
                }
                left = false;
                if (nodeLeft.next == null) {
                    while (nodeRight.next != null) {
                        left = (nodeLeft.val <= nodeRight.val);
                        if (left) node = new ListNode(nodeLeft.val, nodeLeft.next);
                        else node = new ListNode(nodeRight.val, nodeRight.next);
                        if (previousNode != null) {
                            previousNode.next = node;
                            if (!hasHead) {
                                finalHead = previousNode;
                                hasHead = true;
                            }
                        }
                        previousNode = node;
                        if (left) break;
                        else nodeRight = nodeRight.next;
                    }
                    if (left) {
                        while (nodeRight.next != null) {
                            node = new ListNode(nodeRight.val, nodeRight.next);
                            previousNode.next = node;
                            if (!hasHead) {
                                finalHead = previousNode;
                                hasHead = true;
                            }
                            previousNode = node;
                            nodeRight = nodeRight.next;
                        }
                        node = new ListNode(nodeRight.val, null);
                    }
                    else {
                        left = (nodeLeft.val <= nodeRight.val);
                        if (left) node = new ListNode(nodeLeft.val, null);
                        else node = new ListNode(nodeRight.val, null);

                        if(previousNode != null){
                            previousNode.next = node;
                            if (!hasHead) {
                                finalHead = previousNode;
                            }
                        }
                        else{
                            previousNode = node;
                            finalHead = previousNode;
                        }
                        previousNode = node;
                        if (left) {
                            node = new ListNode(nodeRight.val, null);
                        }
                        else {
                            node = new ListNode(nodeLeft.val, null);
                        }

                    }


                }
                else {
                    while (nodeLeft.next != null) {
                        left = (nodeLeft.val <= nodeRight.val);
                        if (left) node = new ListNode(nodeLeft.val, nodeLeft.next);
                        else node = new ListNode(nodeRight.val, null);

                        if (previousNode != null) {
                            previousNode.next = node;
                            if (!hasHead) {
                                finalHead = previousNode;
                                hasHead = true;
                            }
                        }
                        previousNode = node;

                        if (left) nodeLeft = nodeLeft.next;
                        else break;
                    }
                    if (!left) {
                        while (nodeLeft.next != null) {
                            node = new ListNode(nodeLeft.val, nodeLeft.next);
                            previousNode.next = node;
                            if (!hasHead) {
                                finalHead = previousNode;
                                hasHead = true;
                            }
                            previousNode = node;
                            nodeLeft = nodeLeft.next;
                        }
                        node = new ListNode(nodeLeft.val, null);
                    }
                    else {
                        left = (nodeLeft.val <= nodeRight.val);
                        if (left) node = new ListNode(nodeLeft.val, null);
                        else node = new ListNode(nodeRight.val, null);

                        previousNode.next = node;
                        if (!hasHead) {
                            finalHead = previousNode;
                        }
                        previousNode = node;

                        if (left) {
                            node = new ListNode(nodeRight.val, null);
                        } else {
                            node = new ListNode(nodeLeft.val, null);
                        }
                    }
                }
                previousNode.next = node;
            }
        }
        return finalHead;
    }
    public static void main(String[] args) {


        ListNode node01 = new ListNode();
        ListNode node02 = new ListNode();
        ListNode node03 = new ListNode();
//        ListNode node04 = new ListNode();
//        ListNode node05 = new ListNode();
//        ListNode node06 = new ListNode();
//        ListNode node07 = new ListNode();
//        ListNode node08 = new ListNode();
        node01.next = node02;
        node02.next = node03;
//        node03.next = node04;
//        node04.next = node05;
//        node05.next = node06;
//        node06.next = node07;
//        node07.next = node08;
        node01.val = -1;
        node02.val = 5;
        node03.val = 11;
//        node04.val = 4;
//        node05.val = 5;
//        node06.val = 6;
//        node07.val = 7;
//        node08.val = 8;

        ListNode node11 = new ListNode();
        ListNode node12 = new ListNode();
//        ListNode node13 = new ListNode();
//        ListNode node14 = new ListNode();
//        ListNode node15 = new ListNode();
//        ListNode node16 = new ListNode();
        node11.val = 6;
        node12.val = 10;
//        node13.val = 10;
//        node14.val = 11;
//        node15.val = 12;
//        node16.val = 20;
        node11.next = node12;
//        node12.next = node13;
//        node13.next = node14;
//        node14.next = node15;
//        node15.next = node16;

        MergeKList mergeKList = new MergeKList();


        ListNode node = mergeKList.mergeKLists(new ListNode[]{null, node01, null,node11});

        if(node == null){
            System.out.println((Object) null);
        }else{
            while (node.next != null) {
                System.out.print(node.val + " ");
                node = node.next;
            }

            System.out.println(node.val + " ");
        }

    }
}

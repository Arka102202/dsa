public class ListNode {

    char charVal;
    int val;
    ListNode left, right;

    ListNode() {
    }
    ListNode(ListNode node){
        this(node.charVal,node.left, node.right);
    }

    ListNode(char charVal) {
        this.charVal = charVal;
    }
    ListNode(int val) {
        this.val = val;
    }

    ListNode(char charVal, ListNode left, ListNode right) {
        this.charVal = charVal;
        this.left = left;
        this.right = right;
    }

    ListNode(int val, ListNode left, ListNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    boolean hasLeft(){
        return left != null;
    }

    boolean hasRight(){
        return right != null;
    }


}

public class Node<T extends Comparable<T>> {
    T val;
    int height = -1;
    Node<T> left;
    Node<T> right;
    Node() {
        val = null;
    }
    Node(Node<T> node){
        this(node.val,node.left, node.right, node.height);
    }

    Node(T val) {
        this.val = val;
    }
    Node(T val, Node<T> left, Node<T> right, int height) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    boolean hasLeft(){
        return left != null;
    }

    boolean hasRight(){
        return right != null;
    }

    Node<T> reset(){
        this.val = null;
        this.left = null;
        this.right = null;
        this.height = -1;

        return this;
    }

}

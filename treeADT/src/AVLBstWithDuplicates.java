import java.util.LinkedList;

/**
 * This class is a basic implementation of the AVL BST with added functionality to
 * store duplicate data as a count value at each node. As the duplicate values are
 * not stored in the actual tree, the BST looks the same.
 * <p>
 *     <i><strong>If a node doesn't have a child then the value of a child node is
 *     null then it will have a height of -1.</strong></i> So that we can always
 *     use the formula which is  <pre>
 *     node.height = Math.max(node.left.height, node.right.height);</pre>
 *     to calculate the height of node.
 * </p>
 *
 * @param <T>
 * @author Arkadyuti Sikdar
 */
public class AVLBstWithDuplicates<T extends Comparable<T>> {

    /**
     * It is the root node of the whole tree.
     * <p>
     * Implementation is provided at the last of this class.
     */
    Node<T> masterNode;
    int size;
    /**
     * Flags to store added or removed state of node.
     */
    boolean added, removed;

    AVLBstWithDuplicates() {
        masterNode = new Node<>();
        size = 0;
    }

    /**
     * Public method for users to add a node if possible.
     *
     * @param t the value.
     * @return returns true if added or else false.
     */
    public boolean add(T t) {
        added = false;
        if (size == 0) {
            added = true;
            masterNode = new Node<>(t, new Node<>(), new Node<>(), 0, 1);
        } else add(t, masterNode);
        if (added) size++;
        return added;
    }

    /**
     * This is the actual method which gets called to add a node in the tree with the provided value.
     * <p>
     *     This method first tries to get to the point or the {@link Node} (if the data is already present).
     *     Then if it finds that the node is null then it adds the node or if the node is already present
     *     then just increase the count of that node.</p>
     * <p>
     *     After adding or updating the required node it performs the height balancing functions as required.
     * </p>
     *
     * @param t the value that needs to be added.
     * @param node the masterNode.
     * @return returns the added or updated node.
     */
    private Node<T> add(T t, Node<T> node) {
        if (node.val == null) {
            added = true;
            node = new Node<>(t, new Node<>(), new Node<>(), 0, 1);
            node.height = 0;
            return node;
        }
        // extra for same values
        else if (node.val.equals(t)) {
            added = true;
            node.count++;
            return node;
        }

        if (t.compareTo(node.val) < 0) {
            node.left = add(t, node.left);
            if (node.left.height - node.right.height == 2) {
                if (t.compareTo(node.left.val) < 0) rotateRight(node);
                else leftRightRotate(node);
            }
        } else if (t.compareTo(node.val) > 0) {
            node.right = add(t, node.right);
            if (node.right.height - node.left.height == 2) {
                if (t.compareTo(node.right.val) > 0) rotateLeft(node);
                else rightLeftRotate(node);
            }
        }
        node.height = Math.max(node.left.height, node.right.height) + 1;
        return node;
    }

    /**
     * This method performs the LL or rightRotate operation.
     *
     * @param node this is the node which is rotated.
     */
    private void rotateRight(Node<T> node) {
        Node<T> left = node.left.right, right = node.right;
        int count = node.left.count, height = 0;

        if (left != null && right != null)
            height = Math.max(left.height, right.height) + 1;
        else if (right != null) height = right.height + 1;
        else if (left != null) height = left.height + 1;

        Node<T> temp = new Node<>(node.val, left, right, height, node.count);
        node.val = node.left.val;
        node.right = temp;
        node.left = node.left.left;
        if (left != null && right != null)
            node.height = Math.max(node.left.height, node.right.height) + 1;
        else if (right != null) node.height = node.right.height + 1;
        else if (left != null) node.height = node.left.height + 1;
        else node.height = 0;
        node.count = count;
    }
    /**
     * This method performs the RR or leftRotate operation.
     *
     * @param node this is the node which is rotated.
     */
    private void rotateLeft(Node<T> node) {
        Node<T> left = node.left, right = node.right.left;
        int count = node.right.count, height = 0;

        if (left != null && right != null)
            height = Math.max(left.height, right.height) + 1;
        else if (right != null) height = right.height + 1;
        else if (left != null) height = left.height + 1;

        Node<T> temp = new Node<>(node.val, left, right, height, node.count);
        node.val = node.right.val;
        node.right = node.right.right;
        node.left = temp;
        if (left != null && right != null)
            node.height = Math.max(node.left.height, node.right.height) + 1;
        else if (right != null) node.height = node.right.height + 1;
        else if (left != null) node.height = node.left.height + 1;
        else node.height = 0;
        node.count = count;
    }
    /**
     * This method performs the rightLeftRotate operation.
     *
     * @param node this is the node which is rotated.
     */
    private void rightLeftRotate(Node<T> node) {
        rotateRight(node.right);
        rotateLeft(node);
    }
    /**
     * This method performs the leftRightRotate operation.
     *
     * @param node this is the node which is rotated.
     */
    private void leftRightRotate(Node<T> node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    /**
     * This the public method to delete a node.
     *
     * @param t the value that needs to be deleted.
     * @return returns the deleted value.
     */
    public T remove(T t) {
        removed = false;
        remove(t, masterNode);
        if (removed) {
            size--;
            if (size == 0) masterNode = new Node<>();
            return t;
        } else return null;
    }

    /**
     * This method gets called to delete a node if there are no more duplicate values of that node or
     * otherwise update the count value of that node.
     * <p>
     *     I have used the inOrder-Predecessor to replace the deleted node.
     * </p>
     *
     * @param t value to be deleted.
     * @param node always the masterNode.
     * @return returns the deleted node.
     */
    private Node<T> remove(T t, Node<T> node) {
        // if the value is equal to the value of this node
        if (node.val != null && node.val.equals(t)) {
            if (node.count > 1) {
                node.count--;
                removed = true;
            } else {
                removed = true;
                Node<T> replacementNode = node;
                if (t.compareTo(masterNode.val) < 0) {
                    if (node.right.val != null) node.right = findPredecessorLeft(node.right, replacementNode);
                    else node = node.left;
                } else {
                    if (node.left.val != null) node.left = findPredecessorRight(node.left, replacementNode);
                    else node = node.right;
                }
                if (node.val != null) node.height = Math.max(node.left.height, node.right.height) + 1;
            }
        }
        // if the value is less than the value of this node
        if (node.val != null && t.compareTo(node.val) < 0) {
            node.left = remove(t, node.left);
            if (node.right.height - node.left.height == 2) {
                if(node.right.right.val != null) rotateLeft(node);
                else rightLeftRotate(node);
            }
            else if (node.left.height - node.right.height == 2) {
                if (node.left.left.val != null) rotateRight(node);
                else leftRightRotate(node);
            }

        }
        // if the value is greater than the value of this node
        else if (node.val != null) {
            node.right = remove(t, node.right);
            if (node.right.height - node.left.height == 2) {
                if(node.right.right.val != null) rotateLeft(node);
                else if (node.right.left.val != null) rightLeftRotate(node);
            }
            else if (node.left.height - node.right.height == 2) {
                if (node.left.left.val != null) rotateRight(node);
                else if (node.left.right.val != null) leftRightRotate(node);
            }
        }
        if (node.val != null) node.height = Math.max(node.left.height, node.right.height) + 1;
        return node;
    }

    /**
     * This method finds the predecessor for nodes on the left side of the masterNode.
     *
     * @param node this the right child of the node that needs to be deleted.
     * @param replacementNode the node that needs to be deleted
     * @return returns the predecessor node.
     */
    private Node<T> findPredecessorLeft(Node<T> node, Node<T> replacementNode) {
        if (node.left.val != null) node.left = findPredecessorLeft(node.left, replacementNode);
        else {
            replacementNode.val = node.val;
            replacementNode.count = node.count;
            return new Node<>(node.right);
        }
        if (node.left.height - node.right.height == 2) rotateRight(node);
        else if (node.right.height - node.left.height == 2) rotateLeft(node);
        return node;
    }

    /**
     * This method finds the predecessor for nodes on the right side of the masterNode.
     *
     * @param node this the left child of the node that needs to be deleted.
     * @param replacementNode the node that needs to be deleted
     * @return returns the predecessor node.
     */
    private Node<T> findPredecessorRight(Node<T> node, Node<T> replacementNode) {
        if (node.right.val != null) node.right = findPredecessorRight(node.right, replacementNode);
        else {
            replacementNode.val = node.val;
            replacementNode.count = node.count;
            return new Node<>(node.left);
        }
        if (node.left.height - node.right.height == 2) rotateRight(node);
        else if (node.right.height - node.left.height == 2) rotateLeft(node);
        return node;
    }

    /**
     * This method returns the max value in the node.
     *
     * @return returns the max value.
     */
    public T getMAx() {
        if (masterNode != null && masterNode.right.val != null) {
            Node<T> node = masterNode.right;
            while (node.right.val != null)
                node = node.right;
            return node.val;
        } else return masterNode.val;
    }

    /**
     * This method prints the value and the count of any node which has the provided value.
     *
     * @param t the value of the node that you wants to display.
     */
    public void show(T t) {
        Node<T> node = masterNode;
        while (node.val != null) {
            if (t.compareTo(node.val) < 0) node = node.left;
            else if (t.compareTo(node.val) > 0) node = node.right;
            else {
                System.out.println("val = " + t + ", count = " + node.count);
                node = new Node<>();
            }
        }
    }

    /**
     * Displays the whole tree in level order manner.
     */
    private void display() {
        int level = (int) Math.ceil(Math.log(size)/Math.log(2));
        if (level > 0) level--;

        LinkedList<Node<T>> stack = new LinkedList<>();
        stack.add(masterNode);
        while (!stack.isEmpty() && level-- >= 0) {
            int s = stack.size();
            for (int i = 0; i < s; i++) {
                Node<T> temp = stack.removeFirst();
                if (temp.val != null) System.out.printf("(%6S,%2d), ", temp.val, temp.count);
                else System.out.print("( Null ,00), ");
                if (temp.hasLeft())
                    stack.add(temp.left);
                if (temp.hasRight())
                    stack.add(temp.right);
            }
            System.out.println("\b\b");
        }
    }

    /*
        Some basic implementation of this class.
     */
    public static void main(String[] args) {
        AVLBstWithDuplicates<Integer> tree = new AVLBstWithDuplicates<>();

        for (int i=1; i<16; i++) tree.add(i);
        tree.display();
        for(int i = 0; i<16; i++)
            if (i != 8) tree.remove(i);
        tree.display();
        tree.remove(8);
        tree.display();
        tree.add(9);
        tree.add(8);
        tree.add(1);
        tree.add(15);
        tree.add(4);
        tree.add(10);
        tree.add(6);
        tree.add(7);
        tree.add(13);
        tree.add(3);
        tree.add(2);
        tree.add(11);
        tree.add(12);
        tree.add(14);
        tree.add(5);
        tree.display();
        for(int i=1; i<=15; i++) System.out.print(tree.remove(tree.getMAx()) +", ");
    }

    /**
     * This the implementation of the Node that stores the data and repeat count.
     * If the node val is null then the height of that node is -1.
     *
     */
    static public class Node<T extends Comparable<T>> {
        T val;
        int count;
        int height = -1;
        Node<T> left;
        Node<T> right;

        Node() {
            val = null;
            left = null;
            right = null;
        }

        Node(Node<T> node) {
            this(node.val, node.left, node.right, node.height, node.count);
        }

        Node(T val, Node<T> left, Node<T> right, int height, int count) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.height = height;
            this.count = count;
        }

        boolean hasLeft() {
            return left != null;
        }

        boolean hasRight() {
            return right != null;
        }
    }
}
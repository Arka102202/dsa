import java.util.LinkedList;

public class AVLBst<T extends Comparable<T>> {
    Node<T> masterNode;
    int size;
    boolean added, removed;

    AVLBst() {
        masterNode = new Node<>();
        size = 0;
    }

    public boolean add(T t) {
        added = false;
        if (size == 0) {
            added = true;
            masterNode = new Node<>(t, new Node<>(), new Node<>(), 0);
        } else add(t, masterNode);
        if(added) size++;
        return added;
    }

    private Node<T> add(T t, Node<T> node) {
        if (node.val == null) {
            added = true;
            node = new Node<>(t, new Node<>(), new Node<>(), 0);
            node.height = 0;
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
        else added = false;
        node.height = Math.max(node.left.height, node.right.height) + 1;
        return node;
    }

    private void rotateRight(Node<T> node) {
        Node<T> temp = new Node<>(node.val, node.left.right, node.right, Math.max(node.left.right.height, node.right.height) + 1);
        node.val = node.left.val;
        node.right = temp;
        node.left = node.left.left;
    }

    private void rotateLeft(Node<T> node) {
        Node<T> temp = new Node<>(node.val, node.left, node.right.left, Math.max(node.left.height, node.right.left.height) + 1);
        node.val = node.right.val;
        node.right = node.right.right;
        node.left = temp;
    }

    private void rightLeftRotate(Node<T> node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    private void leftRightRotate(Node<T> node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    /**
     *
     * to remove a node from the AVL tree
     *         step: 1 :
     *              find the node with the value you want to remove
     *         step: 2:
     *              then find the successor of that node.
     *              the steps to find the successor are:
     *                  a: check if the value is less than the master node, ==> if yes
     *                          then first go to the right and then left until you hit a node with "Null" left child.
     *                          then you replace the value of this node with value of the node that you want to delete
     *                          and the right child of this node becomes the left child of the previous node.
     *
     *                  b: check if the value is greater than the master node, ==> if yes
     *                          then first go to the left and then right until you hit a node with "Null" right child.
     *                          then you replace the value of this node with value of the node that you want to delete
     *                          and the left child of this node becomes the right child of the previous node.
     *
     *
     * @param t the value that has to be deleted.
     * @return T
     */
    public T remove(T t) {
        removed = false;
        remove(t, masterNode);
        if (removed) {
            size--;
            return t;
        } else return null;
    }

    private Node<T> remove(T t, Node<T> node) {
        if (node.val != null && node.val.equals(t)) {
            removed = true;
            Node<T> replacementNode = node;
            if (t.compareTo(masterNode.val) < 0) {
                if (node.right.val != null) node.right = findSuccessorLeft(node.right, replacementNode);
                else node = node.left;
            } else {
                if (node.left.val != null) node.left = findSuccessorRight(node.left, replacementNode);
                else node = node.right;
            }
            if (node.val != null) node.height = Math.max(node.left.height, node.right.height) + 1;
            return node;
        }

        if (node.val != null && t.compareTo(node.val) < 0) {
            node.left = remove(t, node.left);
            if (node.left.height - node.right.height == 2) rotateRight(node);
            else if (node.right.height - node.left.height == 2) rotateLeft(node);
        } else if (node.val != null) {
            node.right = remove(t, node.right);
            if (node.right.height - node.left.height == 2) rotateLeft(node);
            else if (node.left.height - node.right.height == 2) rotateRight(node);
        }
        if (node.val != null) node.height = Math.max(node.left.height, node.right.height) + 1;
        return node;
    }

    private Node<T> findSuccessorLeft(Node<T> node, Node<T> replacementNode) {
        if (node.left.val != null) node.left = findSuccessorLeft(node.left, replacementNode);
        else {
            replacementNode.val = node.val;
            return new Node<>(node.right);
        }
        if (node.left.height - node.right.height == 2) rotateRight(node);
        return node;
    }

    private Node<T> findSuccessorRight(Node<T> node, Node<T> replacementNode) {
        if (node.right.val != null) node.right = findSuccessorRight(node.right, replacementNode);
        else {
            replacementNode.val = node.val;
            return new Node<>(node.left);
        }
        if (node.right.height - node.left.height == 2) rotateLeft(node);
        return node;
    }

    private void display() {
        LinkedList<Node<T>> stack = new LinkedList<>();
        stack.add(masterNode);
        while (!stack.isEmpty()) {
            int s = stack.size();
            for (int i = 0; i < s; i++) {
                Node<T> temp = stack.removeFirst();
                if (temp.val != null) System.out.print(temp.val + ", ");
                if (temp.hasLeft())
                    stack.add(temp.left);
                if (temp.hasRight())
                    stack.add(temp.right);
            }
            System.out.println("\b\b");
        }
    }

    public static void main(String[] args) {

        AVLBst<Integer> tree = new AVLBst<>();
        for(int i=1; i<=15; i++)
            tree.add(i);

        if(tree.add(2)) System.out.println("added successfully");
        else System.out.println("duplicate");
        tree.display();
        tree.remove(4);
        System.out.println(tree.remove(7));
        tree.remove(5);
        tree.remove(6);
        tree.display();

    }


}

import java.util.LinkedList;

public class AVLBstWithDuplicates<T extends Comparable<T>> {
    Node<T> masterNode;
    int size;
    boolean added, removed;

    AVLBstWithDuplicates() {
        masterNode = new Node<>();
        size = 0;
    }

    public boolean add(T t) {
        added = false;
        if (size == 0) {
            added = true;
            masterNode = new Node<>(t, new Node<>(), new Node<>(), 0, 1);
        } else add(t, masterNode);
        if (added) size++;
        return added;
    }

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

    private void rightLeftRotate(Node<T> node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    private void leftRightRotate(Node<T> node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    public T remove(T t) {
        removed = false;
        remove(t, masterNode);
        if (removed) {
            size--;
//                System.out.println(t);
            return t;
        } else return null;
    }

    private Node<T> remove(T t, Node<T> node) {
        if (node.val != null && node.val.equals(t)) {
            if (node.count > 1) {
                node.count--;
                removed = true;
            } else {
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
            }
        }
        if (node.val != null && t.compareTo(node.val) < 0) {
            node.left = remove(t, node.left);
            if (node.right.height - node.left.height == 2) {
                if(node.right.right.val != null) rotateLeft(node);
                else if (node.right.left.val != null) rightLeftRotate(node);
            }
            else if (node.left.height - node.right.height == 2) {
                if (node.left.left.val != null) rotateRight(node);
                else if (node.left.right.val != null) leftRightRotate(node);
            }

        } else if (node.val != null) {
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

    private Node<T> findSuccessorLeft(Node<T> node, Node<T> replacementNode) {
        if (node.left.val != null) node.left = findSuccessorLeft(node.left, replacementNode);
        else {
            replacementNode.val = node.val;
            replacementNode.count = node.count;
            return new Node<>(node.right);
        }
        if (node.left.height - node.right.height == 2) rotateRight(node);
        else if (node.right.height - node.left.height == 2) rotateLeft(node);
        return node;
    }

    private Node<T> findSuccessorRight(Node<T> node, Node<T> replacementNode) {
        if (node.right.val != null) node.right = findSuccessorRight(node.right, replacementNode);
        else {
            replacementNode.val = node.val;
            replacementNode.count = node.count;
            return new Node<>(node.left);
        }
        if (node.left.height - node.right.height == 2) rotateRight(node);
        else if (node.right.height - node.left.height == 2) rotateLeft(node);
        return node;
    }

    public T getMAx() {
        if (masterNode != null && masterNode.right.val != null) {
            Node<T> node = masterNode.right;
            while (node.right.val != null)
                node = node.right;
            return node.val;
        } else return masterNode.val;
    }

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

    private void display() {
        LinkedList<Node<T>> stack = new LinkedList<>();
        stack.add(masterNode);
        while (!stack.isEmpty()) {
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

    public static void main(String[] args) {
        AVLBstWithDuplicates<Integer> tree = new AVLBstWithDuplicates<>();

        for (int i=1; i<16; i++) tree.add(i);
        tree.display();
        tree.remove(8);
        tree.display();
    }
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
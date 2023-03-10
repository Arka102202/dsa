import java.util.LinkedList;

public class FlattenBinaryTreeToLinkedList {
    static TreeNode masterNode;
    static TreeNode child;
    static LinkedList<TreeNode> list = new LinkedList<>();
    //    static void flatten(TreeNode root) {
//        masterNode = new TreeNode(root.val);
//        child = masterNode;
//        if (root.left != null) generate(root.left);
//        if (root.right != null) generate(root.right);
//        root = masterNode;
//        System.out.println();
//    }
    static void generate(TreeNode node) {
        child = child.right = new TreeNode(node.val);
        if (node.left != null) generate(node.left);
        if (node.right != null) generate(node.right);
    }
    static void flatten(TreeNode root) {
        list.clear();
        if(root != null){
            generate1(root);
            TreeNode prev = root;
            prev.left = null;
            for (int i = 1; i < list.size(); i++) prev = prev.right = list.get(i);
        }
    }
    static void generate1(TreeNode node) {
        list.add(new TreeNode(node.val));
        if (node.left != null) generate1(node.left);
        if (node.right != null) generate1(node.right);
    }

    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(20),
                n2 = new TreeNode(10),
                n3 = new TreeNode(30),
                n4 = new TreeNode(5),
                n5 = new TreeNode(15),
                n6 = new TreeNode(25),
                n7 = new TreeNode(35),
                n8 = new TreeNode(6),
                n9 = new TreeNode(14),
                n10 = new TreeNode(24),
                n11 = new TreeNode(100);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.right = n8;
        n5.left = n9;
        n6.left = n10;
        n7.right = n11;
        flatten(n1);
        System.out.println();

        n1 = new TreeNode(1);
        n2 = new TreeNode(2);
        n3 = new TreeNode(5);
        n4 = new TreeNode(3);
        n5 = new TreeNode(4);
        n7 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = null;
        n3.right = n7;
        flatten(n1);
        System.out.println();

    }
}

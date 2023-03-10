public class DeleteNodeInABST {
    static TreeNode masterNode;
    static boolean found = false;
    static TreeNode deleteNode(TreeNode root, int key) {
        found = false;
        masterNode = root;
        if(root != null) masterNode = findNode(root,key);
        return masterNode;
    }
    static TreeNode findNode(TreeNode root, int key){
        if(root.val == key){
            found = true;
            if(root.right != null) root.right = findReplacement(root.right,root);
            else root = root.left;
            return root;
        }
        if(root.left != null) {
            root.left = findNode(root.left, key);
            if(found) return root;
        }
        if (root.right != null) {
            root.right = findNode(root.right, key);
            if(found) return root;
        }
        return root;
    }
    static TreeNode findReplacement(TreeNode root, TreeNode replacementNode){
        if(root.left != null) root.left = findReplacement(root.left, replacementNode);
        else {
            replacementNode.val = root.val;
            return root.right;
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(20),
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

        root.left = n2;
        root.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n4.right = n8;
        n5.left = n9;
        n6.left = n10;
        n7.right = n11;

        root = deleteNode(root, 100);
        System.out.println();
        deleteNode(root, 10);
        System.out.println();
        deleteNode(n11, 100);
        System.out.println();
        root = new TreeNode(4);
                n2 = new TreeNode(7);
                n3 = new TreeNode(6);
                n4 = new TreeNode(8);
                n5 = new TreeNode(5);
                n6 = new TreeNode(9);

        root.left = null;
        root.right = n2;
        n2.left = n3;
        n2.right = n4;
        n3.left = n5;
        n4.right = n6;

        deleteNode(root, 7);
        System.out.println();



    }


}

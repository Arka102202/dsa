import java.util.LinkedList;
public class BSTIterator {
    LinkedList<TreeNode> stack = new LinkedList<>();
    public BSTIterator(TreeNode root) {
        stack.add(root);
        TreeNode node = root;
        while(node.left != null){
            TreeNode lNode = node.left;
            node.left = null;
            stack.push(lNode);
            node = lNode;
        }
    }
    public int next() {
        if(stack.size() > 0){
            TreeNode node = stack.removeFirst();
            int val = node.val;
            if(node.right != null) {
                TreeNode rNode = node.right;
                node.right = null;
                stack.push(rNode);
            }
            if(stack.size() > 0) node = stack.getFirst();
            while(node.left != null){
                TreeNode lNode = node.left;
                node.left = null;
                stack.push(lNode);
                node = lNode;
            }
            return val;
        }
        else return -1;
    }
    public boolean hasNext() {
        return stack.size()>0;
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
        BSTIterator bSTIterator = new BSTIterator(root);

        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());
        System.out.println(bSTIterator.next());
        System.out.println(bSTIterator.hasNext());










    }
}

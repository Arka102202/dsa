import java.util.*;
public class MergeBSTsToCreateSingleBST {
    static LinkedList<Integer> indices = new LinkedList<>();
    static HashMap<Integer, TreeNode> map = new HashMap<>();
    static int len, opCount = 0;
    static TreeNode masterNode;
    static TreeNode canMerge(List<TreeNode> trees) {
        indices.clear();
        map.clear();
        TreeNode[] array = new TreeNode[trees.size()];
        int i = 0;
        for (TreeNode node : trees) {
            map.put(node.val, node);
            array[i++] = node;
        }
        len = trees.size();
        for (i = 0; i < len; i++) {
            opCount = 0;
            masterNode = new TreeNode(array[i].val, array[i].left, array[i].right);
            indices.add(masterNode.val);
            if (canCreate(masterNode, true)) return masterNode;
            indices.clear();
            masterNode = new TreeNode();
        }
        return null;
    }
    static boolean canCreate(TreeNode treeNode, boolean left) {
        if (opCount == len - 1) return indices.size() == len;
        TreeNode node = opCount == 0 ? masterNode : treeNode;
        if (node.left != null) {
            if (node.equals(masterNode)) left = true;
            TreeNode child = map.get(node.left.val);
            if (child != null) {
                if ((child.right == null || child.right.val < node.val) &&
                        ((left && (child.left == null || child.left.val < masterNode.val) ||
                                (!left && (child.left == null || child.left.val > masterNode.val))))) {
                    treeNode.left = child;
                    opCount += 1;
                    indices.add(node.left.val);
                    if (canCreate(child, left)) return true;
                }
            } else return false;
        }
        if (node.right != null) {
            if (node.equals(masterNode)) left = false;
            TreeNode child = map.get(node.right.val);
            if (child != null) {
                if ((child.left == null || child.left.val > node.val) &&
                        ((left && (child.right == null || child.right.val < masterNode.val)) ||
                                (!left && (child.right == null || child.right.val > masterNode.val)))) {
                    treeNode.right = child;
                    opCount += 1;
                    indices.add(node.right.val);
                    return canCreate(child, left);
                }
            } else return false;
        }
        return false;
    }
    static void display() {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(masterNode);
        while (!stack.isEmpty()) {
            int s = stack.size();
            for (int i = 0; i < s; i++) {
                TreeNode temp = stack.removeFirst();
                if (temp != null) {
                    System.out.print(temp.val + ", ");
                    if (temp.left != null)
                        stack.add(temp.left);
                    else System.out.print("null, ");
                    if (temp.right != null)
                        stack.add(temp.right);
                    else System.out.print("null, ");
                } else System.out.print("null, ");
            }
        }
        System.out.println();
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            int s = stack.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < s; i++) {
                TreeNode temp = stack.removeFirst();
                if (temp != null) {
                    list.add(temp.val);
                    if (temp.left != null)
                        stack.add(temp.left);
                    if (temp.right != null)
                        stack.add(temp.right);
                }
            }
            lists.add(list);
        }
        return lists;
    }
    static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        LinkedList<TreeNode> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
        stack2.add(root);
        int s;
        while (true){
            List<Integer> list = new ArrayList<>();
            if ((lists.size() & 1) == 0) {
                s = stack2.size();
                for (int i = 0; i < s; i++) {
                    TreeNode temp = stack2.removeFirst();
                    if (temp != null) {
                        list.add(temp.val);
                        if (temp.left != null)
                            stack1.add(temp.left);
                        if (temp.right != null)
                            stack1.add(temp.right);
                    }
                }
            } else {
                s = stack1.size();
                for (int i = 0; i < s; i++) {
                    TreeNode temp = stack1.removeLast();
                    if (temp != null) {
                        list.add(temp.val);
                        if (temp.right != null)
                            stack2.push(temp.right);
                        if (temp.left != null)
                            stack2.push(temp.left);
                    }
                }
            }
            if(list.size() == 0) break;
            else lists.add(list);
        }
        return lists;
    }



    public static void main(String[] args) {


        TreeNode node = new TreeNode(3,new TreeNode(9), new TreeNode(20,new TreeNode(15), new TreeNode(7)));
        System.out.println(zigzagLevelOrder(node));
        node = new TreeNode(1,new TreeNode(2, new TreeNode(4), null), new TreeNode(3,null, new TreeNode(5)));
        System.out.println(zigzagLevelOrder(node));



        TreeNode t01 = new TreeNode(2);
        t01.left = new TreeNode(1);
        TreeNode t12 = new TreeNode(2), t13 = new TreeNode(5);
        TreeNode t11 = new TreeNode(3, t12, t13);
        TreeNode t21 = new TreeNode(5);
        t21.left = new TreeNode(4);
        List<TreeNode> trees = List.of(t01, t11, t21);
        canMerge(trees);
        display();

        t01 = new TreeNode(10);
        t01.left = new TreeNode(9);
        t12 = new TreeNode(8);
        t11 = new TreeNode(9,t12,null);
        t21 = new TreeNode(8);
        t21.left = new TreeNode(7);
        trees = List.of(t01,t11,t21);
        canMerge(trees);
        display();

        t01 = new TreeNode(1);
        t01.right = new TreeNode(4);
        t12 = new TreeNode(3);
        t11 = new TreeNode(4, t12, null);
        t21 = new TreeNode(3);
        t21.left = new TreeNode(2);
        trees = List.of(t01, t11, t21);
        canMerge(trees);
        display();


        trees = List.of(new TreeNode(20, new TreeNode(10, null, null), new TreeNode(30, null, null)),
                new TreeNode(10, new TreeNode(5, null, null), new TreeNode(15, null, null)),
                new TreeNode(5, null, new TreeNode(6, null, null)),
                new TreeNode(30, new TreeNode(25, null, null), new TreeNode(35, null, null)),
                new TreeNode(35, null, new TreeNode(100, null, null)),
                new TreeNode(15, new TreeNode(14, null, null), null),
                new TreeNode(25, new TreeNode(24, null, null), null));
        canMerge(trees);
        display();

        trees = List.of(new TreeNode(1, null, new TreeNode(2, null, null)),
                new TreeNode(2, null, new TreeNode(3, null, null)),
                new TreeNode(3, new TreeNode(1, null, null), null));
        canMerge(trees);
        display();

    }
}



//static HashSet<Integer> indices = new HashSet<>();
//    static int len, opCount = 0;
//    static TreeNode masterNode;
//    static boolean ended;
//    static TreeNode canMerge(List<TreeNode> trees) {
//        len = trees.size();
//        TreeNode[] treeArr = trees.toArray(new TreeNode[0]);
//        for (int i = 0; i < len; i++) {
//            opCount = 0;
//            ended = false;
//            TreeNode node = treeArr[i];
//            indices.add(i);
//            masterNode = new TreeNode(node.val, node.left, node.right);
//            if (canCreate(treeArr, masterNode, true)) return masterNode;
//            masterNode = new TreeNode();
//            indices.clear();
//        }
//        return null;
//    }
//    static boolean canCreate(TreeNode[] treeArr, TreeNode treeNode, boolean left) {
//        if (opCount == len - 1) {
//            ended = true;
//            return indices.size() == len;
//        }
//        TreeNode node = opCount == 0 ? masterNode : treeNode;
//        if (node.left != null) {
//            if (node.equals(masterNode)) left = true;
//            TreeNode leftNode = new TreeNode(node.left.val, node.left.left, node.left.right);
//            int p = indexOf(leftNode, treeArr);
//            if (p >= 0) {
//                TreeNode child = treeArr[p];
//                if ((child.right == null || child.right.val < node.val) &&
//                        ((left && (child.left == null || child.left.val < masterNode.val) ||
//                                (!left && (child.left == null || child.left.val > masterNode.val))))) {
//                    treeNode.left = child;
//                    opCount += 1;
//                    indices.add(p);
//                    if (canCreate(treeArr, child, left)) return true;
//                    if(ended) return indices.size() == len;
//                }
//            } else return false;
//        }
//        if (node.right != null) {
//            if (node.equals(masterNode)) left = false;
//            TreeNode rightNode = new TreeNode(node.right.val, node.right.left, node.right.right);
//            int p = indexOf(rightNode, treeArr);
//            if (p >= 0) {
//                TreeNode child = treeArr[p];
//                if ((child.left == null || child.left.val > node.val) &&
//                        ((left && (child.right == null || child.right.val < masterNode.val)) ||
//                                (!left && (child.right == null || child.right.val > masterNode.val)))) {
//                    treeNode.right = child;
//                    opCount += 1;
//                    indices.add(p);
//                    if (canCreate(treeArr, child, left)) return true;
//                    if(ended) return indices.size() == len;
//                }
//            } else return false;
//        }
//        return false;
//    }
//    static int indexOf(TreeNode node, TreeNode[] treeArr){
//        for(int i=0; i<len; i++)
//            if(node.val == treeArr[i].val) return i;
//        return -1;
//    }
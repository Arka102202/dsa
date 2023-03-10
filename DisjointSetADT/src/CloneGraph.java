import java.util.*;

public class CloneGraph {

    public static Node cloneGraph(Node node) {
        if(node == null) return null;
        Node copy = new Node(node.val);
        Node[] visited = new Node[101];
        visited[copy.val] = copy;
        return dfs(node,visited,copy);
    }

    public static Node dfs(Node node, Node[] visited, Node copy){
        List<Node> neighbour = node.neighbors;
        for(int i=0; i<neighbour.size(); i++){
            Node n = neighbour.get(i);
            if(visited[n.val] == null){
                Node newNode = new Node(n.val);;
                visited[n.val] = newNode;
                copy.neighbors.add(dfs(n,visited, newNode));
            }
            else copy.neighbors.add(visited[n.val]);
        }
        return copy;
    }

    public static Node deepCopyBFS(Node node) {
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<List<Node>> stack2 = new LinkedList<>();
        HashMap<Integer, Node> visited = new HashMap<>();
        Node copy = new Node(node.val);
        stack1.add(copy);
        visited.put(copy.val, copy);
        stack2.add(node.neighbors);
        while(stack1.size() > 0) {
            for(int i=0; i<stack1.size(); i++){
                Node r = stack1.removeFirst();
                List<Node> list1 = r.neighbors;

                List<Node> list2 = stack2.removeFirst();

                for(int j = 0; j<list2.size(); j++){

                    int val = list2.get(j).val;

                    Node n = new Node(val);

                    if(!visited.containsKey(val)){
                        list1.add(n);
                        stack1.addLast(n);
                        stack2.addLast(list2.get(j).neighbors);
                    }else list1.add(visited.get(val));
                }

                visited.put(r.val,r);
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors = new ArrayList<>(List.of(n2,n4));
        n2.neighbors = new ArrayList<>(List.of(n1,n3));
        n3.neighbors = new ArrayList<>(List.of(n2,n4));
        n4.neighbors = new ArrayList<>(List.of(n1,n3));

        Node n = cloneGraph(n1);
        System.out.println(n == n1);

        n1 = new Node(1);
        n2 = new Node(2);

        n1.neighbors = new ArrayList<>(List.of(n2));
        n2.neighbors = new ArrayList<>(List.of(n1));

        n = cloneGraph(n1);
        System.out.println(n == n1);

    }


}

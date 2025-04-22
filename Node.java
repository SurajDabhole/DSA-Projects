public class Node implements Comparable<Node>{
    char data;
    int cost;
    Node left,right;

    public Node(char data,int cost){
        this.data = data;
        this.cost = cost;
        this.left = null;        
        this.right = null;        
    }

    @Override
    public int compareTo(Node other){
        return this.cost - other.cost;
    }
}
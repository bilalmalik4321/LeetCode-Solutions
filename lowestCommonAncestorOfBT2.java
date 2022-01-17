import java.util.ArrayList;
import java.util.List;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> parentsOfP = new ArrayList<>();
        
        while(p!=null){
            parentsOfP.add(p);
            p=p.parent;
        }
        
        while(q!=null && !parentsOfP.contains(q)){
            q = q.parent;
        }
        
        return q;
        
    }
}
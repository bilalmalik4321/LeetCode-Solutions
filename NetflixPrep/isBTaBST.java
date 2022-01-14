package NetflixPrep;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
    Given a Binary Tree, figure out whether it’s a Binary Search Tree. 
    In a binary search tree, each node’s key value is smaller than the key value of 
    all nodes in the right subtree, and are greater than the key values of all nodes 
    in the left subtree i.e. L < N < RL<N<R.
*/


public class isBTaBST {

    List<Integer> treeTraversalVals  = new ArrayList<>();

    private void dfs(BinaryTreeNode node){
        if(node.left!=null)dfs(node.left);

        treeTraversalVals.add(node.val);

        if(node.right!=null)dfs(node.right);
    }

    private boolean checkIfSorted(){
        if(treeTraversalVals.isEmpty() || treeTraversalVals.size()==1)return true;

        Iterator<Integer> iter = treeTraversalVals.iterator();
        int current, previous = iter.next();
        
        while(iter.hasNext()){
            current = iter.next();
            if(previous > current)return false;

            previous = current;
        }

        return true;
    }

    public boolean is_bst(BinaryTreeNode root) {
    
        dfs(root);

        return checkIfSorted();
    }
    
    class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

}

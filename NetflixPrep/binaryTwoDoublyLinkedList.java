package NetflixPrep;

/*
Convert a binary tree to a doubly linked list so that the order of
the doubly linked list is the same as an in-order traversal of the binary tree. 
After conversion, the left pointer of the node should be pointing to the 
previous node in the doubly linked list, and the right pointer should be 
pointing to the next node in the doubly linked list. 
*/

public class binaryTwoDoublyLinkedList {
    
    BinaryTreeNode head;
    BinaryTreeNode pre;

    //in order traversal
    public void dfs(BinaryTreeNode node){
        if(node.left!=null)dfs(node.left);

        if(head==null){
            head = node;
        }
        else {
            pre.right = node;
            node.left = pre;
        }

        pre  = node;

        if(node.right!=null)dfs(node.right);
    }

    public BinaryTreeNode convert_to_linked_list(BinaryTreeNode root) {
        
        dfs(root);
        head.left = null;
        pre.right = null;

        return head;
    }

  class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;
  }

}

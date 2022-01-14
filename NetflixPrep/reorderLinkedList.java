// Asked to reorder a linked list with all the odd indexed nodes preceeding the evens. 
package NetflixPrep;

import java.util.ArrayList;
import java.util.List;

class ReorderLinkedList{

    List<LinkedListNode> odd = new ArrayList<>();
    List<LinkedListNode> even = new ArrayList<>();

    public LinkedListNode solution(LinkedListNode head){
        search(head,0);

        if(head==null)return null;

        LinkedListNode pre = null;
        LinkedListNode newHead = null;

        for(int i=0;i<odd.size();i++){
            LinkedListNode node = odd.get(i);
            if(pre!=null){
                pre.next = node;
                newHead = node;
            }
            pre = node;
        }

        for(int i=0;i<even.size();i++){
            LinkedListNode node = even.get(i);
            if(pre!=null)pre.next = node;
            pre = node;
        }

        if(newHead==null) return pre;
        
        return newHead;

    }

    void search(LinkedListNode node, int index){
        if(node==null)return;

        if(index%2==0)even.add(node);
        else odd.add(node);

        search(node.next, index+1);
    }

}

/*
 0    1    2    3     4     5
 2 -> 4 -> 5 -> 12 -> 13 -> 1

 4 -> 12 -> 1 -> 2 -> 5 -> 13
*/

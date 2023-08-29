public class mergeKSortedLists {
    /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a,b)->a.val - b.val
        );
        
        for(ListNode list : lists){
            if(list!=null) minHeap.add(list);
        }
        
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(!minHeap.isEmpty()){
            ListNode node = minHeap.remove();
            curr.next = node;
            curr = node;
            if(node.next != null) minHeap.add(node.next);
        }
        
        return dummy.next;
    }
}

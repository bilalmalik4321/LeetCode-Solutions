// Runtime: 2 ms, faster than 77.36% of Java online submissions for Add Two Numbers.
// Memory Usage: 39.4 MB, less than 38.91% of Java online submissions for Add Two Numbers.


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
    
    public boolean carry = false;
    
    public ListNode checkSum(ListNode listNode, ListNode l1, ListNode l2){
               int sum = 0;
               sum = l1.val+l2.val;
            
               if(carry){
                  carry=false;
                  sum += 1;
                }

                //carry logic             
                if(sum >= 10){
                  carry=true;
                  int digit=Integer.parseInt(Integer.toString(sum).substring(1, 2));
                  sum = digit;
                }


                listNode.val = sum;

                l1=l1.next;
                l2=l2.next;
            
                
                if(l1 != null && l2 != null){
                    ListNode listNodeNext = new ListNode();
                    listNode.next = listNodeNext;
                    checkSum(listNodeNext, l1, l2);
                }
                else if(l1 != null && l2 == null){
                    ListNode listNodeNext = new ListNode();
                    listNode.next = listNodeNext;
                    
                    ListNode addedZeroNode = new ListNode(0);
                    checkSum(listNodeNext, l1, addedZeroNode);
                }
                else if(l1 == null && l2 != null){
                    ListNode listNodeNext = new ListNode();
                    listNode.next = listNodeNext;
                    
                    ListNode addedZeroNode = new ListNode(0);
                    checkSum(listNodeNext, addedZeroNode, l2);
                }
                else if(l1 == null && l2 == null && carry){
                    ListNode listNodeNext = new ListNode(1);
                    listNode.next = listNodeNext;
                }
            
                return listNode;
            
        }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        return checkSum(new ListNode(), l1, l2);
        
    }
}

package NetflixPrep;
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

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
        ListNode head = new ListNode();
        ListNode node = head;
        ListNode lastRef = new ListNode();

        while(l1!=null || l2!=null){
            int sum = 0;
            
            if(l1!=null)sum+=l1.val;
            if(l2!=null)sum+=l2.val;

            if(carry){
                sum++;
                carry = false;
            }

            if(sum > 9){
                carry = true;
                sum%=10;
            }

            node.val = sum;
            ListNode next = new ListNode();
            node.next = next;
            lastRef = node;
            node = next;

            if(l1!=null)l1 = l1.next;
            if(l2!=null)l2 = l2.next;
        }

        lastRef.next = null;

        if(carry){
            ListNode last = new ListNode(1);
            lastRef.next = last;
        }

        return head;

    }
}

/*
l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
                   |                     |

carry = true
head = {8}
node = {}

next = {}
sum = 0
lastRef = {0}
last = {1}

[8,9,9,9,0,0,0,1]

*/

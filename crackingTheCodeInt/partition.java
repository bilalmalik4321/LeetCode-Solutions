package crackingTheCodeInt;

class Solution {

    class ListNode{
        int val;
        ListNode next;
    }

    public ListNode partition(ListNode head, int x){
        ListNode node = head;
        ListNode pre = new ListNode();

        while(node!=null){
            if(node.val < x && node != head){
                ListNode next = node.next;
                pre.next = next;
                node.next = head;
                head = node;
                node = next;
            }
            else{
                pre = node;
                node = node.next;
            }
        }

        return head;
    }
}

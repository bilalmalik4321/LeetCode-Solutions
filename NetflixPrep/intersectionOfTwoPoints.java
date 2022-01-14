package NetflixPrep;

import java.util.ArrayList;
import java.util.List;

class Intersect{
    public static LinkedListNode intersect(LinkedListNode head1,LinkedListNode head2) {
  
        List<LinkedListNode> listVal = new ArrayList<>();
  
        while(head1!=null){
          listVal.add(head1);
          head1 = head1.next;
        }
  
        while(head2!=null && !listVal.contains(head2)){
          head2 = head2.next;
        }
  
        return head2;
    } 
  } 

  class LinkedListNode{
      int val;
      LinkedListNode next;
  }


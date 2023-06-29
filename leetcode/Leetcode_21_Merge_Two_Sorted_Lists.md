> https://leetcode.com/problems/merge-two-sorted-lists/

![img.png](image/21_성능.png)

---
~~~java
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode answer = new ListNode();
        ListNode current = answer;
        ListNode temp1 = list1;
        ListNode temp2 = list2;

        while (temp1 != null || temp2 != null) {
            if (temp1 == null) {
                current.next = new ListNode(temp2.val);
                temp2 = temp2.next;
            } else if (temp2 == null) {
                current.next = new ListNode(temp1.val);
                temp1 = temp1.next;
            } else {

                if (temp1.val < temp2.val) {
                    current.next = new ListNode(temp1.val);
                    temp1 = temp1.next;
                } else {
                    current.next = new ListNode(temp2.val);
                    temp2 = temp2.next;
                }
            }

            current = current.next;
        }

        return answer.next;
    }
}
~~~
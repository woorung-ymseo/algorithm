import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.SortedMap;
import java.util.stream.Stream;

public class Leetcode_21_Merge_Two_Sorted_Lists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    @Test
    public void mergeTwoLists() {
        ListNode list1 = null;
        ListNode list2 = null;

//        ListNode list1 = null;
//        ListNode list2 = new ListNode(0);
//
//        ListNode list1 = new ListNode(1);
//        list1.next = new ListNode(2);
//        list1.next.next = new ListNode(4);
//
//        ListNode list2 = new ListNode(1);
//        list2.next = new ListNode(3);
//        list2.next.next = new ListNode(4);

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

        System.out.println(answer.next);
    }

}

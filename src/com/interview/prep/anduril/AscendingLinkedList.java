package com.interview.prep.anduril;

public class AscendingLinkedList {

    // Definition of the ListNode class
    public static class ListNode {
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;

    /**
     * Inserts a value into a sorted singly linked list in ascending order.
     *
     * Approach:
     * - Traverse the list to find the correct position to insert the new value.
     * - If the new value is smaller than the head, update the head.
     * - Otherwise, insert the value between the appropriate nodes.
     *
     * Time Complexity:
     * - O(n), where n is the number of nodes in the list.
     *
     * Space Complexity:
     * - O(1), as no additional space is used.
     */
    public ListNode insertToList(int val) {
        ListNode node = new ListNode(val);
        if (head == null) { // Case when the list is empty
            head = node;
            return head;
        }

        ListNode current = head;
        ListNode prev = null;
        // Traverse the list to find the correct position
        while (current != null && current.val < val) {
            prev = current;
            current = current.next;
        }

        if (prev == null) { // Insert at the beginning
            node.next = head;
            head = node;
        } else { // Insert in the middle or end
            prev.next = node;
            node.next = current;
        }

        return head;
    }

    /**
     * Inserts a value into a sorted circular singly linked list in ascending order.
     *
     * Approach:
     * - If the list is empty, create a new node that points to itself.
     * - If the new value is smaller than or equal to the head, update the head and adjust pointers.
     * - Otherwise, traverse the list and insert the value in the correct position.
     *
     * Time Complexity:
     * - O(n), where n is the number of nodes in the list.
     *
     * Space Complexity:
     * - O(1), as no additional space is used.
     */
    public ListNode insertToCircularList(int val) {
        ListNode node = new ListNode(val);

        // Case when the list is empty
        if (head == null) {
            head = node;
            node.next = head;
            return head;
        }

        // Case when the new value is smaller than or equal to the head
        if (head.val >= val) {
            ListNode last = head;
            // Find the last node in the circular list
            while (last.next != head) {
                last = last.next;
            }
            last.next = node; // Update the last node's next pointer
            node.next = head; // Point the new node to the current head
            head = node; // Update the head to the new node
            return head;
        }

        // Case when inserting in the middle or end
        ListNode current = head;
        while (current.next != head && current.next.val < val) {
            current = current.next;
        }
        node.next = current.next; // Point the new node to the next node
        current.next = node; // Update the current node's next pointer

        return head;
    }
}
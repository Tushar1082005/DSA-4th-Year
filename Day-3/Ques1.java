import java.util.Scanner;

public class Ques1 {
    // Definition for singly-linked list node
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // A dummy node helps handle edge cases like removing the head
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            
            ListNode fast = dummy;
            ListNode slow = dummy;

            // Move fast pointer so that the gap between slow and fast is n nodes
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }

            // Move both until fast reaches the end
            // Slow will then be right before the node to be deleted
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            // Skip the n-th node
            slow.next = slow.next.next;

            return dummy.next;
        }
    }

    // Example usage with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution sol = new Solution();
        
        // Take input from user
        System.out.print("Enter the number of nodes: ");
        int numNodes = sc.nextInt();
        
        System.out.println("Enter the values of the nodes:");
        ListNode head = null;
        ListNode current = null;
        
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Node " + (i + 1) + ": ");
            int val = sc.nextInt();
            
            if (head == null) {
                head = new ListNode(val);
                current = head;
            } else {
                current.next = new ListNode(val);
                current = current.next;
            }
        }
        
        System.out.print("Enter the position (n) to remove from end: ");
        int n = sc.nextInt();
        
        ListNode result = sol.removeNthFromEnd(head, n);
        
        // Print result
        System.out.print("Result: ");
        if (result == null) {
            System.out.println("List is empty");
        } else {
            while (result != null) {
                System.out.print(result.val + (result.next != null ? " -> " : ""));
                result = result.next;
            }
            System.out.println();
        }
        
        sc.close();
    }
}

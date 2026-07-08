import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int val) {
        data = val;
        next = null;
    }
}

public class Ques2 {

    public static Node add(Node head, Node head1) {
        // Create a dummy node to act as the head of the result list
        Node d = new Node(0);
        Node t = d;
        int c = 0; // carry

        // Loop as long as there are nodes to process or a carry remains
        while (head != null || head1 != null || c > 0) {
            int s = c; // start with the previous carry
            
            if (head != null) {
                s += head.data;
                head = head.next;
            }
            
            if (head1 != null) {
                s += head1.data;
                head1 = head1.next;
            }
            
            // Create a new node with the single-digit sum
            t.next = new Node(s % 10);
            t = t.next; // move the tail pointer
            
            // Calculate the new carry
            c = s / 10;
        }
        
        // Return the actual head of the result list, skipping the dummy node
        return d.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method with user input
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input for List 1
        System.out.print("Enter the number of nodes in List 1: ");
        int n1 = sc.nextInt();
        Node head = null;
        Node current = null;
        
        System.out.println("Enter the values for List 1:");
        for (int i = 0; i < n1; i++) {
            System.out.print("Node " + (i + 1) + ": ");
            int val = sc.nextInt();
            if (head == null) {
                head = new Node(val);
                current = head;
            } else {
                current.next = new Node(val);
                current = current.next;
            }
        }
        
        // Input for List 2
        System.out.print("Enter the number of nodes in List 2: ");
        int n2 = sc.nextInt();
        Node head1 = null;
        current = null;
        
        System.out.println("Enter the values for List 2:");
        for (int i = 0; i < n2; i++) {
            System.out.print("Node " + (i + 1) + ": ");
            int val = sc.nextInt();
            if (head1 == null) {
                head1 = new Node(val);
                current = head1;
            } else {
                current.next = new Node(val);
                current = current.next;
            }
        }
        
        System.out.print("Input List 1: ");
        printList(head);
        System.out.print("Input List 2: ");
        printList(head1);
        
        Node result = add(head, head1);
        System.out.print("Sum List:     ");
        printList(result);
        
        sc.close();
    }
}

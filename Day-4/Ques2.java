import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Ques2 {
    public static int shortestSubarray(int[] nums, int k) {
        long[] prefix = new long[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int answer = nums.length + 1;

        for (int i = 0; i < prefix.length; i++) {
            while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
                answer = Math.min(answer, i - deque.pollFirst());
            }

            while (!deque.isEmpty() && prefix[i] <= prefix[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return answer == nums.length + 1 ? -1 : answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of elements:");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println("Enter K:");
        int k = scanner.nextInt();

        int result = shortestSubarray(nums, k);
        System.out.println(result);

        scanner.close();
    }
}
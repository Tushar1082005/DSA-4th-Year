import java.util.Scanner;

public class Ques1 {

	public static int minJumps(int[] nums) {
		int n = nums.length;

		if (n <= 1) {
			return 0;
		}

		if (nums[0] == 0) {
			return -1;
		}

		int jumps = 0;
		int currentEnd = 0;
		int farthest = 0;

		for (int i = 0; i < n - 1; i++) {
			farthest = Math.max(farthest, i + nums[i]);

			if (i == currentEnd) {
				if (farthest == currentEnd) {
					return -1;
				}

				jumps++;
				currentEnd = farthest;

				if (currentEnd >= n - 1) {
					return jumps;
				}
			}
		}

		return currentEnd >= n - 1 ? jumps : -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of elements: ");
		int n = scanner.nextInt();

		int[] nums = new int[n];
		System.out.println("Enter the elements:");
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
		}

		int result = minJumps(nums);
		System.out.println("Minimum jumps: " + result);

		scanner.close();
	}
}

import java.util.Arrays;
import java.util.Scanner;

public class Ques2 {

	public static int candy(int[] nums) {
		int n = nums.length;

		if (n == 0) {
			return 0;
		}

		int[] candies = new int[n];

		Arrays.fill(candies, 1);

		for (int i = 1; i < n; i++) {
			if (nums[i] > nums[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}

		for (int i = n - 2; i >= 0; i--) {
			if (nums[i] > nums[i + 1]) {
				candies[i] = Math.max(candies[i], candies[i + 1] + 1);
			}
		}

		int sum = 0;
		for (int c : candies) {
			sum += c;
		}

		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of children: ");
		int n = scanner.nextInt();

		int[] nums = new int[n];
		System.out.println("Enter the ratings:");
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
		}

		int result = candy(nums);
		System.out.println("Minimum candies needed: " + result);

		scanner.close();
	}
}

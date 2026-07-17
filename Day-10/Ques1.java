import java.util.Scanner;

public class Ques1 {

	public static long maximumSumSubarray(long[] nums, long k) {
		int n = nums.length;

		if (k > n) {
			return 0;
		}

		long windowSum = 0;

		// Calculate sum of first window
		for (int i = 0; i < k; i++) {
			windowSum += nums[i];
		}

		long maxSum = windowSum;

		// Slide the window
		for (int i = (int) k; i < n; i++) {
			windowSum += nums[i];
			windowSum -= nums[i - (int) k];
			maxSum = Math.max(maxSum, windowSum);
		}

		return maxSum;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		long k = sc.nextLong();

		long[] nums = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextLong();
		}

		long result = maximumSumSubarray(nums, k);
		System.out.println(result);

		sc.close();
	}
}

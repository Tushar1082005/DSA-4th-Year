import java.util.Scanner;

public class Ques2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			sc.close();
			return;
		}

		int n = sc.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}

		int lower = sc.nextInt();
		int upper = sc.nextInt();

		Ques2 obj = new Ques2();
		System.out.println(obj.countRangeSum(nums, lower, upper));

		sc.close();
	}

	public int countRangeSum(int[] nums, int lower, int upper) {
		long[] prefix = new long[nums.length + 1];

		for (int i = 0; i < nums.length; i++) {
			prefix[i + 1] = prefix[i] + nums[i];
		}

		return (int) mergeSort(prefix, 0, prefix.length - 1, lower, upper);
	}

	private long mergeSort(long[] sum, int left, int right, int lower, int upper) {
		if (left >= right) {
			return 0;
		}

		int mid = (left + right) / 2;

		long count = mergeSort(sum, left, mid, lower, upper)
				   + mergeSort(sum, mid + 1, right, lower, upper);

		int l = mid + 1;
		int r = mid + 1;

		for (int i = left; i <= mid; i++) {
			while (l <= right && sum[l] - sum[i] < lower) {
				l++;
			}
			while (r <= right && sum[r] - sum[i] <= upper) {
				r++;
			}
			count += r - l;
		}

		long[] temp = new long[right - left + 1];
		int i = left;
		int j = mid + 1;
		int k = 0;

		while (i <= mid && j <= right) {
			if (sum[i] <= sum[j]) {
				temp[k++] = sum[i++];
			} else {
				temp[k++] = sum[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = sum[i++];
		}
		while (j <= right) {
			temp[k++] = sum[j++];
		}

		System.arraycopy(temp, 0, sum, left, temp.length);

		return count;
	}
}

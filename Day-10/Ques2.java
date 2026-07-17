import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ques2 {

	public List<Integer> solve(int N, int[] arr, int target) {
		List<Integer> ans = new ArrayList<>();

		int start = 0;
		int sum = 0;

		for (int end = 0; end < N; end++) {
			sum += arr[end];

			while (sum > target && start <= end) {
				sum -= arr[start];
				start++;
			}

			if (sum == target) {
				ans.add(start + 1); // 1-based index
				ans.add(end + 1);   // 1-based index
				return ans;
			}
		}

		ans.add(-1);
		return ans;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int target = sc.nextInt();

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		Ques2 obj = new Ques2();
		List<Integer> result = obj.solve(N, arr, target);

		if (result.size() == 1 && result.get(0) == -1) {
			System.out.println(-1);
		} else {
			System.out.println(result.get(0) + " " + result.get(1));
		}

		sc.close();
	}
}

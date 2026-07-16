import java.util.Scanner;

public class Ques2 {

	static class Solution {

		public int minRemovals(int n, int m, int[] arrN, int[] arrM) {

			int[] k = arrM;
			int[] cnt = new int[m + 1];

			int sumK = 0;
			for (int x : k) {
				sumK += x;
			}

			int preSat = 0;
			for (int i = 0; i < m; i++) {
				if (k[i] == 0) {
					preSat++;
				}
			}

			int left = 0, satisfied = preSat, minLen = Integer.MAX_VALUE;

			for (int right = 0; right < n; right++) {

				int c = arrN[right];
				cnt[c]++;

				if (cnt[c] == k[c - 1]) {
					satisfied++;
				}

				while (satisfied == m) {

					minLen = Math.min(minLen, right - left + 1);

					int lc = arrN[left];
					cnt[lc]--;

					if (cnt[lc] == k[lc - 1] - 1) {
						satisfied--;
					}

					left++;
				}
			}

			return (minLen == Integer.MAX_VALUE) ? -1 : (minLen - sumK);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arrN = new int[n];
		int[] arrM = new int[m];

		for (int i = 0; i < n; i++) {
			arrN[i] = sc.nextInt();
		}

		for (int i = 0; i < m; i++) {
			arrM[i] = sc.nextInt();
		}

		Solution solution = new Solution();
		System.out.println(solution.minRemovals(n, m, arrN, arrM));

		sc.close();
	}
}

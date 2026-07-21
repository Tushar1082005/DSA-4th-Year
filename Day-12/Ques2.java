import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Ques2 {

	public static int maximalRectangle(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

		int n = matrix.length;
		int m = matrix[0].length;

		int[] height = new int[m];
		int ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					height[j]++;
				} else {
					height[j] = 0;
				}
			}

			ans = Math.max(ans, largestHistogram(height));
		}

		return ans;
	}

	private static int largestHistogram(int[] h) {
		Stack<Integer> st = new Stack<>();
		int max = 0;

		for (int i = 0; i <= h.length; i++) {
			int cur = (i == h.length) ? 0 : h[i];

			while (!st.isEmpty() && h[st.peek()] > cur) {
				int height = h[st.pop()];
				int width = st.isEmpty() ? i : i - st.peek() - 1;
				max = Math.max(max, height * width);
			}

			st.push(i);
		}

		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(maximalRectangle(matrix));
	}
}

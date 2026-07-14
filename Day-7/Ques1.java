import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Ques1 {

	static class Solution {

		public List<Integer> specialXor(int N, int Q, int[] a, int[][] query) {

			int xor = 0;
			for (int x : a) {
				xor ^= x;
			}

			int[] pre = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				pre[i] = pre[i - 1] ^ a[i - 1];
			}

			List<Integer> ans = new ArrayList<>();

			for (int i = 0; i < Q; i++) {
				int l = query[i][0];
				int r = query[i][1];

				int inside = pre[r] ^ pre[l - 1];
				ans.add(xor ^ inside);
			}

			return ans;
		}
	}

	static class FastScanner {
		private final InputStream in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0;
		private int len = 0;

		FastScanner(InputStream inputStream) {
			this.in = inputStream;
		}

		private int read() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0) {
					return -1;
				}
			}
			return buffer[ptr++];
		}

		int nextInt() throws IOException {
			int c;
			do {
				c = read();
			} while (c <= ' ' && c != -1);

			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}

			int value = 0;
			while (c > ' ') {
				value = value * 10 + (c - '0');
				c = read();
			}
			return value * sign;
		}
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner(new BufferedInputStream(System.in));

		int N = fs.nextInt();
		int Q = fs.nextInt();

		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = fs.nextInt();
		}

		int[][] query = new int[Q][2];
		for (int i = 0; i < Q; i++) {
			query[i][0] = fs.nextInt();
			query[i][1] = fs.nextInt();
		}

		Solution solution = new Solution();
		List<Integer> ans = solution.specialXor(N, Q, a, query);

		StringBuilder sb = new StringBuilder();
		for (int value : ans) {
			sb.append(value).append('\n');
		}
		System.out.print(sb.toString());
	}
}
 

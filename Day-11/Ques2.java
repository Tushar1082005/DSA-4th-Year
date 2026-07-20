import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

public class Ques2 {
	public static int longestConsecutive(int[] nums) {
		HashSet<Integer> s = new HashSet<>();
		for (int x : nums) s.add(x);

		int ans = 0;
		for (int x : s) {
			if (!s.contains(x - 1)) {
				int y = x;
				while (s.contains(y)) y++;
				ans = Math.max(ans, y - x);
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner(System.in);

		int n = fs.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = fs.nextInt();
		}

		System.out.println(longestConsecutive(nums));
	}

	static class FastScanner {
		private final InputStream in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0;
		private int len = 0;

		FastScanner(InputStream is) {
			this.in = new BufferedInputStream(is);
		}

		private int read() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0) return -1;
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
}

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

class Main {
	static long subarraysDivByK(int[] arr, int k) {
		HashMap<Integer, Integer> m = new HashMap<>();
		m.put(0, 1);

		long ans = 0;
		long s = 0;

		for (int x : arr) {
			s += x;
			int r = (int) ((s % k + k) % k);
			ans += m.getOrDefault(r, 0);
			m.put(r, m.getOrDefault(r, 0) + 1);
		}

		return ans;
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner(System.in);

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = fs.nextInt();
		}
		int k = fs.nextInt();

		System.out.println(subarraysDivByK(arr, k));
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
}


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Ques2 {

	public static int count(int[][] grid) {

		Queue<int[]> q = new LinkedList<>();

		int fresh = 0, time = 0;
		int n = grid.length, m = grid[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					q.offer(new int[] { i, j });
				} else if (grid[i][j] == 1) {
					fresh++;
				}
			}
		}

		int[] r = { -1, 1, 0, 0 };
		int[] c = { 0, 0, -1, 1 };

		while (!q.isEmpty() && fresh > 0) {

			for (int s = q.size(); s > 0; s--) {

				int[] x = q.poll();

				for (int k = 0; k < 4; k++) {

					int nr = x[0] + r[k];
					int nc = x[1] + c[k];

					if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1) {

						grid[nr][nc] = 2;
						fresh--;

						q.offer(new int[] { nr, nc });
					}
				}
			}

			time++;
		}

		return fresh == 0 ? time : -1;
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

		int n = fs.nextInt();
		int m = fs.nextInt();

		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = fs.nextInt();
			}
		}

		System.out.print(count(grid));
	}
}


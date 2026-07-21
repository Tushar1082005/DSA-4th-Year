import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Ques1 {

	public static String Reduced_String(int k, String s) {
		if (k == 1) return "";

		class Pair {
			char ch;
			int cnt;

			Pair(char c, int n) {
				ch = c;
				cnt = n;
			}
		}

		ArrayDeque<Pair> st = new ArrayDeque<>();

		for (char c : s.toCharArray()) {
			if (!st.isEmpty() && st.peekLast().ch == c) {
				st.peekLast().cnt++;
				if (st.peekLast().cnt == k) {
					st.pollLast();
				}
			} else {
				st.addLast(new Pair(c, 1));
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Pair p : st) {
			for (int i = 0; i < p.cnt; i++) {
				sb.append(p.ch);
			}
		}

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder input = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			input.append(line).append(' ');
		}

		StringTokenizer st = new StringTokenizer(input.toString());
		if (!st.hasMoreTokens()) {
			return;
		}

		int k = Integer.parseInt(st.nextToken());
		String s = st.hasMoreTokens() ? st.nextToken() : "";

		System.out.println(Reduced_String(k, s));
	}
}

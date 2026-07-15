import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {

	static class Solution {

		public List<String> AllPossibleStrings(String s) {
			List<String> ans = new ArrayList<>();
			generate(s, 0, new StringBuilder(), ans);
			Collections.sort(ans);
			return ans;
		}

		private void generate(String s, int idx, StringBuilder curr, List<String> ans) {
			if (idx == s.length()) {
				if (curr.length() > 0) {
					ans.add(curr.toString());
				}
				return;
			}

			generate(s, idx + 1, curr, ans);

			curr.append(s.charAt(idx));
			generate(s, idx + 1, curr, ans);
			curr.deleteCharAt(curr.length() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		if (s == null) {
			return;
		}

		s = s.trim();

		Solution sol = new Solution();
		List<String> result = sol.AllPossibleStrings(s);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.size(); i++) {
			if (i > 0) {
				sb.append(' ');
			}
			sb.append(result.get(i));
		}

		System.out.print(sb.toString());
	}
}

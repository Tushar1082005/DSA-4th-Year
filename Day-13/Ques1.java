import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ques1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) {
			sc.close();
			return;
		}

		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		int q = sc.nextInt();
		int[][] query = new int[q][2];
		for (int i = 0; i < q; i++) {
			query[i][0] = sc.nextInt();
			query[i][1] = sc.nextInt();
		}

		Ques1 obj = new Ques1();
		List<Integer> ans = obj.left(N, A, q, query);
		for (int value : ans) {
			System.out.println(value);
		}

		sc.close();
	}

	public List<Integer> left(int N, int[] A, int q, int[][] query) {
		List<Integer> ans = new ArrayList<>();

		if ((N & (N - 1)) != 0) {
			while (q-- > 0) {
				ans.add(-1);
			}
			return ans;
		}

		int h = 0;
		int temp = N;
		while (temp > 1) {
			h++;
			temp >>= 1;
		}

		int[] tree = new int[4 * N];

		build(1, 0, N - 1, h % 2 == 1, tree, A);

		for (int[] qu : query) {
			int idx = qu[0];
			int val = qu[1];

			if (idx < 0 || idx >= N) {
				ans.add(-1);
				continue;
			}

			update(1, 0, N - 1, idx, val, h % 2 == 1, tree);
			A[idx] = val;
			ans.add(tree[1]);
		}

		return ans;
	}

	private void build(int node, int l, int r, boolean isOr, int[] tree, int[] A) {
		if (l == r) {
			tree[node] = A[l];
			return;
		}

		int mid = (l + r) / 2;

		build(node * 2, l, mid, !isOr, tree, A);
		build(node * 2 + 1, mid + 1, r, !isOr, tree, A);

		if (isOr) {
			tree[node] = tree[node * 2] | tree[node * 2 + 1];
		} else {
			tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
		}
	}

	private void update(int node, int l, int r, int idx, int val, boolean isOr, int[] tree) {
		if (l == r) {
			tree[node] = val;
			return;
		}

		int mid = (l + r) / 2;

		if (idx <= mid) {
			update(node * 2, l, mid, idx, val, !isOr, tree);
		} else {
			update(node * 2 + 1, mid + 1, r, idx, val, !isOr, tree);
		}

		if (isOr) {
			tree[node] = tree[node * 2] | tree[node * 2 + 1];
		} else {
			tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
		}
	}
}

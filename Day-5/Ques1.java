import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ques1 {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	static class Solution {
		int cameras = 0;

		// 0 -> Node needs a camera
		// 1 -> Node has a camera
		// 2 -> Node is covered
		private int dfs(TreeNode root) {
			if (root == null) {
				return 2;
			}

			int left = dfs(root.left);
			int right = dfs(root.right);

			if (left == 0 || right == 0) {
				cameras++;
				return 1;
			}

			if (left == 1 || right == 1) {
				return 2;
			}

			return 0;
		}

		public int minCameraCover(TreeNode root) {
			if (dfs(root) == 0) {
				cameras++;
			}
			return cameras;
		}
	}

	// Input format (level-order):
	// n
	// v1 v2 v3 ... vn
	// Use -1 for null nodes.
	// Example:
	// 7
	// 0 0 0 -1 -1 0 0
	private static TreeNode buildTree(Scanner sc) {
		int n = sc.nextInt();
		if (n == 0) {
			return null;
		}

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		if (arr[0] == -1) {
			return null;
		}

		TreeNode root = new TreeNode(arr[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		int i = 1;
		while (!queue.isEmpty() && i < n) {
			TreeNode current = queue.poll();

			if (i < n && arr[i] != -1) {
				current.left = new TreeNode(arr[i]);
				queue.offer(current.left);
			}
			i++;

			if (i < n && arr[i] != -1) {
				current.right = new TreeNode(arr[i]);
				queue.offer(current.right);
			}
			i++;
		}

		return root;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		TreeNode root = buildTree(sc);
		Solution solution = new Solution();
		int answer = solution.minCameraCover(root);

		System.out.println(answer);
		sc.close();
	}
}

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int val) {
		this.val = val;
	}
}

class Solution {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		ArrayDeque<TreeNode> stack = new ArrayDeque<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode current = stack.pop();
			result.add(current.val);

			if (current.left != null) {
				stack.push(current.left);
			}
			if (current.right != null) {
				stack.push(current.right);
			}
		}

		Collections.reverse(result);
		return result;
	}
}

public class Ques1 {
	private static TreeNode buildTree(List<Integer> values) {
		if (values.isEmpty() || values.get(0) == null) {
			return null;
		}

		TreeNode root = new TreeNode(values.get(0));
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);

		int index = 1;
		while (!queue.isEmpty() && index < values.size()) {
			TreeNode current = queue.poll();

			if (index < values.size() && values.get(index) != null) {
				current.left = new TreeNode(values.get(index));
				queue.offer(current.left);
			}
			index++;

			if (index < values.size() && values.get(index) != null) {
				current.right = new TreeNode(values.get(index));
				queue.offer(current.right);
			}
			index++;
		}

		return root;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of nodes in level order representation:");
		int n = scanner.nextInt();

		List<Integer> values = new ArrayList<>();
		System.out.println("Enter the node values using -1 for null nodes:");
		for (int i = 0; i < n; i++) {
			int value = scanner.nextInt();
			values.add(value == -1 ? null : value);
		}

		TreeNode root = buildTree(values);
		Solution solution = new Solution();
		List<Integer> result = solution.postorderTraversal(root);

		for (int value : result) {
			System.out.print(value + " ");
		}
		System.out.println();

		scanner.close();
	}
}


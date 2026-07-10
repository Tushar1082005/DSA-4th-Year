import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ques2 {

	static class Solution {

		public int isBipartite(int[][] graph) {

			int n = graph.length;
			int[] color = new int[n];

			for (int i = 0; i < n; i++) {

				if (color[i] != 0) {
					continue;
				}

				Queue<Integer> q = new LinkedList<>();
				q.offer(i);
				color[i] = 1;

				while (!q.isEmpty()) {

					int node = q.poll();

					for (int nei : graph[node]) {

						if (color[nei] == 0) {
							color[nei] = -color[node];
							q.offer(nei);
						} else if (color[nei] == color[node]) {
							return 0;
						}
					}
				}
			}

			return 1;
		}
	}

	// Input format:
	// n
	// For each node i in [0..n-1]:
	// k nei1 nei2 ... neik
	// Example:
	// 4
	// 2 1 3
	// 2 0 2
	// 2 1 3
	// 2 0 2
	private static int[][] readGraph(Scanner sc) {
		int n = sc.nextInt();
		int[][] graph = new int[n][];

		for (int i = 0; i < n; i++) {
			int k = sc.nextInt();
			graph[i] = new int[k];
			for (int j = 0; j < k; j++) {
				graph[i][j] = sc.nextInt();
			}
		}

		return graph;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[][] graph = readGraph(sc);
		Solution solution = new Solution();
		int answer = solution.isBipartite(graph);

		System.out.println(answer);
		sc.close();
	}
}

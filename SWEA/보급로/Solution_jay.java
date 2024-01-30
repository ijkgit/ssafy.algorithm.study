import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Solution {
	int n;
	int[][] graph;
	int[][] sum;
	boolean[][] visited;
	int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().sol();
	}

	public void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testCase; t++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			for (int r = 0; r < graph.length; r++) {
				String row = br.readLine();
				for (int c = 0; c < graph.length; c++) {
					graph[r][c] = row.charAt(c) - '0';
				}
			}
			sum = new int[n][n];
			visited = new boolean[n][n];
			
			bfs();
			
			sb.append("#" + t + " " + sum[n - 1][n - 1] + "\n");
		}
		System.out.print(sb);
	}

	public void bfs() {
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(new Point(0, 0));
		visited[0][0] = true;
		
		while (!deque.isEmpty()) {
			Point point = deque.poll();
			int x = point.x;
			int y = point.y;
			for (int d = 0; d < direction.length; d++) {
				int newX = x + direction[d][0];
				int newY = y + direction[d][1];
				if (0 <= newX && newX < n && 0 <= newY && newY < n) {
					if (!visited[newX][newY]) {
						visited[newX][newY] = true;
						sum[newX][newY] = graph[newX][newY] + sum[x][y];
						deque.addLast(new Point(newX, newY));
					} else {
						if (graph[newX][newY] + sum[x][y] < sum[newX][newY]) {
							sum[newX][newY] = graph[newX][newY] + sum[x][y];
							deque.addLast(new Point(newX, newY));
						}
					}
				}
			}
		}
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}

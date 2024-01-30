import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_Recursive {
	int[][] direction = { { 0, 1 }, { 0, -1 }, { -1, 0 } };
	int[][] graph;
	int ax, ay;
	boolean[][] visited;

	public static void main(String[] args) throws IOException {
		new Solution_Recursive().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = 10;

		for (int t = 1; t <= testCase; t++) {
			br.readLine();
			graph = new int[100][100];
			for (int x = 0; x < graph.length; x++) {
				String row = br.readLine();
				StringTokenizer st = new StringTokenizer(row);
				for (int y = 0; y < graph.length; y++) {
					graph[x][y] = Integer.parseInt(st.nextToken());
					if (graph[x][y] == 2) {
						ax = x;
						ay = y;
					}
				}
			}

			visited = new boolean[100][100];
			sb.append("#").append(t).append(" ").append(dfs(ax, ay)).append("\n");
		}
		System.out.println(sb);
	}

	private int dfs(int x, int y) {
		// basis part
		if (x == 0)
			return y;

		// inductive part
		
		for (int d = 0; d < 2; d++) {
			int nx = x + direction[d][0];
			int ny = y + direction[d][1];

			if (checkStatus(nx, ny)) {
				visited[x][y] = true;
				return dfs(nx, ny);
			}
		}
		int nx = x + direction[2][0];
		int ny = y + direction[2][1];
		return dfs(nx, ny);
	}

	private boolean checkStatus(int x, int y) {
		return (0 <= x && x < 100 && 0 <= y && y < 100 && !visited[x][y] && graph[x][y] == 1);
	}
}

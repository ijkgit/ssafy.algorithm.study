import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private int[][] graph;
	private boolean[][][] visited;
	private int n;
	private int m;
	private int ans;
	private static final int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for (int i = 0; i < graph.length; i++) {
			String row = br.readLine();
			for (int j = 0; j < graph[0].length; j++) {
				graph[i][j] = row.charAt(j) - '0';
			}
		}

		int ans = bfs();

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[n][m][2];
		queue.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			if (p.x == n - 1 && p.y == m - 1)
				return p.cnt;
			
			for (int d = 0; d < direction.length; d++) {
				int nx = p.x + direction[d][0];
				int ny = p.y + direction[d][1];

				if (nx == n - 1 && ny == m - 1)
					return p.cnt + 1;

				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

				if (visited[nx][ny][p.v]) continue;

				if (p.v == 1 && graph[nx][ny] == 1) continue;

				if (graph[nx][ny] == 0) {
					visited[nx][ny][p.v] = true;
					queue.offer(new Point(nx, ny, p.cnt + 1, p.v));
				} else if (graph[nx][ny] == 1) {
					visited[nx][ny][1] = true;
					queue.offer(new Point(nx, ny, p.cnt + 1, 1));
				}
			}
		}

		return -1;
	}

	class Point {
		int x, y, cnt, v;

		public Point(int x, int y, int cnt, int v) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.v = v;
		}

	}
}

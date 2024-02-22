import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_semin {
	// 적록색약: (R,G),B, 아닌 사람: R,G,B
	static char[][] maps;
	static int N, yes, no;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		maps = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				maps[i][j] = line.charAt(j);
			}
		}
		int cnt = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(j, i, maps[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt).append(" ");
		cnt = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					diffBfs(j, i, maps[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt);
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(int x, int y, char start) {
		visited[y][x] = true;
		Deque<Point> dq = new ArrayDeque<>();
		dq.offer(new Point(x, y));
		while (!dq.isEmpty()) {
			Point p = dq.poll();
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + p.x;
				int ny = dy[d] + p.y;
				if (nx >= N || ny >= N || nx < 0 || ny < 0) {
					continue;
				}
				if (visited[ny][nx]) {
					continue;
				}
				if (maps[ny][nx] == start) {
					dq.offer(new Point(nx, ny));
					visited[ny][nx] = true;
				}
			}
		}
	}

	private static void diffBfs(int x, int y, char start) {
		visited[y][x] = true;
		Deque<Point> dq = new ArrayDeque<>();
		dq.offer(new Point(x, y));
		while (!dq.isEmpty()) {
			Point p = dq.poll();
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + p.x;
				int ny = dy[d] + p.y;
				if (nx >= N || ny >= N || nx < 0 || ny < 0) {
					continue;
				}
				if (visited[ny][nx]) {
					continue;
				}
				if (maps[ny][nx] == start
						|| (start == 'R' || start == 'G') && (maps[ny][nx] == 'R' || maps[ny][nx] == 'G')) {
					dq.offer(new Point(nx, ny));
					visited[ny][nx] = true;
				}
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, maps[][], ans;
	static int sx, sy, ex, ey;

	static class Point implements Comparable<Point> {
		int x, y, time;

		Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			maps = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());

			go(sx, sy, 0);
			sb.append(ans == Integer.MAX_VALUE ? -1 : ans).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void go(int x, int y, int time) {
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		Queue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(x, y, time));
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			x = p.x;
			y = p.y;
			time = p.time;
			if (x == ex && y == ey) {
				ans = time;
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int nt = time + 1;
				if (nx >= N || ny >= N || nx < 0 || ny < 0) {
					continue;
				}
				if (visited[nx][ny]) {
					continue;
				}
				if (maps[nx][ny] == 1) {
					continue;
				}
				if (maps[nx][ny] == 2 && ((nt) % 3 != 0)) {
					visited[nx][ny] = true;
					nt = nt % 3 == 2 ? nt + 1 : nt + 2;
					pq.add(new Point(nx, ny, nt));
					continue;
				}
				visited[nx][ny] = true;
				pq.add(new Point(nx, ny, nt));
			}
		}

	}
}

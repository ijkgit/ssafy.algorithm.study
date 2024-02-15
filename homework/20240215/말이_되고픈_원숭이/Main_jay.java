import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private int k;
	private int w;
	private int h;
	private int[][] map;
	private static final int[][] horseDirection = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 },
			{ 2, -1 }, { 2, 1 } };
	private static final int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
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
		boolean[][][] visited = new boolean[h][w][k + 1];
		queue.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			if (p.x == h - 1 && p.y == w - 1)
				return p.cnt;
			
			for (int d = 0; d < direction.length; d++) {
				int nx = p.x + direction[d][0];
				int ny = p.y + direction[d][1];

				if (checkStatus(nx, ny))
					continue;
				if (visited[nx][ny][p.k])
					continue;

				visited[nx][ny][p.k] = true;
				queue.offer(new Point(nx, ny, p.cnt + 1, p.k));

				if (nx == h - 1 && ny == w - 1)
					return p.cnt + 1;
			}
			
			if(p.k == k) continue;
			
			for (int hd = 0; hd < horseDirection.length; hd++) {
				int nx = p.x + horseDirection[hd][0];
				int ny = p.y + horseDirection[hd][1];

				if (checkStatus(nx, ny))
					continue;
				if (visited[nx][ny][p.k+1])
					continue;

				visited[nx][ny][p.k + 1] = true;
				queue.offer(new Point(nx, ny, p.cnt + 1, p.k + 1));

				if (nx == h - 1 && ny == w - 1)
					return p.cnt + 1;
			}

			
		}

		return -1;
	}

	private boolean checkStatus(int x, int y) {
		return x < 0 || y < 0 || x >= h || y >= w || map[x][y] == 1;
	}

	class Point {
		int x, y, cnt, k;

		public Point(int x, int y, int cnt, int k) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.k = k;
		}
	}
}

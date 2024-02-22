import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_semin {
	static int N, M, maps[][], current;
	static boolean visited[][];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Deque<Point> dq = new ArrayDeque<>();
	static Set<Point> sides = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int prev = 0;
		int day = 0;
		while (true) {
			sides.clear();
			visited = new boolean[N][M];
			bfs(0, 0);
			if (sides.size() == 0) {
				System.out.println(day);
				System.out.println(prev);
				return;
			} else {
				for (Point p : sides) {
					maps[p.y][p.x] = 0;
				}
			}
			prev = sides.size();
			day++;
		}

	}

	private static void bfs(int x, int y) {
		dq.clear();
		dq.offer(new Point(x, y));
		visited[y][x] = true;
		while (!dq.isEmpty()) {
			Point p = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if (nx >= M || ny >= N || nx < 0 || ny < 0 || visited[ny][nx]) {
					continue;
				}
				if (maps[ny][nx] == 1) {
					sides.add(new Point(nx, ny));
				} else {
					visited[ny][nx] = true;
					dq.add(new Point(nx, ny));
				}
			}
		}
	}
}

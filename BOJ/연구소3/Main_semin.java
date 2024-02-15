import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main_semin {
	static int N, M, maps[][], res;
	static List<Point> virus;
	static int[] vIdxs;
	static int[][] visited;
	static Deque<Point> dq = new ArrayDeque<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = Integer.MAX_VALUE;
		maps = new int[N][N];
		vIdxs = new int[M]; // 활성화시킬 바이러스 인덱스 리스트
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
				if (maps[i][j] == 2) {
					virus.add(new Point(j, i));
				}
			}
		}
		combination(0, 0);
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);
	}

	private static void combination(int idx, int k) {
		if (k == M) {
			bfs();
			return;
		}
		if (idx == virus.size()) {
			return;
		}
		vIdxs[k] = idx;
		combination(idx + 1, k + 1);
		combination(idx + 1, k);
	}

	private static void bfs() {
		dq.clear();
		visited = new int[N][N];
		for (int i = 0; i < M; i++) {
			Point p = virus.get(vIdxs[i]);
			visited[p.y][p.x] = 1;
			dq.add(p);
		}
		while (!dq.isEmpty()) {
			Point p = dq.poll();
			for (int k = 0; k < 4; k++) {
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if (nx >= N || ny >= N || nx < 0 || ny < 0) {
					continue;
				}
				if (maps[ny][nx] == 1 || visited[ny][nx] != 0) { // 벽이거나, 방문한 적이 있거나, 비활성화된 바이러스라면
					continue;
				}
				visited[ny][nx] = visited[p.y][p.x] + 1; // 비활성화된 바이러스를 만나면 이 때 활성화 됨(일반 빈칸과 똑같이 취급)
				dq.add(new Point(nx, ny));
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j] == 1 || maps[i][j] == 2) { // 처음부터 map에 바이러스나 벽이 있었다면
					continue;
				}
				if (visited[i][j] == 0) {
					return; // 만약 바이러스가 퍼지지 않은 빈 칸이 있다면
				}
				max = Math.max(max, visited[i][j] - 1);
			}
		}
		res = Math.min(max, res);

	}

}

public class Main_semin {
	static int N, M, maps[][];
	static boolean[][][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				maps[i][j] = Character.getNumericValue(line[j]);
			}
		}

		// (0,0) -> (N-1,M-1)
		bfs(0, 0);
	}

	private static void bfs(int r, int c) {
		Deque<Point> dq = new ArrayDeque<>();
		visited[r][c][0] = true;
		dq.add(new Point(r, c, 1, 0)); // r, c, 거리, 벽 부순적 있나.
		while (!dq.isEmpty()) {
			Point p = dq.poll();
			// System.out.println(p);
			if (p.r == N - 1 && p.c == M - 1) {
				System.out.println(p);
				System.out.println(p.dist);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (nr >= N || nc >= M || nr < 0 || nc < 0) {
					continue;
				}
				if (maps[nr][nc] == 0 && !visited[nr][nc][p.broke]) { // 지금 borke의 상태로 방문했는지를 봐야 함. 어짜피 벽은 안부술 것
					visited[nr][nc][p.broke] = true;
					dq.add(new Point(nr, nc, p.dist + 1, p.broke));
				}
				if (maps[nr][nc] == 1 && p.broke == 0) { // 벽이고, 벽을 한번도 부수지 x
					// 선택을 한다. 부술지
					if (!visited[nr][nc][1]) {
						visited[nr][nc][1] = true;
						dq.add(new Point(nr, nc, p.dist + 1, 1));
					}
					// 안부술지 (안부수면 못가므로 무용지물)
//					if (!visited[nr][nc][0]) {
//						visited[nr][nc][0] = true;
//						dq.add(new Point(nr, nc, p.dist + 1, 0));
//					}
				}
			}
		}
		System.out.println(-1);
	}

	static class Point {
		int r, c, dist, broke; // 부쉈으면 1, 안부쉈으면 0

		Point(int r, int c, int dist, int broke) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.broke = broke;
		}

		public String toString() {
			return "r: " + r + " c: " + c + " dist: " + dist + " 부순 적:" + broke;
		}
	}
}

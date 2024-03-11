import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/*
map N * N 에 대해, 가로인 상태에서 방문했는지, 세로인 상태에서 방문했는지 -> 3차원 배열 사용
 */
public class Main {
	private static int N;
	private static int[][] map;
	private static boolean[][][] v;
	private static ArrayDeque<Point> q;
	private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N][2];
		q = new ArrayDeque<>();
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
				// B의 중심을 찾기
				// d : 가로 0 세로 1
				if (map[i][j] == 'B') {
					cnt++;
					if (cnt != 2) continue;
					if (1 <= j && map[i][j - 1] == 'B') {
						v[i][j][0] = true;
						q.offer(new Point(i, j, 0));
					} else {
						v[i][j][1] = true;
						q.offer(new Point(i, j, 1));
					}
				}
			}
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static int bfs() {
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				if (checkDestination(p.x, p.y, p.d)) return cnt;

				for (int[] d : direction) {
					int nx = p.x + d[0];
					int ny = p.y + d[1];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

					if (checkMove(nx, ny, p.d)) {
						q.offer(new Point(nx, ny, p.d));
						v[nx][ny][p.d] = true;
					}
				}

				int nd = p.d == 1 ? 0 : 1;
				if (checkRotate(p.x, p.y, nd)) {
					q.offer(new Point(p.x, p.y, nd));
					v[p.x][p.y][nd] = true;
				}
			}
			cnt++;
		}
		return 0;
	}

	// 목적지를 검증하는 함수
	private static boolean checkDestination(int x, int y, int d) {
		if (d == 1) {
			for (int nx = x - 1; nx <= x + 1; nx++) {
				if (nx < 0 || nx >= N) return false;
				if (map[nx][y] != 'E') return false;
			}
		} else {
			for (int ny = y - 1; ny <= y + 1; ny++) {
				if (ny < 0 || ny >= N) return false;
				if (map[x][ny] != 'E') return false;
			}
		}
		return true;
	}

	// 이동 가능한지 확인하는 함수
	private static boolean checkMove(int x, int y, int d) {
		if (v[x][y][d]) return false;
		if (d == 1) {
			if (x == 0 || x == N - 1) return false;
			for (int nx = x - 1; nx <= x + 1; nx++) {
				if (map[nx][y] == '1') return false;
			}
		} else {
			if (y == 0 || y == N - 1) return false;
			for (int ny = y - 1; ny <= y + 1; ny++) {
				if (map[x][ny] == '1') return false;
			}
		}
		return true;
	}

	// 회전이 가능한지 확인하는 함수
	private static boolean checkRotate(int x, int y, int d) {
		if (v[x][y][d]) return false;
		for (int nx = x - 1; nx <= x + 1; nx++) {
			for (int ny = y - 1; ny <= y + 1; ny++) {
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) return false;
				if (map[nx][ny] == '1') return false;
			}
		}
		return true;
	}

	static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}

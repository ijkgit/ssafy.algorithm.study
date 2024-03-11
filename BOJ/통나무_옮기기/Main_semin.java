import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_semin {
	static int N, res;
	static char[][] maps;
	static boolean[][][] visited;

	static class Log {
		int x, y, dir, cnt;

		Log(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new char[N][N];
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				maps[i][j] = line[j];
			}
		}
		int sx = -1, sy = -1, sd = -1;
		L: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j] == 'B') {
					if (i + 1 < N && maps[i + 1][j] == 'B') {
						sx = i + 1;
						sy = j;
						sd = 1;
					}
					if (j + 1 < N && maps[i][j + 1] == 'B') {
						sx = i;
						sy = j + 1;
						sd = 0;
					}
					break L;
				}
			}
		}
		BFS(new Log(sx, sy, sd, 0));
		System.out.println(res);
	}

	private static void BFS(Log start) {
		Deque<Log> dq = new ArrayDeque<>();
		visited = new boolean[N][N][2]; // 가로:0, 세로:1
		visited[start.x][start.y][start.dir] = true;
		dq.add(start);

		while (!dq.isEmpty()) {
			Log log = dq.poll();
			if (isGoal(log)) {
				res = log.cnt;
				return;
			}
			if (checkDir(log, 0)) { //up
				visited[log.x - 1][log.y][log.dir] = true;
				dq.offer(new Log(log.x - 1, log.y, log.dir, log.cnt + 1));
			}
			if (checkDir(log, 1)) { //down
				visited[log.x + 1][log.y][log.dir] = true;
				dq.offer(new Log(log.x + 1, log.y, log.dir, log.cnt + 1));
			}
			if (checkDir(log, 2)) { //left
				visited[log.x][log.y - 1][log.dir] = true;
				dq.offer(new Log(log.x, log.y - 1, log.dir, log.cnt + 1));
			}
			if (checkDir(log, 3)) { //right
				visited[log.x][log.y + 1][log.dir] = true;
				dq.offer(new Log(log.x, log.y + 1, log.dir, log.cnt + 1));
			}
			if (checkTurn(log)) { //turn
				int nd = log.dir == 1 ? 0 : 1;
				visited[log.x][log.y][nd] = true;
				dq.offer(new Log(log.x, log.y, nd, log.cnt + 1));
			}
		}

	}

	private static boolean checkTurn(Log log) {
		int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int nd = log.dir == 1 ? 0 : 1;
		if (visited[log.x][log.y][nd]) {
			return false;
		}
		for (int d = 0; d < 8; d++) {
			int nx = log.x + dx[d];
			int ny = log.y + dy[d];
			if (nx >= N || ny >= N || nx < 0 || ny < 0) {
				return false;
			}
			if (maps[nx][ny] == '1') {
				return false;
			}
		}
		return true;
	}

	private static boolean checkDir(Log log, int md) {
		// up, down, left, right
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		if (log.dir == 0) { // 가로 통나무
			int[][] points = { { log.x, log.y - 1 }, { log.x, log.y }, { log.x, log.y + 1 } };
			int nx = log.x + dx[md];
			int ny = log.y + dy[md];
			if (nx >= N || ny >= N || nx < 0 || ny < 0) {
				return false;
			}
			if (visited[nx][ny][log.dir]) { // 현재 통나무 방향으로 밀었을 때 방문한 적 있는가?
				return false;
			}
			for (int[] p : points) {
				nx = p[0] + dx[md];
				ny = p[1] + dy[md];
				if (nx >= N || ny >= N || nx < 0 || ny < 0) {
					return false;
				}
				if (maps[nx][ny] == '1') {
					return false;
				}
			}
			return true;
		}
		
		// 세로 통나무
		int[][] points = { { log.x - 1, log.y }, { log.x, log.y }, { log.x + 1, log.y } };
		int nx = log.x + dx[md];
		int ny = log.y + dy[md];
		if (nx >= N || ny >= N || nx < 0 || ny < 0) {
			return false;
		}
		if (visited[nx][ny][log.dir]) { // 현재 통나무 방향으로 밀었을 때 방문한 적 있는가?
			return false;
		}
		for (int[] p : points) {
			nx = p[0] + dx[md];
			ny = p[1] + dy[md];
			if (nx >= N || ny >= N || nx < 0 || ny < 0) {
				return false;
			}
			if (maps[nx][ny] == '1') {
				return false;
			}
		}
		return true;
	}

	private static boolean isGoal(Log log) {
		if (maps[log.x][log.y] != 'E') {
			return false;
		}
		if (log.dir == 0) { // 가로
			if (log.y + 1 >= N || log.y - 1 < 0) {
				return false;
			}
			if (maps[log.x][log.y + 1] == 'E' && maps[log.x][log.y - 1] == 'E') {
				return true;
			}
		}
		if (log.dir == 1) { // 세로
			if (log.x + 1 >= N || log.x - 1 < 0) {
				return false;
			}
			if (maps[log.x + 1][log.y] == 'E' && maps[log.x - 1][log.y] == 'E') {
				return true;
			}
		}
		return false;
	}
}

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, maps[][], res;

	static class Pipe {
		Point start, end;
		int status; // 0:가로, 1:세로, 2:대각선

		Pipe(Point start, Point end, int status) {
			this.start = start;
			this.end = end;
			this.status = status;
		}

		@Override
		public String toString() {
			return "Pipe{" + "start=" + start + ", end=" + end + ", status=" + status + '}';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		res = 0;
		N = Integer.parseInt(br.readLine());
		maps = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Pipe p = new Pipe(new Point(1, 1), new Point(2, 1), 0);
		dfs(p);
		System.out.println(res);
	}

	private static void dfs(Pipe p) {
		if (p.end.x == N && p.end.y == N) {
			res++;
			return;
		}
		switch (p.status) {
		case 0:
			dfsWidth(p);
			dfsDiagonal(p);
			break;
		case 1:
			dfsHeight(p);
			dfsDiagonal(p);
			break;
		case 2:
			dfsWidth(p);
			dfsHeight(p);
			dfsDiagonal(p);
		}
	}

	static int[] dx = { 1, 0, 1 };
	static int[] dy = { 0, 1, 1 };

	private static void dfsDiagonal(Pipe p) {
		for (int i = 0; i < 3; i++) {
			int nx = p.end.x + dx[i];
			int ny = p.end.y + dy[i];
			if (nx > N || ny > N || maps[ny][nx] == 1) {
				return;
			}
		}
		int nx = p.end.x + dx[2];
		int ny = p.end.y + dy[2];
		dfs(new Pipe(p.end, new Point(nx, ny), 2));
	}

	private static void dfsWidth(Pipe p) {
		int nx = p.end.x + dx[0];
		int ny = p.end.y + dy[0];
		if (nx > N || ny > N || maps[ny][nx] == 1) {
			return;
		}
		dfs(new Pipe(p.end, new Point(nx, ny), 0));
	}

	private static void dfsHeight(Pipe p) {
		int nx = p.end.x + dx[1];
		int ny = p.end.y + dy[1];
		if (nx > N || ny > N || maps[ny][nx] == 1) {
			return;
		}
		dfs(new Pipe(p.end, new Point(nx, ny), 1));
	}
}

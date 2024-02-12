import java.util.*;
import java.io.*;

/*
 * 		[BOJ 2146] 다리 만들기
 * 			1. bfs 탐색 알고리즘으로 육지 별로 그룹핑
 * 			2. 육지에서 뻗어 나가는 bfs 로 최단거리 측정
 */
public class Main_sangphil {
	static class Pair {int x; int y; int w; public Pair(int x, int y, int w) {this.x=x;this.y=y;this.w=w;} public Pair(int x, int y) {this.x=x;this.y=y;}}
	static final int[] dx = {1,-1,0,0};
	static final int[] dy = {0,0,1,-1};
	static int N, ans;
	static int[][] arr;

	public static void main (String[] args) throws IOException {
		// System.setIn(Main.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int group = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					bfs(i, j, group++);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) {
					ans = Math.min(ans, calculDis(i, j, arr[i][j]));
				}
			}
		}
		System.out.println(ans);
	}
	static int calculDis (int x, int y, int group) {
		Deque<Pair> q = new ArrayDeque<Pair>(){{add(new Pair(x, y, 0));}};
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (arr[nx][ny] != group && arr[nx][ny] != 0) {
						return p.w;
					}
					if (arr[nx][ny] == 0 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny, p.w+1));
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	static void bfs (int x, int y, int group) {
		arr[x][y] = group;
		Deque<Pair> q = new ArrayDeque<Pair>(){{add(new Pair(x, y));}};

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (arr[nx][ny] == 1) {
						arr[nx][ny] = group;
						q.add(new Pair(nx, ny));
					}
				}
			}
		}
	}
}
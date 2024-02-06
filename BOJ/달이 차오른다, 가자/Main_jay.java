import java.io.*;
import java.util.*;

public class Main {
	char[][] graph;
	int n, m;
	int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new char[n][m];

		int startX = 0, startY = 0;
		for (int x = 0; x < n; x++) {
			String s = br.readLine();
			for (int y = 0; y < m; y++) {
				graph[x][y] = s.charAt(y);
				if (graph[x][y] == '0') {
					startX = x;
					startY = y;
				}
			}
		}
		visited = new boolean[n][m][64];
		int ans = sol(startX, startY, 0, 0);
		sb.append(ans);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol(int x, int y, int cnt, int keys) {

		Queue<Point> queue = new ArrayDeque<>();
		visited[x][y][keys] = true;
		queue.offer(new Point(x, y, cnt, keys));

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			x = p.x;
			y = p.y;
			cnt = p.cnt;
			keys = p.keys;

			for (int d = 0; d < direction.length; d++) {
				int newX = x + direction[d][0];
				int newY = y + direction[d][1];
				if (checkStatus(newX, newY, visited, keys)) { // 배열 범위를 벗어나지 않았는지, 벽이 아닌지, 방문 했는지 확인
					if (checkGo(newX, newY)) { // 일반 길
						visited[newX][newY][keys] = true;
						queue.offer(new Point(newX, newY, cnt + 1, keys));
					} else if (checkAns(newX, newY)) { // 탈출 시
						return cnt + 1;
					} else if (checkKey(newX, newY)) { // 소문자일 때
						int newKey = 1 << (graph[newX][newY] - 'a');
						newKey |= keys;
						visited[newX][newY][newKey] = true;
						queue.offer(new Point(newX, newY, cnt + 1, newKey));
					} else if (checkDoor(newX, newY)) { // 대문자일 때
						if (haveKey(newX, newY, keys)) { // 키가 있다면
							visited[newX][newY][keys] = true;
							queue.offer(new Point(newX, newY, cnt + 1, keys));
						}
					}
				}
			}
		}

		return -1; // 루프를 탈출했다면 미로를 빠져나가지 못한 것
	}

	private boolean checkStatus(int x, int y, boolean[][][] visited, int keys) {
		return 0 <= x && x < n && 0 <= y && y < m && graph[x][y] != '#' && !visited[x][y][keys];
	}

	private boolean checkGo(int x, int y) {
		return graph[x][y] == '.' || graph[x][y] == '0';
	}

	private boolean checkAns(int x, int y) {
		return graph[x][y] == '1';
	}

	private boolean checkKey(int x, int y) {
		return graph[x][y] >= 'a' && graph[x][y] <= 'f';
	}

	private boolean checkDoor(int x, int y) {
		return graph[x][y] >= 'A' && graph[x][y] <= 'F';
	}
	
	private boolean haveKey(int x, int y, int keys) {
		return (keys & (1 << graph[x][y] - 'A')) != 0;
	}

	class Point {
		int x;
		int y;
		int cnt;
		int keys;

		public Point(int x, int y, int cnt, int keys) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.keys = keys;
		}
	}
}

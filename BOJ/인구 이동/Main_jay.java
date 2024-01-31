package edu.ssafy.im.BOJ.No16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph;
	static int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static int n, l, r;
	static boolean[][] visited;
	static int[][] union;
	static int[][] unionNum;

	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	public void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		graph = new int[n][n];
		union = new int[n][n];
		for (int x = 0; x < n; x++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			for (int y = 0; y < n; y++) {
				graph[x][y] = Integer.parseInt(st.nextToken());
				union[x][y] = graph[x][y];
			}
		}
		
		int day = 0;
		while (true) {
			visited = new boolean[n][n];
			unionNum = new int[n][n];
			int unum = 0;
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (!visited[x][y]) {
						unum++;
						bfs(x, y, unum);
					}
				}
			}

			boolean flag = true;
			L: for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (graph[x][y] != union[x][y]) {
						flag = false;
						break L;
					}
				}
			}

			if (flag) {
				sb.append(day);
				break;
			}

			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					graph[x][y] = union[x][y];
				}
			}

			day++;
		}

		System.out.print(sb);
	}

	public void bfs(int x, int y, int unum) {
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(new Point(x, y));
		visited[x][y] = true;
		unionNum[x][y] = unum;

		int sum = graph[x][y];
		int cnt = 1;

		while (!deque.isEmpty()) {
			Point point = deque.poll();
			for (int d = 0; d < direction.length; d++) {
				int nx = point.x + direction[d][0];
				int ny = point.y + direction[d][1];

				if (checkStatus(nx, ny)) {
					if (checkRange(point.x, point.y, nx, ny)) {
						sum += graph[nx][ny];
						cnt++;
						visited[nx][ny] = true;
						deque.addLast(new Point(nx, ny));
						unionNum[nx][ny] = unum;
					}
				}
			}
		}
		
		int score = sum / cnt;
		if (cnt != 1) {
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (visited[r][c] && unionNum[r][c] == unum) {
						union[r][c] = score;
					}
				}
			}
		}
	}

	public boolean checkStatus(int x, int y) {
		return (0 <= x && x < n && 0 <= y && y < n && !visited[x][y]);
	}

	public boolean checkRange(int x, int y, int newX, int newY) {
		int diff = Math.abs(graph[x][y] - graph[newX][newY]);
		return (l <= diff && diff <= r);
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}

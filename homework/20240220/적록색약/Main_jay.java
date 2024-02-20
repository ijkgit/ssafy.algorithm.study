import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().io();
	}

	private int n;
	private char[][] map01;
	private char[][] map02;
	private boolean[][] visited;
	private static final int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		map01 = new char[n][n];
		map02 = new char[n][n];
		for (int i = 0; i < map01.length; i++) {
			String row = br.readLine();
			for (int j = 0; j < map01.length; j++) {
				map01[i][j] = row.charAt(j);
				if(map01[i][j] == 'G') {
					map02[i][j] = 'R';
				} else {
					map02[i][j] = map01[i][j];
				}
			}
		}
		
		sb.append(sol(map01)).append(" ").append(sol(map02));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private int sol(char[][] map) {
		visited = new boolean[n][n];
		int ans = 0;
		for (int i = 0; i < map01.length; i++) {
			for (int j = 0; j < map01.length; j++) {
				if(!visited[i][j]) {
					bfs(i, j, map[i][j], map);
					ans++;
				}
			}
		}
		return ans;
	}

	private void bfs(int x, int y, int COLOR, char[][] map) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x, y));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < direction.length; d++) {
				int nx = p.x + direction[d][0];
				int ny = p.y + direction[d][1];
				
				if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || map[nx][ny] != COLOR) continue;
				
				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny));
			}
		}
	}

	class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
